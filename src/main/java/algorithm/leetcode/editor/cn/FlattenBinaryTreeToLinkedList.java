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
public class FlattenBinaryTreeToLinkedList{
      public static void main(String[] args) {
           Solution solution = new FlattenBinaryTreeToLinkedList().new Solution();
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
        if (root == null)
            return;
        LinkedList<TreeNode> res = new LinkedList<>();
        //前序遍历二叉树
        dfs(root,res);
        TreeNode head = res.removeFirst();
        head.left = null;
        //遍历链表，将链表中的TreeNode节点前后串联起来
        while (res.size() > 0) {
            TreeNode tmp = res.removeFirst();
            tmp.left = null;
            head.right = tmp;
            head=head.right;
        }
    }

    //前序遍历整棵二叉树，并将遍历的结果放到数组中
    void dfs(TreeNode root, List<TreeNode> res) {
        if(root==null) {
            return;
        }
        res.add(root);
        dfs(root.left,res);
        dfs(root.right,res);
    }

}
//leetcode submit region end(Prohibit modification and deletion)

}