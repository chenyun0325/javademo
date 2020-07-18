// 给定一个二叉树，找出其最大深度。
//
// 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
//
// 说明: 叶子节点是指没有子节点的节点。
//
// 示例：
// 给定二叉树 [3,9,20,null,null,15,7]，
//
// 3
// / \
// 9 20
// / \
// 15 7
//
// 返回它的最大深度 3 。
// Related Topics 树 深度优先搜索

package algorithm.leetcode.editor.cn;

import com.thare.algorithm.archive.nowcoder.coding_interviews.helper.TreeNode;
import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Stack;

public class MaximumDepthOfBinaryTree {
    public static void main(String[] args) {
        Solution solution = new MaximumDepthOfBinaryTree().new Solution();
    }

    // leetcode submit region begin(Prohibit modification and deletion)
    /**
     * Definition for a binary tree node. public class TreeNode { int val; TreeNode left; TreeNode right; TreeNode(int
     * x) { val = x; } }
     */
    class Solution {

        /**
         * 经典参考资料：
         * https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/solution/javashi-xian-san-chong-fang-fa-di-gui-shi-xian-die/
         * https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/solution/er-cha-shu-de-zui-xiao-shen-du-by-leetcode/
         * 递归理解: https://blog.csdn.net/u013505811/article/details/94603773
         * 
         * @param root
         * @return
         */
        public int maxDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int leftDepth = maxDepth(root.left);
            int rightDepth = maxDepth(root.right);
            return Math.max(leftDepth, rightDepth) + 1;
        }

        /**
         * 先序遍历
         * 
         * @param root
         * @return
         */
        public int maxDepthDfs(TreeNode root) {
            if (root == null) {
                return 0;
            }
            Stack<Pair<TreeNode, Integer>> stack = new Stack<>();
            stack.push(new Pair<>(root, 1));
            int maxDepth = 0;
            while (!stack.isEmpty()) {
                Pair<TreeNode, Integer> pop = stack.pop();
                TreeNode node = pop.getKey();
                //当前深度
                Integer current = pop.getValue();
                if (current > maxDepth) {
                    maxDepth = current;
                }
                if (node.right != null) {
                    stack.push(new Pair<>(node.right, current + 1));
                }
                if (node.left != null) {
                    stack.push(new Pair<>(node.left, current + 1));
                }
            }

            return maxDepth;
        }


        private  int maxDepth1(TreeNode root) {
            if (root == null) {
                return 0;
            }
            //BFS的层次遍历思想，记录二叉树的层数，
            //遍历完，层数即为最大深度
            LinkedList<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            int maxDepth = 0;
            while (!queue.isEmpty()) {
                maxDepth++;
                int levelSize = queue.size();
                for (int i = 0; i < levelSize; i++) {
                    TreeNode node = queue.pollFirst();
                    if (node.left != null) {
                        queue.add(node.left);
                    }
                    if (node.right != null) {
                        queue.add(node.right);
                    }
                }
            }
            return maxDepth;
        }
    }
    // leetcode submit region end(Prohibit modification and deletion)

}
