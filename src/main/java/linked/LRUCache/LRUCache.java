package linked.LRUCache;

import java.util.HashMap;
import java.util.Map;

/**
 * @author LuoXuanwei
 * @version 1.0
 * @description LRC缓存
 * @date 2025/10/11 22:55
 *
 * 请你设计并实现一个满足  LRU (最近最少使用) 缓存 约束的数据结构。
 * 实现 LRUCache 类：
 * LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 key-value 。如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
 * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
 *
 * 思路:
 * 双向链表 + 哈希表
 * 链表用于确认数据的使用情况，哈希表用于定位数据节点在链表中的位置
 * 将缓存的数据结构设计成链表，链表头是最近使用的，链表尾是最久未使用的。
 * 表头是一个哨兵节点
 */
public class LRUCache {
    // 缓存容量
    private final int capacity;
    // 哨兵节点
    private final Node dummy = new Node(0, 0);
    // 缓存数据
    private final Map<Integer, Node> map = new HashMap<>();

    public LRUCache(int capacity) {
        this.capacity = capacity;
        dummy.setNext(dummy);
        dummy.setPre(dummy);
    }

    public int get(int key) {
        // 获取节点(假如节点存在该方法会将该节点放置到链表头部)
        Node node = getNode(key);
        return node == null ? -1 : node.getValue();
    }

    public void put(int key, int value) {
        // 获取节点(假如节点存在该方法会将该节点放置到链表头部)
        Node node = getNode(key);
        if (node != null){
            node.setValue(value);
            return;
        }

        node = new Node(key, value);
        map.put(key, node);
        putToFront(node);

        // 缓存已满
        if (map.size() > capacity){
            // 删除链表尾部节点
            Node last = dummy.getPre();
            remove(last);
            map.remove(last.getKey());
        }
    }

    public Node getNode(int key){
        if (!map.containsKey(key)){
            return null;
        }
        Node node = map.get(key);
        // 将节点从链表中删除然后添加到链表头部
        remove(node);
        putToFront(node);

        return node;
    }

    public void remove(Node node){
        // 删除节点
        node.getPre().setNext(node.getNext());
        node.getNext().setPre(node.getPre());
    }

    public void putToFront(Node node){
        // 为 node 设置前后置节点
        node.setNext(dummy.getNext());
        node.setPre(dummy);
        // 为 dummy 设置新的下一个节点
        dummy.setNext(node);
        // 为 node.getNext() 设置新的前一个节点
        node.getNext().setPre(node);
    }
}
