package file.tools;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtils {

    static String webroot;

    static String fileFullpath;

    static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

    /**
     * 将字符串补充成定长
     * @param str
     * @param length
     * @return 
     * @create: 2014年12月26日 上午12:40:18 chenyun
     * @history:
     */

    public static String formatStr(String str, int length) {
        int size = str.length();
        int gap = length - size;
        if (gap < 0) {
            logger.error("xxxxxxx");
        }
        for (int i = 0; i < gap; i++) {
            str = str + "";
        }
        return str;
    }

    /**
     * 获取web根目录
     * @return 
     * @create: 2015年1月8日 下午8:17:10 chenyun
     * @history:
     */
    public static String getWebRoot() {
        if (webroot == null) {
            File file = null;
            // 根据classloader寻找资源url
            URL url = FileUtils.class.getClassLoader().getResource("");
            String filename = url.getFile();
            String Protocol = url.getProtocol();
            System.err.println(Protocol);
            System.err.println(filename);
            try {
                file = new File(URLDecoder.decode(filename, "utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            if (file != null) {
                webroot = file.getParentFile().getName();
            }
        }

        return webroot;
    }

    /**
     * 获取class文件的执行目录,getResource("")--表示获取根目录。
     * @return 
     * @create: 2015年1月8日 下午10:07:11 chenyun
     * @history:
     */
    public static String getWebRootFullpath() {
        if (fileFullpath == null) {
            File file = null;
            // 根据classloader寻找资源url
            URL url = FileUtils.class.getClassLoader().getResource("");
            String filename = url.getFile();
            try {
                file = new File(URLDecoder.decode(filename, "utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            if (file != null) {
                fileFullpath = file.getAbsolutePath();
            }
        }
        return fileFullpath;
    }

    /**
     * 从定义的目录查找文件
     * @param folder
     * @param targetFoldername
     * @return 
     * @create: 2015年1月8日 下午9:05:48 chenyun
     * @history:
     */
    public static File getTarget(File folder, String targetFoldername) {
        File targetfolder = null;
        // 子目录
        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()
                        && file.getName().equals(targetFoldername)) {
                    targetfolder = file;
                    return targetfolder;
                }
            }
        }
        // 父目录
        File parentFolder = folder.getParentFile();
        if (parentFolder != null) {
            targetfolder = getTarget(parentFolder, targetFoldername);
        }
        return targetfolder;
    }

}
