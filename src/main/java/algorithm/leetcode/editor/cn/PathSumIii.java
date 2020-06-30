//给定一个二叉树，它的每个结点都存放着一个整数值。 
//
// 找出路径和等于给定数值的路径总数。 
//
// 路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。 
//
// 二叉树不超过1000个节点，且节点数值范围是 [-1000000,1000000] 的整数。 
//
// 示例： 
//
// root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
//
//      10
//     /  \
//    5   -3
//   / \    \
//  3   2   11
// / \   \
//3  -2   1
//
//返回 3。和等于 8 的路径有:
//
//1.  5 -> 3
//2.  5 -> 2 -> 1
//3.  -3 -> 11
// 
// Related Topics 树

package algorithm.leetcode.editor.cn;

import com.thare.algorithm.tree.TreeNode;

/**
 * https://leetcode-cn.com/problems/path-sum-iii/solution/437lu-jing-zong-he-iii-di-gui-fang-shi-by-ming-zhi/
 * https://leetcode-cn.com/problems/path-sum-iii/solution/shuang-zhong-di-gui-hao-yong-de-hen-by-xiao-jian-f/
 */
public class PathSumIii{
      public static void main(String[] args) {
           Solution solution = new PathSumIii().new Solution();
      }
      //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public int pathSum(TreeNode root, int sum) {
        if (root == null){
            return 0;
        }
        /**
         * 分解三部分:当前结点,当前节点左右子树
         */
        int current = countPath(root,sum);
        int left = pathSum(root.left,sum);
        int right = pathSum(root.right,sum);
        return current+left+right;
    }
    public int countPath(TreeNode root,int sum){
        if (root == null){
            return 0;
        }
        sum = sum - root.val;
        int result = sum==0?1:0;
        return result + countPath(root.left,sum)+countPath(root.right,sum);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}