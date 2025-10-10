package linked.partition;

/**
 * @author LuoXuanwei
 * @version 1.0
 * @description 分割链表
 * @date 2025/10/10 19:53
 *
 * 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
 *
 * 你应当 保留 两个分区中每个节点的初始相对位置。
 */
public class Partition {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(4);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(2);

        ListNode res = partition(head, 3);
        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }

    /**
     * 思路:比 x 大的节点放一起，比 x 小的节点放一起，最后两个进行连接即可
     * @param head
     * @param x
     * @return
     */
    public static ListNode partition(ListNode head, int x) {
        ListNode small = new ListNode(0);
        ListNode smallHead = small;
        ListNode big = new ListNode(0);
        ListNode bigHead = big;

        ListNode cur = head;

        while (cur != null){
            if (cur.val < x){
                small.next = cur;
                small = small.next;
            }else {
                big.next = cur;
                big = big.next;
            }
            cur = cur.next;
        }

        big.next = null;
        small.next = bigHead.next;

        return smallHead.next;
    }
}
