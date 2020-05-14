跟后序遍历的那道差不多,改了一下
**迭代**
```java
class Solution {
    LinkedList<Node> temp=new LinkedList<>();
    List<Integer> res=new ArrayList<Integer>();
    public List<Integer> preorder(Node root){
        if (root==null) return res;
        temp.add(root);
        while (!temp.isEmpty()){
            Node node = temp.pollLast();
            res.add(node.val);
            for (int i = node.children.size()-1; i >=0 ; i--) {
                if (node.children.get(i)!=null){
                    temp.add(node.children.get(i));
                }
            }
        }
        return res;
    }
}
```
**递归**
```java
class Solution {
    List<Integer> res=new ArrayList<Integer>();
    public List<Integer> preorder(Node root) {
        helper(root);
        return res;
    }
    public void helper(Node root){
        if (root==null) return;
        res.add(root.val);
        for (int i = 0; i <root.children.size() ; i++) {
            helper(root.children.get(i));
        }
    }
}
```