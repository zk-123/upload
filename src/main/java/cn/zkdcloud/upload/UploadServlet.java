package cn.zkdcloud.upload;

import org.apache.log4j.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @author  zk
 * @version  2017/4/16.
 */
public class UploadServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger(UploadServlet.class);

    /**上传文件处理(POST)
     *
     * @param req request
     * @param resp response
     * @throws ServletException exception
     * @throws IOException exception
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        preHandler(req,resp);
        Part part = req.getPart(ConstUtil.getNameProperty());
        String originFileName = part.getSubmittedFileName();
        String ret = checkFile(part);//检查文件

        if (ret != null) {
            part.write(ret+"/"+ originFileName);
            resp.getWriter().print(ConstUtil.getHostUrl() + "/resources/" + ret + "/" + originFileName);

            logger.info("ip为" + getIp(req) + "存入的文件是：" + originFileName);
        } else {
            resp.getWriter().print("文件格式不支持");
            logger.error("文件格式不支持");
        }
    }

    /** req和resp的设置(contentType,encoding,allow-origin)
     *
     * @param request request
     * @param response response
     * @throws UnsupportedEncodingException exception
     */
    private void preHandler(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
    }

    /** 检查文件
     *
     * @param part filePart
     * @return suffix
     */
    private String checkFile(Part part) {
        String submittedFileName = part.getSubmittedFileName();
        String suffix = submittedFileName.substring(submittedFileName.lastIndexOf('.') + 1, submittedFileName.length());

        for (String filter : ConstUtil.getSuffixFilter().split(",")) {
            if (suffix.equals(filter)){
                initSubDir(suffix); //创建子目录
                return suffix;
            }
        }
        return null;
    }

    /**获取ip
     *
     * @param request request
     * @return ip
     */
    private String getIp(HttpServletRequest request) {
        if (request.getHeader("x-forwarded-for") == null) {
            return request.getRemoteAddr();
        }
        return request.getHeader("x-forwarded-for");
    }

    /**创建上传子目录
     *
     * @param dirName dirName
     */
    private void initSubDir(String dirName) {
        String subDirPath = ConstUtil.getLocation() + "/" + dirName;
        File subDir = new File(subDirPath);

        if (!subDir.exists()){
            subDir.mkdir();
            logger.info("初始化子目录："+dirName);
        }
    }
}
