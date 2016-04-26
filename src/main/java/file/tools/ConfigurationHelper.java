package file.tools;

import java.io.File;
import java.net.URL;
import java.util.HashSet;
import java.util.Properties;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.io.SAXReader;

import file.datastructure.IConfiguration;
import file.datastructure.PropretiesConfiguration;
import file.datastructure.XmlConfiguration;
import file.service.IConfigurationHelper;

/**
 * 加载文件系统和jar包中的资源文件
 * @author: chenyun 
 * @since: 2015年1月4日 下午3:56:54 
 * @history:
 */
public class ConfigurationHelper implements IConfigurationHelper {
    static final String JARURL_PREFIX = "jar:file:";

    static final String FILE_PREFIX = "file:///";

    // 获取 classloader
    private ClassLoader outclassLoader = IConfigurationHelper.class
        .getClassLoader();

    @Override
    public File loadFile(String path, String encoding) {
        File file = null;
        try {
            String webrootfull = FileUtils.getWebRootFullpath();
            String finalPath = webrootfull
                    + "/"
                    + path.substring(IConfigurationHelper.path_relative
                        .length());
            file = new File(finalPath);

        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return file;
    }

    @Override
    public IConfiguration loadFileToConfiguration(String namespace,
            String path, String encoding, HashSet<String> traversalSet) {
        IConfiguration cfg = null;
        // 1.根据path构建url
        try {
            URL url;
            if (path.startsWith(FILE_PREFIX) || path.startsWith(JARURL_PREFIX)) {
                url = new URL(path);
            } else {
                url = outclassLoader.getResource(path);
            }
            // 2.根据url构建doc对象
            if (path.trim()
                .endsWith("." + IConfiguration.FORMATTYPE_PROPERTIES)) {
                Properties properties = new Properties();
                properties.load(url.openStream());// 文件内容
                cfg = new PropretiesConfiguration(url, properties);
            } else if (path.trim()
                .endsWith("." + IConfiguration.FORMATTYPE_XML)) {
                SAXReader reader = new SAXReader();
                reader.setEncoding(encoding);
                Document doc = reader.read(url.openStream());
                cfg = new XmlConfiguration(doc, url);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

        return cfg;
    }

    @Override
    public IConfiguration loadDomToConfiguration(URL url, String domContent) {
        IConfiguration cfg = null;
        Document doc;
        try {
            doc = DocumentHelper.parseText(domContent);
            cfg = new XmlConfiguration(doc, url);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return cfg;
    }

}
