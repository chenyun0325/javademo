package base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by cy111966 on 2016/11/21.
 */
public class StringTest {

  public static void main(String[] args) {
    List<String> handler = new ArrayList<>();
    for (int i=0;i<10000;i++){
      BigData bd = new BigData();
      handler.add(bd.getSubString(0,5));
      bd = null;
    }
  }
  static class BigData{
    private String str = new String(new char[1000000]);
    public String getSubString(int begin ,int end){
      return str.substring(begin, end);
    }
  }
}
