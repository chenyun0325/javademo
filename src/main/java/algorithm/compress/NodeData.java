package algorithm.compress;

import java.util.Arrays;
import java.util.stream.Collectors;

public class NodeData implements Comparable<NodeData> {
    /**
     * 存放数据（字符）本身，比如'a' =>97 '' =>32
     */
    Byte data;
    /**
     * 权值，表示字符出现的次数
     */
    int weight;

    NodeData left;

    NodeData right;

    public NodeData(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(NodeData o) {
        return this.weight-o.weight;
    }

    @Override
    public String toString() {
        return "NodeData{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    public void preOrder(){
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null){
            this.right.preOrder();
        }
    }

    
}
