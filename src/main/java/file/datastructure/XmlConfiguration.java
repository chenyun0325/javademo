package file.datastructure;

import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class XmlConfiguration implements IConfiguration {
    /**
     * 文档对象
     */
    private Document doc;

    /**
     * 根节点
     */
    private XmlElement root;

    /**
     * url
     */
    private URL url;

    public XmlConfiguration(Document doc, URL url) {
        this.doc = doc;
        this.url = url;
        this.root = new XmlElement(doc.getRootElement(), null);
    }

    @Override
    public URL getResource() {
        // TODO Auto-generated method stub
        return url;
    }

    @Override
    public String getFormatType() {
        // TODO Auto-generated method stub
        return IConfiguration.FORMATTYPE_XML;
    }

    @Override
    public IElement getRoot() {
        // TODO Auto-generated method stub
        return root;
    }

    /**
     * doc的内容本地保存
     * @see IConfiguration#saveToFile(String, String)
     */
    @Override
    public void saveToFile(String path, String encoding) {
        XMLWriter writer;
        OutputFormat format;
        FileOutputStream fos;
        FileChannel fcount;
        FileLock flLock;

        try {
            format = OutputFormat.createPrettyPrint();
            format.setEncoding(encoding);
            fos = new FileOutputStream(path);
            fcount = fos.getChannel();
            flLock = null;
            while (true) {
                flLock = fcount.tryLock();
                if (flLock != null) {
                    break;
                } else {
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        // TODO: handle exception
                        e.printStackTrace();
                    }
                }

            }
            writer = new XMLWriter(fos, format);
            writer.write(doc);
            writer.close();
            fos.close();
            fcount.close();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    @Override
    public String getRootElementAsXml() {
        // TODO Auto-generated method stub
        return doc.asXML();
    }

}
