package linked.rotateRight;

/**
 * @author LuoXuanwei
 * @version 1.0
 * @description 旋转链表
 * @date 2025/10/9 16:00
 *
 * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
 */
public class RotateRight {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        ListNode res = rotateRight(head, 2);

        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }

    /**
     * 思路:创建一个循环链表，并获取旋转的起始位置，移动指针，获取旋转后的链表，然后断开循环
     * @param head
     * @param k
     * @return
     */
    public static ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }

        // 获取链表长度
        ListNode cur = head;
        int n = 1;
        while (cur.next != null) {
            cur = cur.next;
            n++;
        }

        // 创建一个循环链表
        cur.next = head;

        // 获取旋转的起始位置
        k = k % n;
        k = n - k;

        // 移动指针
        while (k > 0) {
            cur = cur.next;
            k--;
        }

        // 获取旋转后的链表
        head = cur.next;

        // 断开循环
        cur.next = null;

        return head;
    }
}
