//给定一个二叉树，原地将它展开为一个单链表。 
//
// 
//
// 例如，给定二叉树 
//
//     1
//   / \
//  2   5
// / \   \
//3   4   6 
//
// 将其展开为： 
//
// 1
// \
//  2
//   \
//    3
//     \
//      4
//       \
//        5
//         \
//          6 
// Related Topics 树 深度优先搜索

package algorithm.leetcode.editor.cn;

import com.thare.algorithm.tree.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/solution/dong-hua-yan-shi-si-chong-jie-fa-114-er-cha-shu-zh/
 * https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/solution/114-er-cha-shu-zhan-kai-wei-lian-biao-by-ming-zhi-/
 */
public class FlattenBinaryTreeToLinkedList_1 {
      public static void main(String[] args) {
           Solution solution = new FlattenBinaryTreeToLinkedList_1().new Solution();
      }
      //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public void flatten(TreeNode root) {
        if(root == null){
            return ;
        }
        //将根节点的左子树变成链表
        flatten(root.left);
        //将根节点的右子树变成链表
        flatten(root.right);
        TreeNode temp = root.right;
        //把树的右边换成左边的链表
        root.right = root.left;
        //记得要将左边置空
        root.left = null;
        //找到树的最右边的节点
        while(root.right != null) root = root.right;
        //把右边的链表接到刚才树的最右边的节点
        root.right = temp;
    }


}
//leetcode submit region end(Prohibit modification and deletion)

}