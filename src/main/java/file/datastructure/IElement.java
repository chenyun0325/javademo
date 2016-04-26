package file.datastructure;

import java.util.List;

public interface IElement {
    /**
     * 取得节点的文本内容
     * 
     * @return
     */
    public String getContent();

    /**
     * 取得属性值
     * 
     * @param key
     * @return
     */
    public String getProperty(String key);

    /**
     * 取得相应key属性值或者子节点的内容。先取属性值，如果为空，则取key所对应的子节点的值
     * 
     * @param key
     * @return
     */
    public String getPropertyOrElementContent(String key);

    /**
     * 取得所有属性名称
     * 
     * @return
     */
    public String[] propertyNames();

    /**
     * 取得父节点
     * 
     * @return
     */
    public IElement getParent();

    /**
     * 取得所有儿子节点（不包括次级以下的子孙）
     * 
     * @return
     */
    public List<IElement> getChildren();

    /**
     * 根据名称取得孩子节点
     * 
     * @param name
     * @return
     */
    public IElement getChild(String name);

    /**
     * 保存属性值
     * 
     * @param key
     * @param value
     */
    public void setProperty(String key, String value);

    /**
     * 保存节点文本内容
     * 
     * @param text
     */
    public void setContent(String text);

    /**
     * 按指定的名称获取儿子节点列表
     * @param name
     * @return
     */
    public List<IElement> getChildren(String name);

}
