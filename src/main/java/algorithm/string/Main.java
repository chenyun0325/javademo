package algorithm.string;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

        //保存最长公共子串
        static String result = "";
        public static void main(String [] args)  {

            List<String> list = new ArrayList<>();
            String s = "abcdabcd";
            //得到字符串的所有后缀
            for(int i = s.length()-1;i>=0;i--) {
                list.add(s.substring(i));
            }
            Collections.sort(list);
//            String longestCommonPrefix = StringOpt.longestCommonPrefix(list.toArray(new String[list.size()]));
//            System.err.println(longestCommonPrefix);
            int maxLen = 0;
            for(int i = 0;i<s.length()-1;i++) {
                int len = getComlen(list.get(i),list.get(i+1));
                maxLen = Math.max(maxLen, len);
            }
            System.out.println(result+":"+maxLen);
        }
        //得到两个字符串最长公共长度
        public static int getComlen(String str1,String str2) {
            int i;
            for(i =0;i<str1.length()&&i<str2.length();i++) {
                if(str1.charAt(i)!=str2.charAt(i)) {
                    break;
                }
            }
            String temp = str1.substring(0,i);
            if(temp.length()>result.length()) result = temp;
            return i;
        }
}
