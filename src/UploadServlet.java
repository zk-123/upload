import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * @Author zk
 * @Date 2017/4/16.
 * @Email 2117251154@qq.com
 */
public class UploadServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger(UploadServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setHeader("Access-Control-Allow-Origin", "*");


        Part part = req.getPart(UploadDirUtil.getNameProperty());
        String submittedFileName = part.getSubmittedFileName();
        String suffix = submittedFileName.substring(submittedFileName.lastIndexOf('.') + 1, submittedFileName.length());
        //检查后缀
        if (checkFile(suffix)) {
            initSubDir(suffix);
            part.write(suffix+"/"+part.getSubmittedFileName());
            resp.getWriter().print(UploadDirUtil.getHostUrl() + "/" + suffix + "/" + part.getSubmittedFileName());
            logger.info("ip为" + getIp(req) + "存入的文件是：" + submittedFileName);
        } else {
            logger.error("非法的后缀:" + submittedFileName);
            resp.getWriter().print("文件格式不支持");
        }

    }

    /**
     * 检查后缀
     *
     * @param suffix
     * @return
     */
    private boolean checkFile(String suffix) {
        for (String filter : UploadDirUtil.getSuffixFilter().split(",")) {
            if (suffix.equals(filter))
                return true;
        }
        return false;
    }

    /**
     * 获取ip
     *
     * @param request
     * @return
     */
    private String getIp(HttpServletRequest request) {
        if (request.getHeader("x-forwarded-for") == null) {
            return request.getRemoteAddr();
        }
        return request.getHeader("x-forwarded-for");
    }

    /**
     * 文件上传子目录
     *
     * @param dirName
     */
    private void initSubDir(String dirName) {

        initUploadDir();
        String subDirPath = UploadDirUtil.getUploadDirPath() + "/" + dirName;
        File subDir = new File(subDirPath);
        if (!subDir.exists())
            subDir.mkdir();

        logger.info("初始化子目录："+dirName);
    }

    /**
     * 文件上传目录
     */
    private void initUploadDir() {
        String uploadDirPath = UploadDirUtil.getUploadDirPath();
        File uploadDir = new File(uploadDirPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
            logger.info("初始化文件上传目录:" + uploadDirPath);
        }
    }
}
