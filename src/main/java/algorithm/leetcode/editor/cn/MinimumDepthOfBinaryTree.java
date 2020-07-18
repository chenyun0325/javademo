// 给定一个二叉树，找出其最小深度。
//
// 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
//
// 说明: 叶子节点是指没有子节点的节点。
//
// 示例:
//
// 给定二叉树 [3,9,20,null,null,15,7],
//
// 3
// / \
// 9 20
// / \
// 15 7
//
// 返回它的最小深度 2.
// Related Topics 树 深度优先搜索 广度优先搜索

package algorithm.leetcode.editor.cn;

import algorithm.tree.BinaryTree;
import algorithm.tree.BinaryTree.Node;

import static algorithm.tree.BinaryTree.createBinTree;

/**
 * https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/solution/yi-ti-nang-gua-ji-hu-suo-you-de-ji-ben-suan-fa-by-/
 * https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/solution/javashi-xian-san-chong-fang-fa-di-gui-shi-xian-die/
 */
public class MinimumDepthOfBinaryTree {
    public static void main(String[] args) {
        Solution solution = new MinimumDepthOfBinaryTree().new Solution();
        int[] arr = {1,2,3,4,5,6,7,8,9};
        createBinTree(arr);
        Node root = BinaryTree.nodeList.get(0);
        System.out.println(solution.minDepth(root,1));
    }

    // leetcode submit region begin(Prohibit modification and deletion)
    /**
     * Definition for a binary tree node. public class TreeNode { int val; TreeNode left; TreeNode right; TreeNode(int
     * x) { val = x; } }
     */
    class Solution {
        public int minDepth(Node root) {
            if (root == null) {
                return 0;
            }
            if (root.left == null && root.right == null) {
                return 1;
            }
            int minDepth = Integer.MAX_VALUE;

            if (root.left != null) {
                minDepth = Math.min(minDepth(root.left), minDepth);
            }
            if (root.right != null) {
                minDepth = Math.min(minDepth(root.right), minDepth);
            }

            return minDepth + 1;
        }

        public int minDepth1(Node root) {
            if (root == null) {
                return 0;
            }
            //这道题递归条件里分为三种情况
            //1.左孩子和有孩子都为空的情况，说明到达了叶子节点，直接返回1即可
            if (root.left == null && root.right == null) {
                return 1;
            }

            //2.如果左孩子和由孩子其中一个为空，那么需要返回比较大的那个孩子的深度
            int m1 = minDepth1(root.left);
            int m2 = minDepth1(root.right);

            //这里其中一个节点为空，说明m1和m2有一个必然为0，所以可以返回m1 + m2 + 1;

            if (root.left==null || root.right == null){
                return m1+m2+1;
            }
            //3.最后一种情况，也就是左右孩子都不为空，返回最小深度+1即可

            return Math.min(m1,m2) + 1;
        }

        public int minDepth2(Node root) {
            // 基本情况 dp[null] = 0 ，dp[叶子] = 1
            if (root == null) return 0;
            if (root.left == null && root.right == null) return 1;

            // 状态转移方程
            //              / dp[右孩子] + 1 ，左孩子为null
            // dp[非叶子] =    dp[左孩子] + 1 ，右孩子为null
            //              \ min(dp[左孩子], dp[右孩子]) + 1 ，左右孩子都不为null
            if (root.left == null) return minDepth2(root.right) + 1;
            if (root.right == null) return minDepth2(root.left) + 1;
            return Math.min(minDepth2(root.left), minDepth2(root.right)) + 1;
        }

        // depth 已走过的路径
        // root 当前状态（dp数组的维）
        private int minDepth(Node root, int depth) {
            // 最小子状态
            if (root == null) return depth - 1;
            if (root.left == null && root.right == null) return depth;

            // 由状态变量得出选择列表
            // 该层调用返回，相当于回溯，因为上层的depth比该层小1
            if (root.left == null) return minDepth(root.right, depth + 1);
            if (root.right == null) return minDepth(root.left, depth + 1);
            return Math.min(minDepth(root.left, depth + 1), minDepth(root.right, depth + 1));
        }


    }
    // leetcode submit region end(Prohibit modification and deletion)

}
