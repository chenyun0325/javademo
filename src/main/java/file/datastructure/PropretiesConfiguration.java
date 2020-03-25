package file.datastructure;

import org.dom4j.io.OutputFormat;

import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.Properties;

public class PropretiesConfiguration implements IConfiguration {

    private URL url;

    private PropertyElement root;

    private Properties properties;

    public PropretiesConfiguration(URL url, Properties properties) {
        this.url = url;
        this.properties = properties;
        init(properties);
    }

    private void init(Properties properties) {
        root = new PropertyElement(properties);
    }

    @Override
    public URL getResource() {
        return url;
    }

    @Override
    public String getFormatType() {
        return IConfiguration.FORMATTYPE_PROPERTIES;
    }

    @Override
    public IElement getRoot() {
        return root;
    }

    @Override
    public void saveToFile(String path, String encoding) {
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
            this.properties.store(fos, null);
            fos.close();
            fcount.close();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    @Override
    public String getRootElementAsXml() {
        return null;
    }

}
