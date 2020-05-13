package algorithm.string;

import com.alibaba.fastjson.JSON;

import java.util.HashSet;
import java.util.Set;

public class StringOpt {

    public static void main(String[] args) {

//        char[] s = "abcdef".toCharArray();
//        System.out.println(JSON.toJSONString(s));
//        leftRotateString(s,6,3);
//        System.out.println(JSON.toJSONString(s));

        String str="abc";
        printChildString(str.toCharArray(),0,"");
//        printChildString_v(str.toCharArray(),0);
//
//        String testStr = "abcdeab";
//
//        int i = lengthOfLongestSubstring(testStr);
//        System.out.println(i);

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

    /**
     * https://www.jianshu.com/p/5d6744acf6b4
     * @param chars
     * @param index
     * @param lastR
     */
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

    /**
     * https://www.jianshu.com/p/4ef3cfa01367
     * @param strs
     * @return
     */
    public static String longestCommonPrefix(String[] strs){
        if (strs.length==0){
            return "";
        }
        String minStr = strs[0];
        int minLength = minStr.length();
        for (String str : strs) {
            if (str.length()<minLength) {
                minLength=str.length();
                minStr=str;
            }
        }
        String result = "";
        int index = minLength;

        while (index>0){
            String substring = minStr.substring(0, index);
            boolean isMax = true;

            /**
             * 从第二个字符串开始比较
             */
            for (int i=1;i<strs.length;i++){
                /**
                 * 快速跳出循环执行index--
                 */
                if (!strs[i].startsWith(substring)) {
                    isMax = false;
                    break;
                }
            }

            if (isMax){
                result = substring;
                //得到最长公共前缀
                break;
            }
            index--;
        }

        return result;
    }

    public static void printSubString(String str){
        int sum =0;
        int length = str.length();
        for (int i =0;i<length;i++){
            for (int j= i+1;j<length;j++){
                String substring = str.substring(i, j);
                if (substring.equals(new StringBuffer(substring).reverse().toString())){
                    sum++;
                    System.out.println(substring);
                }
            }
        }
        System.out.println(sum);

    }


    /**
     * https://www.liangzl.com/get-article-detail-129119.html
     * https://www.iteye.com/blog/zhongkem-794763
     * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/solution/wu-zhong-fu-zi-fu-de-zui-chang-zi-chuan-by-leetc-2/
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s){
        // 哈希集合，记录每个字符是否出现过
        Set<Character> occ = new HashSet<>();
        int n = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;

        // i为左指针
        for (int i=0;i<n; ++i ){

            if (i!=0){
                // 左指针向右移动一格，移除一个字符
                occ.remove(s.charAt(i-1));
            }
            while (rk +1 <n && !occ.contains(rk+1)){
                // 不断地移动右指针
                occ.add(s.charAt(rk+1));
                rk++;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            ans = Math.max(ans,rk -i -1);

        }

        return ans;
    }
}
