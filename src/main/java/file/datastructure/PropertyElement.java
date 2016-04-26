package file.datastructure;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

public class PropertyElement implements IElement {

    private Properties properties;

    public PropertyElement(Properties properties) {
        this.properties = properties;
    }

    @Override
    public String getContent() {
        return null;
    }

    @Override
    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    @Override
    public String getPropertyOrElementContent(String key) {
        return properties.getProperty(key);
    }

    /**
     * 返回所有属性名称
     * @see IElement#propertyNames()
     */
    @Override
    public String[] propertyNames() {
        Enumeration e = properties.elements();
        List<String> list = new ArrayList<String>();
        while (e.hasMoreElements()) {
            list.add((String) e.nextElement());

        }
        return list.toArray(new String[list.size()]);
    }

    @Override
    public IElement getParent() {
        return null;
    }

    @Override
    public List<IElement> getChildren() {
        return null;
    }

    @Override
    public IElement getChild(String name) {
        return null;
    }

    @Override
    public void setProperty(String key, String value) {
        properties.setProperty(key, value);
    }

    @Override
    public void setContent(String text) {
    }

    @Override
    public List<IElement> getChildren(String name) {
        return null;
    }

}
