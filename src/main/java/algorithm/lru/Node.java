package algorithm.lru;

public class Node {
    /**
     * 健
     */
    Object key;

    Object value;

    Node pre;

    Node next;

    public Node(Object key, Object value) {
        this.key = key;
        this.value = value;
    }
}
