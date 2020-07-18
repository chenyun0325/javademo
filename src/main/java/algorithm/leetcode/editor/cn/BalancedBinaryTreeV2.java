// 给定一个二叉树，判断它是否是高度平衡的二叉树。
//
// 本题中，一棵高度平衡二叉树定义为：
//
//
// 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
//
//
// 示例 1:
//
// 给定二叉树 [3,9,20,null,null,15,7]
//
// 3
// / \
// 9 20
// / \
// 15 7
//
// 返回 true 。
//
// 示例 2:
//
// 给定二叉树 [1,2,2,3,3,null,null,4,4]
//
// 1
// / \
// 2 2
// / \
// 3 3
// / \
// 4 4
//
//
// 返回 false 。
//
//
// Related Topics 树 深度优先搜索

package algorithm.leetcode.editor.cn;

import com.thare.algorithm.archive.nowcoder.coding_interviews.helper.TreeNode;

public class BalancedBinaryTreeV2 {
    public static void main(String[] args) {
        Solution solution = new BalancedBinaryTreeV2().new Solution();
    }

    // leetcode submit region begin(Prohibit modification and deletion)
    /**
     * Definition for a binary tree node. public class TreeNode { int val; TreeNode left; TreeNode right; TreeNode(int
     * x) { val = x; } }
     */
    class Solution {
        public boolean isBalanced(TreeNode root) {
            return height(root) != -1;
        }

        /**
         * https://leetcode-cn.com/problems/balanced-binary-tree/solution/di-gui-fang-fa-zhong-de-yi-xie-xiao-you-hua-by-oum/
         */
        public int height(TreeNode node) {
            /**
             * base case
             */
            if (node == null) {
                return 0;
            }
            if (node.left == null && node.right == null) {
                return 1;
            }
            /**
             * 不平衡直接返回
             * If left subtree is unbalanced, we don't need to compute for right subtree
             */
            int leftHeight = height(node.left);
            if (leftHeight < 0) {
                return -1;
            }
            int rightHeight = height(node.right);

            if (rightHeight < 0) {
                return -1;
            }
            if (Math.abs(leftHeight - rightHeight) > 1) {
                return -1;
            }

            return 1 + Math.max(rightHeight, leftHeight);
        }
    }
    // leetcode submit region end(Prohibit modification and deletion)

}
