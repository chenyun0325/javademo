package algorithm.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenyun on 2020/5/1.
 *
 * https://blog.csdn.net/qq_43255303/article/details/89416512
 */
public class BinaryTree {

    private static List<Node> nodeList = new ArrayList<>();

    static class Node{
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

    public static void createBinTree(int[] arr){
        for (int item : arr) {
            nodeList.add(new Node(item));
        }
        int length = arr.length;
        // 对前lastParentIndex-1个父节点按照父节点与孩子节点的数学关系建立二叉树
        for (int parentIndex = 0;parentIndex<length/2 -1; parentIndex++){
            nodeList.get(parentIndex).left= nodeList.get(2*parentIndex+1);
            nodeList.get(parentIndex).right=nodeList.get(2*parentIndex+2);
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

    public static void preOrderTraverse(Node root){
        if (root == null){
            return;
        }
        System.out.println(root.val);
        preOrderTraverse(root.left);
        preOrderTraverse(root.right);
    }

    public static void inOrderTraverse(Node root){
        if (root == null){
            return;
        }
        inOrderTraverse(root.left);
        System.out.println(root.val);
        inOrderTraverse(root.right);
    }

    public static void postOrderTraverse(Node root){
        if (root == null){
            return;
        }
        postOrderTraverse(root.left);
        postOrderTraverse(root.right);
        System.out.println(root.val);
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8,9};
        createBinTree(arr);
        Node root = nodeList.get(0);
        System.out.println("pre------------");
        preOrderTraverse(root);
        System.out.println("in--------");
        inOrderTraverse(root);
        System.out.println("post------------");
        postOrderTraverse(root);
    }
}
