package cn.zkdcloud.upload;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

/** 获取配置文件
 * @author zk
 * @version  2017/4/16.
 */
public class ConstUtil {
    private static Logger logger = Logger.getLogger(ConstUtil.class);

    private static Properties properties = new Properties();
    /*
    读取配置文件
     */
    static {
        try {
            properties.load(ConstUtil.class.getClassLoader().getResourceAsStream("upload.properties"));
        } catch (IOException e) {
            logger.error("读取不到配置目录文件");
        }
    }

    /*
    创建文件上传目录
     */
    static {
        String uploadDirPath = getLocation();
        File uploadDir = new File(uploadDirPath);

        if (!uploadDir.exists() && uploadDir.isDirectory()) {
            uploadDir.mkdir();
            logger.info("初始化文件上传目录:" + uploadDirPath);
        }
    }

    /**获取Location
     *
     * @return 上传文件路径
     */
    public static String getLocation(){
        return properties.getProperty("location");
    }

    /**获取允许的最大文件大小
     *
     * @return maxFileSize
     */
    public static Long getMaxFileSize(){
        return Long.valueOf(properties.getProperty("maxFileSize"));
    }

    /** 获取input 中name的值
     *
     * @return parameter(name)
     */
    public static String getNameProperty(){
        return properties.getProperty("nameProperty");
    }

    /** 获取允许上传的后缀
     *
     * @return suffers
     */
    public static String getSuffixFilter(){
        return properties.getProperty("suffixFilter");
    }

    /** 获取访问的URL
     *
     * @return URL
     */
    public static String getHostUrl(){
        String hostUrl = properties.getProperty("hostUrl").replace("\\","/");
        if(hostUrl.endsWith("/")){
            hostUrl.substring(0,hostUrl.length()-1);
        }
        return hostUrl;
    }
}
