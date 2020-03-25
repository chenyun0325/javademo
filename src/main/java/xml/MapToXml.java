package xml;

/**
 * Created by chenyun on 2019/10/17.
 */

import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.*;

public class MapToXml {
    public static String formatXML(String xml) {
        String requestXML = null;
        try {
            // 拿取解析器
            SAXReader reader = new SAXReader();
            Document document = reader.read(new StringReader(xml));
            if (null != document) {
                StringWriter stringWriter = new StringWriter();
                // 格式化,每一级前的空格
                OutputFormat format = new OutputFormat("    ", true);
                // xml声明与内容是否添加空行
                format.setNewLineAfterDeclaration(false);
                // 是否设置xml声明头部
                format.setSuppressDeclaration(false);
                // 是否分行
                format.setNewlines(true);
                XMLWriter writer = new XMLWriter(stringWriter, format);
                writer.write(document);
                writer.flush();
                writer.close();
                requestXML = stringWriter.getBuffer().toString();
            }
            return requestXML;
        } catch (Exception e) {
            System.out.println("格式化xml，失败 --> {}"+e);
            return null;
        }
    }
    public static String  parseMap(Map<?, ?> map, StringBuffer sb) {
        Set<?> set = map.keySet();
        for (Iterator<?> it = set.iterator(); it.hasNext();) {
            String key = (String) it.next();
            Object value = map.get(key);
            if (value instanceof HashMap) {
                sb.append("<" + key + ">");
                parseMap((HashMap<?, ?>) value, sb);
                sb.append("</" + key + ">");
            } else if (value instanceof ArrayList) {
                List<?> list = (ArrayList<?>) map.get(key);
                for (int i = 0; i < list.size(); i++) {
                    sb.append("<" + key + ">");
                    Map<?, ?> hm = (HashMap<?, ?>) list.get(i);
                    parseMap(hm, sb);
                    sb.append("</" + key + ">");
                }
            } else {
                sb.append("<" + key + ">" + value + "</" + key + ">");
            }
        }
        return sb.toString();
    }
    /**
     * w3c Element 转成xml串
     * @param element
     * @return
     * @throws Exception
     */
    public static String w3cElementTransferXmlStr(org.w3c.dom.Element element) throws Exception{
        TransformerFactory tFactory = TransformerFactory.newInstance();
        Transformer transformer = tFactory.newTransformer();
        Source source = new DOMSource(element);
        StringWriter out = new StringWriter();
        Result output = new StreamResult(out);
        transformer.transform(source, output);
        out.flush();
        String ss = out.toString();
        return formatXML(ss);
    }
    /**
     *  xml串 转成w3c Element
     * @param xmlString
     * @return
     * @throws Exception
     */
    public static org.w3c.dom.Element getElementByString(String xmlString) throws Exception {
        if (xmlString == null){
            xmlString = "<returnInfo><returnCode>-999</returnCode><description>error</description></returnInfo>";
        }
        org.w3c.dom.Document doc = null;
        Reader strreader = new StringReader(xmlString);
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        doc = builder.parse(new InputSource(strreader));
        return doc.getDocumentElement();
    }

}