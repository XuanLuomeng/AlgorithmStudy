package linked.LRUCache;

/**
 * @author LuoXuanwei
 * @version 1.0
 * @description TODO
 * @date 2025/10/11 23:20
 */
public class Node {
    private int key;
    private int value;
    private Node pre;
    private Node next;

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getPre() {
        return pre;
    }

    public void setPre(Node pre) {
        this.pre = pre;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
