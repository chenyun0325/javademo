
🙋‍ 打卡～

## 思路分析

判断二叉树是否对称
- 若 root == null, 直接返回 true；

- 否则，判断 root.left 与 root.right 这两棵子树是否对称：

     1. 判断 root.left 与 root.right 这两个节点的值是否相等
     
     2. 判断 root.left 的左子树与 root.right 的右子树是否对称
     
     3. 判断 root.left 的右子树与 root.right 的左子树是否对称



---
## 方法一：递归

```java
class Solution {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        // 比较 root.left 与 root.right 这两棵子树是否对称。
        return cmp(root.left, root.right);
    }

    private boolean cmp(TreeNode node1, TreeNode node2) {
        // 首先比较 node1 与 node2 这两个节点的值是否相等
        if (node1 == null && node2 == null) {
            return true;
        }
        if (node1 == null || node2 == null || node1.val != node2.val) {
            return false;
        }
        // 再比较 node1 的左子树与 node2 的右子树是否对称，node1 的右子树与 node2 的左子树是否对称。
        return cmp(node1.left, node2.right) && cmp(node1.right, node2.left);
    }
}
```

复杂度分析：

- 时间复杂度 *O(N)*

- 空间复杂度 *O(N)*

---

## 方法二：迭代

思路和上面的递归解法是一样的。

``` Java
class Solution {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root.left);
        queue.offer(root.right);

        while (!queue.isEmpty()) {
            // 每次出队两个节点 node1 和 node2
            TreeNode node1 = queue.poll();
            TreeNode node2 = queue.poll();
            // 首先比较 node1 与 node2 这两个节点的值是否相等
            if (node1 == null && node2 == null) {
                continue;
            }
            if (node1 == null || node2 == null || node1.val != node2.val) {
                return false;
            }
            // 再将 node1 的左节点与 node2 的右节点一起入队（以便两节点一起出队，进行比较）
            queue.offer(node1.left);
            queue.offer(node2.right);
            // 再将 node1 的右节点与 node2 的左节点一起入队（以便两节点一起出队，进行比较）
            queue.offer(node1.right);
            queue.offer(node2.left);
        }

        return true;
    }
}
```

复杂度分析：

- 时间复杂度 *O(N)*

- 空间复杂度 *O(N)*

---
趁热打铁再来三道「二叉树递归」练习题 💪
1. [100. 相同的树](https://leetcode-cn.com/problems/same-tree/)
2. [110. 平衡二叉树](https://leetcode-cn.com/problems/balanced-binary-tree/)
3. [226. 翻转二叉树](https://leetcode-cn.com/problems/invert-binary-tree/)

---

公众号【[甜姨的奇妙冒险](https://sweeetiee-1256505723.cos.ap-beijing.myqcloud.com/sweetiee.jpeg)】
知乎专栏【[甜姨的力扣题解](https://zhuanlan.zhihu.com/c_1224355183452614656)】
快来关注我吧 ٩(●˙ε˙●)۶

 [miaodong.jpeg](https://pic.leetcode-cn.com/18cb9fdfcfd9652d88342e6b7341d42a5741985765ff51e3254b38f20f2e8c5c-miaodong.jpeg)

