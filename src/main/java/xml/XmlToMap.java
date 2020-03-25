package xml;

/**
 * Created by chenyun on 2019/10/17.
 */

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class XmlToMap {
    public static void main(String[] args) {
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "\n" +
                "<Data>\n" +
                "  <Data_Row>\n" +
                "    <Lis_Barcode>0000000099</Lis_Barcode>\n" +
                "    <pat_id>42</pat_id>\n" +
                "    <pat_no>0000000099</pat_no>\n" +
                "    <blood_time/>\n" +
                "    <pat_name>张三丰</pat_name>\n" +
                "    <pat_bedno/>\n" +
                "    <pat_sex>男</pat_sex>\n" +
                "    <pat_birthday>1994-01-24 00:00:00.0</pat_birthday>\n" +
                "    <pat_age>0</pat_age>\n" +
                "    <pat_ageunit>4</pat_ageunit>\n" +
                "    <dept_name>信息科</dept_name>\n" +
                "    <doctor_name>zyf</doctor_name>\n" +
                "    <clinical_diag/>\n" +
                "    <samp_name>全血</samp_name>\n" +
                "    <LisItems>\n" +
                "      <lis_item_code>403</lis_item_code>\n" +
                "      <lis_item_name>白细胞</lis_item_name>\n" +
                "      <SubItems>\n" +
                "        <lis_subitem_code>350</lis_subitem_code>\n" +
                "        <lis_subitem_name>WBC</lis_subitem_name>\n" +
                "      </SubItems>\n" +
                "    </LisItems>\n" +
                "    <LisItems>\n" +
                "      <lis_item_code>961</lis_item_code>\n" +
                "      <lis_item_name>乙肝三系定量（5项）</lis_item_name>\n" +
                "      <SubItems>\n" +
                "        <lis_subitem_code>316</lis_subitem_code>\n" +
                "        <lis_subitem_name>HBsAg（定量）</lis_subitem_name>\n" +
                "      </SubItems>\n" +
                "      <SubItems>\n" +
                "        <lis_subitem_code>1115</lis_subitem_code>\n" +
                "        <lis_subitem_name>HBeAg（定量）</lis_subitem_name>\n" +
                "      </SubItems>\n" +
                "      <SubItems>\n" +
                "        <lis_subitem_code>547</lis_subitem_code>\n" +
                "        <lis_subitem_name>HBsAb（定量）</lis_subitem_name>\n" +
                "      </SubItems>\n" +
                "      <SubItems>\n" +
                "        <lis_subitem_code>796</lis_subitem_code>\n" +
                "        <lis_subitem_name>HBeAb（定量）</lis_subitem_name>\n" +
                "      </SubItems>\n" +
                "      <SubItems>\n" +
                "        <lis_subitem_code>233</lis_subitem_code>\n" +
                "        <lis_subitem_name>HBcAb（定量）</lis_subitem_name>\n" +
                "      </SubItems>\n" +
                "    </LisItems>\n" +
                "  </Data_Row>\n" +
                "</Data>\n";

        try {
            //xml转map
            Map map = parseXml(xml);
            System.out.println("+++++++++++++++++");

            //map转xml
            StringBuffer sb = new StringBuffer();
            MapToXml.parseMap(map, sb);
            System.out.println(MapToXml.formatXML(sb.toString()));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * xml转为map,map中有list（节点相同时候)，list中有map
     *
     * @param xml
     * @return
     * @throws DocumentException
     */
    public static Map<String, Object> parseXml(String xml) throws DocumentException {
        Map map = new HashMap();
        try {
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(new ByteArrayInputStream(xml.getBytes("UTF-8")));//xml串第一行不能有空格，否则报错
            Element root = document.getRootElement();//得到xml文档根节点元素，即最上层的"<xml>"
            elementTomap(root, map);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return map;
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> elementTomap(Element outele, Map<String, Object> outmap) {
        List<Element> list = outele.elements();
        int size = list.size();
        if (size == 0) {
            outmap.put(outele.getName(), outele.getTextTrim());
        } else {
            Map<String, Object> innermap = new HashMap<String, Object>();
            int i = 1;

            for (Element ele1 : list) {
                String eleName = ele1.getName();

                String value = ele1.getText();
                Object obj = innermap.get(eleName);
                if (obj == null) {
                    elementTomap(ele1, innermap);
                } else {
                    if (obj instanceof java.util.Map) {
                        List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();
                        list1.add((Map<String, Object>) innermap.remove(eleName));
                        elementTomap(ele1, innermap);
                        list1.add((Map<String, Object>) innermap.remove(eleName));
                        innermap.put(eleName, list1);
                    } else if (obj instanceof String) {

                        innermap.put(eleName + i, value);
                        i++;
                    } else {
                        elementTomap(ele1, innermap);
                        Map<String, Object> listValue = (Map<String, Object>) innermap.get(eleName);
                        ((List<Map<String, Object>>) obj).add(listValue);
                        innermap.put(eleName, obj);
                    }

                }
            }
            outmap.put(outele.getName(), innermap);
        }
        return outmap;
    }
}