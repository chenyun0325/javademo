//给定一个经过编码的字符串，返回它解码后的字符串。 
//
// 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。 
//
// 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。 
//
// 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。 
//
// 
//
// 示例 1： 
//
// 输入：s = "3[a]2[bc]"
//输出："aaabcbc"
// 
//
// 示例 2： 
//
// 输入：s = "3[a2[c]]"
//输出："accaccacc"
// 
//
// 示例 3： 
//
// 输入：s = "2[abc]3[cd]ef"
//输出："abcabccdcdcdef"
// 
//
// 示例 4： 
//
// 输入：s = "abc3[cd]xyz"
//输出："abccdcdcdxyz"
// 
// Related Topics 栈 深度优先搜索

package algorithm.leetcode.editor.cn;

import java.util.LinkedList;

/**
 * https://leetcode-cn.com/problems/decode-string/solution/decode-string-fu-zhu-zhan-fa-di-gui-fa-by-jyd/
 */
public class DecodeString{
      public static void main(String[] args) {
           Solution solution = new DecodeString().new Solution();
          String decodeString = solution.decodeString("3[a2[c]]");
          System.out.println(decodeString);
          System.err.println(solution.decodeStringR("3[a2[c]]"));
      }
      //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String decodeString(String s) {
        StringBuilder res = new StringBuilder();
        int multi =0;
        LinkedList<Integer> stack_multi = new LinkedList<>();
        LinkedList<String> stack_res = new LinkedList<>();
        for (char c : s.toCharArray()) {
            if (c=='['){
                stack_multi.addLast(multi);
                stack_res.addLast(res.toString());
                multi=0;
                res = new StringBuilder();
            }else if (c == ']'){
                StringBuilder tmp = new StringBuilder();
                int cur_multi = stack_multi.removeLast();
                for (int i =0;i<cur_multi;i++){
                    tmp.append(res);
                }
                res = new StringBuilder(stack_res.removeLast()+tmp);
            }else if (c>='0'&&c<='9'){
               multi=multi*10+Integer.parseInt(c+"");
            }else {
                res.append(c);
            }
        }

        return res.toString();
    }

    public String decodeStringR(String s){

        return dfs(s,0)[0];
    }

    private String[] dfs(String s,int i){
        StringBuilder res = new StringBuilder();
        int multi =0;

        while (i<s.length()){
            char c = s.charAt(i);
            if (c >='0'&& c <='9') {
                multi = multi * 10 + Integer.parseInt(String.valueOf(c));
            }else if (c =='['){//递归开启条件
                String[] tmp =dfs(s,i+1);
                i=Integer.valueOf(tmp[0]);
                while (multi>0){
                    res.append(tmp[1]);
                    multi--;
                }
            }else if (c==']'){//递归退出条件
                return new String[]{String.valueOf(i),res.toString()};
            }else {
                res.append(c);
            }

            i++;
        }


        return new String[]{res.toString()};
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}