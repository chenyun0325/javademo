//给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。 
//
// 
//
// 示例 : 
//给定二叉树 
//
//           1
//         / \
//        2   3
//       / \     
//      4   5    
// 
//
// 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。 
//
// 
//
// 注意：两结点之间的路径长度是以它们之间边的数目表示。 
// Related Topics 树

package algorithm.leetcode.editor.cn;

import com.thare.algorithm.tree.TreeNode;

/**
 * https://leetcode-cn.com/problems/diameter-of-binary-tree/solution/er-cha-shu-de-zhi-jing-by-leetcode-solution/
 */
public class DiameterOfBinaryTree{
      public static void main(String[] args) {
           Solution solution = new DiameterOfBinaryTree().new Solution();
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
    int maxd=0;
    public int diameterOfBinaryTree(TreeNode root) {
        depth(root);
        return maxd;
    }

    public int depth(TreeNode node){
        // 访问到空节点了，返回0
      if (node == null) {
          return 0;
      }
      int L = depth(node.left); // 左儿子为根的子树的深度
      int R = depth(node.right);// 右儿子为根的子树的深度
      maxd=Math.max(maxd,L+R); //将每个节点最大直径(左子树深度+右子树深度)当前最大值比较并取大者
      return Math.max(L,R)+1;// 返回该节点为根的子树的深度
    }

}
//leetcode submit region end(Prohibit modification and deletion)

}