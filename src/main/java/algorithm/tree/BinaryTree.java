package algorithm.tree;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by chenyun on 2020/5/1.
 *
 * https://blog.csdn.net/qq_43255303/article/details/89416512
 */
public class BinaryTree {

    public static List<Node> nodeList = new ArrayList<>();

    static int index = 0;

    public static class Node {
        public Node left;
        public Node right;
        public int val;

        public Node(Node left, Node right, int val) {
            this.left = left;
            this.right = right;
            this.val = val;
        }

        public Node(int val) {
            this.val = val;
        }
    }

    public static void createBinTree(int[] arr) {
        for (int item : arr) {
            nodeList.add(new Node(item));
        }
        int length = arr.length;
        // 对前lastParentIndex-1个父节点按照父节点与孩子节点的数学关系建立二叉树
        for (int parentIndex = 0; parentIndex < length / 2 - 1; parentIndex++) {
            nodeList.get(parentIndex).left = nodeList.get(2 * parentIndex + 1);
            nodeList.get(parentIndex).right = nodeList.get(2 * parentIndex + 2);
        }
        // 最后一个父节点:因为最后一个父节点可能没有右孩子，所以单独拿出来处理
        int lastParentIndex = arr.length / 2 - 1;
        // 左孩子
        nodeList.get(lastParentIndex).left = nodeList.get(lastParentIndex * 2 + 1);
        // 右孩子,如果数组的长度为奇数才建立右孩子
        if (arr.length % 2 == 1) {
            nodeList.get(lastParentIndex).right = nodeList.get(lastParentIndex * 2 + 2);
        }

    }

    public static void preOrderTraverse(Node root) {
        if (root == null) {
            return;
        }
        System.out.println(root.val);
        preOrderTraverse(root.left);
        preOrderTraverse(root.right);
    }

    public static void inOrderTraverse(Node root) {
        if (root == null) {
            return;
        }
        inOrderTraverse(root.left);
        System.out.println(root.val);
        inOrderTraverse(root.right);
    }

    /**
     * https://www.cnblogs.com/tianzeng/p/10269202.html
     *
     * 
     * TODO k处理错误 参考：https://blog.csdn.net/csdanteng/article/details/77754327
     * 
     * @param root
     * @param k
     * @return
     */
    public static Node KthNode(Node root, int[] k) {

        Node target = null;

        if (root.left != null) {
            target = KthNode(root.left, k);
        }
        // 从叶节点返回的值为nullptr，依次向父节点返回该值，直到k==1，target值改变为当前节点的值
        // 找到该节点后返回即可无需再次遍历
        if (target == null) {
            k[1]++;
            if (k[0] == k[1]) {
                target = root;
            }
        }
        if (target == null && root.right != null) {

            target = KthNode(root.right, k);
        }

        return target;
    }

    public static Node KthNode(Node root, int[] k, Node[] res) {
        if (root == null || res[0] != null) {
            return null;
        }
        // if (res != null){
        // return res;
        // }
        KthNode(root.left, k, res);

        k[1]++;
        if (k[0] == k[1]) {
            res[0] = root;
        }
        KthNode(root.right, k, res);
        return null;
    }

    public static Node KthNodeV1(Node root, int k) {
        if (root != null) {
            Node node = KthNodeV1(root.left, k);
            if (node != null) {
                return node;
            }
            index++;
            if (index == k) {
                return root;
            }
            node = KthNodeV1(root.right, k);
            if (node != null) {
                return node;
            }
        }
        return null;
    }


    public static void inOrderTraverseStack(Node head) {
        if (head != null) {
            Stack<Node> stack = new Stack<>();
            while (!stack.isEmpty() || head != null) {
                if (head != null) {
                    stack.push(head);
                    head = head.left;
                } else {
                    head = stack.pop();
                    System.out.println(head.val);
                    head = head.right;
                }
            }
        }
    }

    public static void postOrderTraverse(Node root) {
        if (root == null) {
            return;
        }
        postOrderTraverse(root.left);
        postOrderTraverse(root.right);
        System.out.println(root.val);
    }

    public static void preOrderTraverseStack(Node root) {
        if (root != null) {
            Stack<Node> stack = new Stack<>();
            stack.add(root);
            while (!stack.isEmpty()) {
                Node head = stack.pop();
                System.out.println(head.val);
                /**
                 * 栈FILO所以先right后left
                 */
                if (head.right != null) {
                    stack.push(head.right);
                }
                if (head.left != null) {
                    stack.push(head.left);
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        createBinTree(arr);
        Node root = nodeList.get(0);
        int[] count = new int[2];
        count[0] = 6;
        Node node = KthNode(root, count);
        System.out.println(node.val);
        System.out.println("pre------------");
        preOrderTraverse(root);
        System.out.println("stack-pre------------");
        preOrderTraverseStack(root);
        System.out.println("in--------");
        inOrderTraverse(root);
        System.out.println("stack-in------------");
        inOrderTraverseStack(root);
        System.out.println("post------------");
        postOrderTraverse(root);
    }
}
