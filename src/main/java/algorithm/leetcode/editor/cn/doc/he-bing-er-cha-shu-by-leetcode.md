#### 方法一：递归

我们可以对这两棵树同时进行前序遍历，并将对应的节点进行合并。在遍历时，如果两棵树的当前节点均不为空，我们就将它们的值进行相加，并对它们的左孩子和右孩子进行递归合并；如果其中有一棵树为空，那么我们返回另一颗树作为结果；如果两棵树均为空，此时返回任意一棵树均可（因为都是空）。

  [1000](https://pic.leetcode-cn.com/Figures/617/617_Merge_Trees_RecursionSlide1.PNG)  [1000](https://pic.leetcode-cn.com/Figures/617/617_Merge_Trees_RecursionSlide2.PNG)  [1000](https://pic.leetcode-cn.com/Figures/617/617_Merge_Trees_RecursionSlide3.PNG)  [1000](https://pic.leetcode-cn.com/Figures/617/617_Merge_Trees_RecursionSlide4.PNG)  [1000](https://pic.leetcode-cn.com/Figures/617/617_Merge_Trees_RecursionSlide5.PNG)  [1000](https://pic.leetcode-cn.com/Figures/617/617_Merge_Trees_RecursionSlide6.PNG)  [1000](https://pic.leetcode-cn.com/Figures/617/617_Merge_Trees_RecursionSlide7.PNG)  [1000](https://pic.leetcode-cn.com/Figures/617/617_Merge_Trees_RecursionSlide8.PNG)  [1000](https://pic.leetcode-cn.com/Figures/617/617_Merge_Trees_RecursionSlide9.PNG)  [1000](https://pic.leetcode-cn.com/Figures/617/617_Merge_Trees_RecursionSlide10.PNG)  [1000](https://pic.leetcode-cn.com/Figures/617/617_Merge_Trees_RecursionSlide11.PNG)  [1000](https://pic.leetcode-cn.com/Figures/617/617_Merge_Trees_RecursionSlide12.PNG)  [1000](https://pic.leetcode-cn.com/Figures/617/617_Merge_Trees_RecursionSlide13.PNG)  [1000](https://pic.leetcode-cn.com/Figures/617/617_Merge_Trees_RecursionSlide14.PNG)  [1000](https://pic.leetcode-cn.com/Figures/617/617_Merge_Trees_RecursionSlide15.PNG) 


```Java [sol1]
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null)
            return t2;
        if (t2 == null)
            return t1;
        t1.val += t2.val;
        t1.left = mergeTrees(t1.left, t2.left);
        t1.right = mergeTrees(t1.right, t2.right);
        return t1;
    }
}
```

**复杂度分析**

* 时间复杂度：*O(N)*，其中 *N* 是两棵树中节点个数的较小值。

* 空间复杂度：*O(N)*，在最坏情况下，会递归 *N* 层，需要 *O(N)* 的栈空间。

#### 方法二：迭代

我们也可以用迭代的方法解决这个问题。

我们首先把两棵树的根节点入栈，栈中的每个元素都会存放两个根节点，并且栈顶的元素表示当前需要处理的节点。在迭代的每一步中，我们取出栈顶的元素并把它移出栈，并将它们的值相加。随后我们分别考虑这两个节点的左孩子和右孩子，如果两个节点都有左孩子，那么就将左孩子入栈；如果只有一个节点有左孩子，那么将其作为第一个节点的左孩子；如果都没有左孩子，那么不用做任何事情。对于右孩子同理。

最后我们返回第一棵树的根节点作为答案。

  [1000](https://pic.leetcode-cn.com/Figures/617/617_Merge_Trees_StackSlide1.PNG)  [1000](https://pic.leetcode-cn.com/Figures/617/617_Merge_Trees_StackSlide2.PNG)  [1000](https://pic.leetcode-cn.com/Figures/617/617_Merge_Trees_StackSlide3.PNG)  [1000](https://pic.leetcode-cn.com/Figures/617/617_Merge_Trees_StackSlide4.PNG)  [1000](https://pic.leetcode-cn.com/Figures/617/617_Merge_Trees_StackSlide5.PNG)  [1000](https://pic.leetcode-cn.com/Figures/617/617_Merge_Trees_StackSlide6.PNG)  [1000](https://pic.leetcode-cn.com/Figures/617/617_Merge_Trees_StackSlide7.PNG)  [1000](https://pic.leetcode-cn.com/Figures/617/617_Merge_Trees_StackSlide8.PNG)  [1000](https://pic.leetcode-cn.com/Figures/617/617_Merge_Trees_StackSlide9.PNG)  [1000](https://pic.leetcode-cn.com/Figures/617/617_Merge_Trees_StackSlide10.PNG)  [1000](https://pic.leetcode-cn.com/Figures/617/617_Merge_Trees_StackSlide11.PNG)  [1000](https://pic.leetcode-cn.com/Figures/617/617_Merge_Trees_StackSlide12.PNG)  [1000](https://pic.leetcode-cn.com/Figures/617/617_Merge_Trees_StackSlide13.PNG)  [1000](https://pic.leetcode-cn.com/Figures/617/617_Merge_Trees_StackSlide14.PNG)  [1000](https://pic.leetcode-cn.com/Figures/617/617_Merge_Trees_StackSlide15.PNG) 

```Java [sol2]
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null)
            return t2;
        Stack < TreeNode[] > stack = new Stack < > ();
        stack.push(new TreeNode[] {t1, t2});
        while (!stack.isEmpty()) {
            TreeNode[] t = stack.pop();
            if (t[0] == null || t[1] == null) {
                continue;
            }
            t[0].val += t[1].val;
            if (t[0].left == null) {
                t[0].left = t[1].left;
            } else {
                stack.push(new TreeNode[] {t[0].left, t[1].left});
            }
            if (t[0].right == null) {
                t[0].right = t[1].right;
            } else {
                stack.push(new TreeNode[] {t[0].right, t[1].right});
            }
        }
        return t1;
    }
}
```

**复杂度分析**

* 时间复杂度：*O(N)*，其中 *N* 是两棵树中节点个数的较小值。

* 空间复杂度：*O(N)*，在最坏情况下，栈中会存放 *N* 个节点。