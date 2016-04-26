package file.datastructure;

import java.net.URL;

public interface IConfiguration {

    public static final String FORMATTYPE_PROPERTIES = "properties";

    public static final String FORMATTYPE_XML = "xml";

    /**
     * 得到资源的URL
     * 
     * @return
     */
    public URL getResource();

    /**
     * 得到格式类型，比如properties或xml
     * 
     * @return
     */
    public String getFormatType();

    /**
     * 得到根节点
     * 
     * @return
     */
    public IElement getRoot();

    /**
     * 保存XML到文件
     * 
     * @param path
     *            路径
     * @param encoding
     *            编码
     */
    public void saveToFile(String path, String encoding);

    /**
     * rootElement转为xml形式
     * 
     * @return xml
     */
    public String getRootElementAsXml();

}
