package cn.zkdcloud;

import cn.zkdcloud.upload.ConstUtil;
import cn.zkdcloud.upload.UploadServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;

import javax.servlet.MultipartConfigElement;

/**
 * @author zk
 * @version 2017/8/13
 */
public class Start {

    /** 上传文件位置context
     *
     * @return uploadWebAppContext
     */
    public static WebAppContext getUploadContext(){
        WebAppContext uploadContext = new WebAppContext();
        String uploadResources = ConstUtil.getLocation();

        uploadContext.setResourceBase(uploadResources);
        uploadContext.setContextPath("/resources");
        return uploadContext;
    }

    public static void main(String[] args) throws Exception {
        Server server = new Server(1000);
        String location = ConstUtil.getLocation();//本地位置
        Long maxFileSize = ConstUtil.getMaxFileSize();//最大上传大小
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        WebAppContext context = new WebAppContext();

        ServletHolder uploadServlet = new ServletHolder(new UploadServlet());
        uploadServlet.getRegistration().setMultipartConfig(new MultipartConfigElement(location,maxFileSize,-1L,0));
        context.addServlet(uploadServlet,"/upload");// http://ip:port/upload
        context.setContextPath("/");
        context.setResourceBase(classLoader.getResource("").getPath().replace("/target/classes/","/src/main/resources/"));
        context.setClassLoader(classLoader);

        HandlerCollection handlers = new HandlerCollection();
        handlers.addHandler(getUploadContext());
        handlers.addHandler(context);
        server.setHandler(handlers);
        server.start();
    }
}
