package file.datastructure;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Element;

public class XmlElement implements IElement {

    private Element dom4jElement;// 节点内容

    /**
     * 父节点
     */
    private IElement parent;

    /**
     * 子节点
     */
    private List<IElement> childs = new ArrayList<IElement>();

    /**
     * 节点名称
     */
    private String nodename;

    public XmlElement(Element dom4jElement, IElement parent) {
        this.dom4jElement = dom4jElement;
        this.parent = parent;
        this.nodename = dom4jElement.getName();
        init();
    }

    /**
     * 初始化子节点
     *  
     * @create: 2015年1月4日 下午2:28:39 chenyun
     * @history:
     */
    private void init() {
        List<Element> list = dom4jElement.elements();// 返回下层节点
        if (list != null) {
            for (Element element : list) {
                IElement child = new XmlElement(element, this);
                childs.add(child);
            }
        }

    }

    @Override
    public String getContent() {

        return dom4jElement.getText();
    }

    @Override
    public String getProperty(String key) {
        // TODO Auto-generated method stub
        return dom4jElement.attributeValue(key);
    }

    @Override
    public String getPropertyOrElementContent(String key) {
        String result = dom4jElement.attributeValue(key);
        if (result == null) {
            Element element = dom4jElement.element(key);
            result = element.getText();
        }
        return result;
    }

    @Override
    public String[] propertyNames() {
        List<Attribute> attributes = dom4jElement.attributes();
        String[] names = null;
        if (attributes != null && attributes.size() > 0) {
            names = new String[attributes.size()];
            for (int i = 0; i < attributes.size(); i++) {
                names[i] = attributes.get(i).getName();
            }
        }
        return names;
    }

    @Override
    public IElement getParent() {
        // TODO Auto-generated method stub
        return this.parent;
    }

    @Override
    public List<IElement> getChildren() {
        // TODO Auto-generated method stub
        return childs;
    }

    @Override
    public IElement getChild(String name) {
        IElement result = null;
        for (IElement child : childs) {
            if (((XmlElement) child).nodename.equals(name.trim())) {
                result = child;
                break;
            }
        }
        return result;
    }

    @Override
    public void setProperty(String key, String value) {
        dom4jElement.addAttribute(key, value);

    }

    @Override
    public void setContent(String text) {
        dom4jElement.setText(text);

    }

    /**
     * 获取指定节点下的子节点
     * @see IElement#getChildren(String)
     */
    @Override
    public List<IElement> getChildren(String name) {
        List<Element> element = dom4jElement.elements(name);
        List<IElement> childs = new ArrayList<IElement>();
        if (element != null) {
            IElement child = null;
            for (Element dom4jchild : element) {
                child = new XmlElement(dom4jchild, this);
                childs.add(child);
            }
        }
        return childs;
    }

}
