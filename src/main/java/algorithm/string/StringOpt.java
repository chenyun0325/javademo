package algorithm.string;

import com.alibaba.fastjson.JSON;

public class StringOpt {

    public static void main(String[] args) {

//        char[] s = "abcdef".toCharArray();
//        System.out.println(JSON.toJSONString(s));
//        leftRotateString(s,6,3);
//        System.out.println(JSON.toJSONString(s));

        String str="abc";
        printChildString(str.toCharArray(),0,"");
        printChildString_v(str.toCharArray(),0);
    }

    /**
     * 双指针操作
     * @param chars
     * @param from
     * @param to
     */
    public static void reverseString(char[] chars,int from,int to){
        while (from<to){
            char t = chars[from];
            /**
             * 赋值+指针移动
             */
            chars[from++]=chars[to];
            chars[to--]= t;
        }
    }

    /**
     * 字符串中字符左移操作
     * abcdef --->n=6/m=3---->defabc
     * @param chars
     * @param n
     * @param m
     */
    public static void leftRotateString(char[] chars,int n,int m){

        //字符串切割
        m = m%n;
        reverseString(chars,0,m-1);
        reverseString(chars,m,n-1);
        reverseString(chars,0,n-1);
    }

    public static void printChildString(char[] chars,int index,String lastR){
        //index代表当前遍历的字符在字符串的位置
        if (index == chars.length){
            System.out.println(lastR);
            return;
        }
        printChildString(chars,index+1,lastR+String.valueOf(chars[index]));//传当前元素
        printChildString(chars,index+1,lastR);//不传当前元素
    }

    /*
     * 核心递归方法
     * */
    public static void printChildString_v(char[] arr, int i) {
        if (i == arr.length) {
            System.out.println("子序列：" + String.valueOf(arr));
        } else {
            char tmp = arr[i];
            arr[i] = 0;
            //不要当前位置字符
            printChildString_v(arr, i + 1);
            //要当前位置字符
            arr[i] = tmp;
            printChildString_v(arr, i + 1);
        }
    }

}
