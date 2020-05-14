#### 方法一：递归

第一种解决方法是使用递归。这是经典的方法，直截了当。我们可以定义一个辅助函数来实现递归。

```Java [solution1]
class Solution {
    public List < Integer > inorderTraversal(TreeNode root) {
        List < Integer > res = new ArrayList < > ();
        helper(root, res);
        return res;
    }

    public void helper(TreeNode root, List < Integer > res) {
        if (root != null) {
            if (root.left != null) {
                helper(root.left, res);
            }
            res.add(root.val);
            if (root.right != null) {
                helper(root.right, res);
            }
        }
    }
}
```

**复杂度分析**

* 时间复杂度：*O(n)*。递归函数 ![T(n)=2\cdotT(n/2)+1 ](./p__T_n__=_2_cdot_T_n_2_+1_.png) 。
* 空间复杂度：最坏情况下需要空间*O(n)*，平均情况为![O(\logn) ](./p__O_log_n__.png) 。
<br />
<br />

#### 方法二：基于栈的遍历

本方法的策略与上衣方法很相似，区别是使用了栈。

下面是示例:

  [image.png](https://pic.leetcode-cn.com/e2b24c9eb433f8ba14c44891e11371dcb9bd8ee9f89d958802cfb502da7a94c2-image.png)  [image.png](https://pic.leetcode-cn.com/10eeee7184287966edfc1bdf42321d3cba925af54a8feca860e9fb4e5373ed4c-image.png)  [image.png](https://pic.leetcode-cn.com/8bccfa2a5634b386e55b07e960da58ec25d58f6aaf60ecbb1348f4e3e8124399-image.png)  [image.png](https://pic.leetcode-cn.com/4d772c2fb4729a5d7fd2435cce8773ee67395af8191cfc324eeab5def8dc52e1-image.png)  [image.png](https://pic.leetcode-cn.com/45b974ac01bb9e32983a9650f2fd94eb7c0cb85747d9a01c6921e3070b4d9999-image.png)  [image.png](https://pic.leetcode-cn.com/bf5ce43502f59ec4a910a092536aba5f8dc118823be4ccb15396e465bcdef4ee-image.png)  [image.png](https://pic.leetcode-cn.com/275d4b64a6c147129f6cb79a6d9fbd9774bbd3c54bcc4b5a349b249e6027cdf3-image.png)  [image.png](https://pic.leetcode-cn.com/12de88b354060944395f32020b0759c19283b9bae5d627c865fa0cd133de903c-image.png)  [image.png](https://pic.leetcode-cn.com/c46c24445c4a29991e5ab2d00d1bf85ce31b92697744820fb6ff4d51fa333c8c-image.png)  [image.png](https://pic.leetcode-cn.com/c2b19ba77b359e5879960495cd5df3bcb720fe629a16783caa38f326e92dc2ce-image.png)  [image.png](https://pic.leetcode-cn.com/b8bac4aabb1707852cce55886d4bd802cf3c2412f4201481f4e8b2937ad55ce1-image.png)  [image.png](https://pic.leetcode-cn.com/990fd38367aa7331054aa0408ba10e8fef69253e605e7f7df7e17761d46648be-image.png)  [image.png](https://pic.leetcode-cn.com/405b4be635eafeecdb78e2b73b2728ec7e56a0f9f8479416e63621feb1ac4239-image.png)  [image.png](https://pic.leetcode-cn.com/220f16e0a6c79e96566cef098ee159ce7e1dceff8fe342156049ad36a43aa83f-image.png) 

```Java [solution 2]
public class Solution {
    public List < Integer > inorderTraversal(TreeNode root) {
        List < Integer > res = new ArrayList < > ();
        Stack < TreeNode > stack = new Stack < > ();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            res.add(curr.val);
            curr = curr.right;
        }
        return res;
    }
}
```

**复杂度分析**

* 时间复杂度：*O(n)*。

* 空间复杂度：*O(n)*。
<br />
<br />

#### 方法三：莫里斯遍历


本方法中，我们使用一种新的数据结构：线索二叉树。方法如下：


>Step 1: 将当前节点current初始化为根节点
>
>Step 2: While current不为空，
>
>     若current没有左子节点
>
>         a. 将current添加到输出
>
>         b. 进入右子树，亦即, current = current.right
>
>     否则
>
>         a. 在current的左子树中，令current成为最右侧节点的右子节点
>
>         b. 进入左子树，亦即，current = current.left


举例而言:
```

          1
        /   \
       2     3
      / \   /
     4   5 6

```
首先，1 是根节点，所以将 current 初始化为 1。1 有左子节点 2，current 的左子树是

```
         2
        / \
       4   5
```
在此左子树中最右侧的节点是 5，于是将 current(1) 作为 5 的右子节点。令 current = cuurent.left (current = 2)。
现在二叉树的形状为:
```
         2
        / \
       4   5
            \
             1
              \
               3
              /
             6
```
对于 current(2)，其左子节点为4，我们可以继续上述过程 
```
        4
         \
          2
           \
            5
             \
              1
               \
                3
               /
              6
```
 由于 4 没有左子节点，添加 4 为输出，接着依次添加 2, 5, 1, 3 。节点 3 有左子节点 6，故重复以上过程。
最终的结果是 [4,2,5,1,6,3]。

了解更多细节请查阅
[线索二叉树](https://baike.baidu.com/item/%E7%BA%BF%E7%B4%A2%E4%BA%8C%E5%8F%89%E6%A0%91/10810037?fr=aladdin) 与
[莫里斯方法解析](https://stackoverflow.com/questions/5502916/explain-morris-inorder-tree-traversal-without-using-stacks-or-recursion)


```Java [solution 3]
class Solution {
    public List < Integer > inorderTraversal(TreeNode root) {
        List < Integer > res = new ArrayList < > ();
        TreeNode curr = root;
        TreeNode pre;
        while (curr != null) {
            if (curr.left == null) {
                res.add(curr.val);
                curr = curr.right; // move to next right node
            } else { // has a left subtree
                pre = curr.left;
                while (pre.right != null) { // find rightmost
                    pre = pre.right;
                }
                pre.right = curr; // put cur after the pre node
                TreeNode temp = curr; // store cur node
                curr = curr.left; // move cur to the top of the new tree
                temp.left = null; // original cur left be null, avoid infinite loops
            }
        }
        return res;
    }
}
```

**复杂度分析**

* 时间复杂度：*O(n)*。 想要证明时间复杂度是*O(n)*，最大的问题是找到每个节点的前驱节点的时间复杂度。乍一想，找到每个节点的前驱节点的时间复杂度应该是 ![O(n\logn) ](./p__O_nlog_n__.png) ，因为找到一个节点的前驱节点和树的高度有关。 
但事实上，找到所有节点的前驱节点只需要*O(n)* 时间。一棵 *n* 个节点的二叉树只有 *n-1* 条边，每条边只可能使用2次，一次是定位节点，一次是找前驱节点。
故复杂度为 *O(n)*。

* 空间复杂度：*O(n)*。使用了长度为 *n* 的数组。
