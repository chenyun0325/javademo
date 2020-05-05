package algorithm.tree;

/**
 * Created by chenyun on 2020/5/1.
 *
 * https://www.cnblogs.com/edisonchou/p/4793345.html
 *
 * https://blog.csdn.net/qq_37964547/article/details/80629461
 *
 * 构建二叉查找树 https://www.jianshu.com/p/0955f48a571d
 */
public class BinarySearchTree {

    private static Node root;

    static class Node {
        Node left;
        Node right;
        int val;

        public Node(Node left, Node right, int val) {
            this.left = left;
            this.right = right;
            this.val = val;
        }

        public Node(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
//        int[] arr = {10, 6, 8, 15, 13, 17, 11, 14};
        int[] arr = {1,2,3,4,5,6,7,8,9};
        for (int item : arr) {
            insert(item);
        }
        System.out.println(root);
    }


    public static void insert(int num) {
        // 新插入节点
        Node treeNode = new Node(num);
        if (root == null) {
            root = treeNode;
        } else {
            Node index = root;

            // 用来储存当前节点
            Node current;

            while (true) {
                current = index;
                if (current.val > num) {
                    if (current.left == null) {
                        current.left = treeNode;
                        break;
                    } else {
                        index = current.left;
                    }
                } else {
                    if (current.right == null) {
                        current.right = treeNode;
                        break;
                    } else {
                        index = current.right;
                    }
                }
            }
        }
    }
}
