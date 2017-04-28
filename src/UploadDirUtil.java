import org.apache.log4j.Logger;
import java.io.IOException;
import java.util.Properties;

/**
 * @Author zk
 * @Date 2017/4/16.
 * @Email 2117251154@qq.com
 */
public class UploadDirUtil {
    private static Logger logger = Logger.getLogger(UploadDirUtil.class);
    private static Properties properties = new Properties();
    static {
        try {
            properties.load(UploadDirUtil.class.getClassLoader().getResourceAsStream("upload.properties"));
        } catch (IOException e) {
            System.out.println("读取不到配置目录文件");
        }
    }
    public static String getUploadDirPath(){
        return properties.getProperty("uploadDir");
    }
    public static String getNameProperty(){
        return properties.getProperty("nameProperty");
    }
    public static String getSuffixFilter(){
        return properties.getProperty("suffixFilter");
    }
    public static String getHostUrl(){
        return properties.getProperty("hostUrl");
    }
}
