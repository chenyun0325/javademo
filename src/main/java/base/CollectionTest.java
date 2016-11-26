package base;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by cy111966 on 2016/11/22.
 */
public class CollectionTest {

  static Map<String,String> map = new HashMap(){
    {
      put("x","x323");
      put("y","x23");
      put("z","x32");
      put("k","x3");
      put("m","x1");
      put("v","x1322");
      put("ewwww","we");
    }
  };
  public static void main(String[] args) {
    int i = 0;
    Iterator<String> its = map.keySet().iterator();
    while (its.hasNext()) {
      String next = its.next();
      if (next.equals("y")){
        its.remove();
      }
    }
    System.out.println(map.keySet().size());
    for (String s : map.values()) {
      System.out.println(s);
    }
  }
}
