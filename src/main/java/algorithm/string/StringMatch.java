package algorithm.string;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class StringMatch {

    public static void main(String[] args) {

        List<String> subsequenceList = subSequence("abcdefg", 3);
        System.out.println(JSONObject.toJSONString(subsequenceList));

        printAllSubStr("abc");

        System.out.println(repeatStr("abcdabcd"));
    }


    /**
     * s中找出t模式出现的首个位置
     * @param s
     * @param t
     * @return
     */
    public static int bruteforce(String s , String t){
        int length = s.length();//目标字符串的长度
        int plength = t.length();//模式串的长度

        //循环目标字符串,i增加代表没有批量成功,模式串右移动
        for (int i=0;i<length-plength;i++){
            //循环模式串
            int j=0;

            while ((j<plength)&& s.charAt(i+j) == t.charAt(j)){
                j++;
            }
            if (j == plength){
                return i;
            }
        }



        return -1;
    }

    /**
     * 主串的给定长度m的子串
     * @param s
     * @param m
     * @return
     */
    public static List<String> subSequence(String s,int m){
        List<String> subSequenceList = new ArrayList<>();
        int length = s.length();
        if (length < m){
            throw new IllegalArgumentException();
        }
        for (int i = 0;i<=length -m ;i++){
            subSequenceList.add(s.substring(i,i+m));
        }
        return subSequenceList;
    }

    /**
     * 字符串所有子串
     * @param s
     */
    public static void printAllSubStr(String s){
        int length = s.length();
        for (int i =0;i<=length;i++){
            for (int j = i;j<=length;j++){
                System.out.println(s.substring(i,j));
            }
        }
    }


    /**
     * 最长重复子串
     * @param str
     * @return
     */
    public static String repeatStr(String str){

        if (str == null || str.length() < 1){
            return "";
        }

        //假设重复的字符串相隔1,2,3,4,...str.length();
        int k =0;
        int max =0;
        int first =0;

        for (int i =1;i<str.length();i++){
            //从当前位置开始，将相隔i 的重复的求出来！
            for (int j=0;j<str.length()-i;j++){
                if (str.charAt(j)==str.charAt(i+j)){
                    k++;
                }else {
                    k=0;
                }
                if (k>max){
                    max =k;
                    first=j-(k-1); //实质为：j-(k-1)
                }
            }
            k =0;
        }

        return str.substring(first,first+max);
    }


}
