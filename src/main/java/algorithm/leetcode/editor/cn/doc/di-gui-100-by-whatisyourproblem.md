### 解题思路
题目需要注意的地方是严格大于和小于 搜索树一般不严格
中序遍历之后再比较的方法需要完整的遍历一遍再比较时间复杂的更高
递归思路：
每个节点带着最大值最小值参加遍历
在最大值和最小值分别不为null的情况下比较 满足条件节点值严格大于最小值严格小于最大值时递归下一层节点进行比较   
递归下一层节点：左节点的最大值是当前节点值 最小值继承当前节点最小值 右节点最小值为当前节点值最大值继承当前节点最大值
设置递归返回的条件 flag==false 可以避免在已经确定flase的情况下任然继续遍历
### 代码

```java
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
    
    Boolean flag = true;

    public boolean isValidBST(TreeNode root) {
        helper(root,null,null);
        return flag;
    }

    public void helper(TreeNode root,Integer max , Integer min){
        
        if (flag==false||root==null)
            return;
        
        if ((max!=null&&root.val>=max)||(min!=null&&root.val<=min)){
            flag=false;
            return ;
        }
        
        helper(root.left,root.val,min);
        helper(root.right,max,root.val);

    }
}
```