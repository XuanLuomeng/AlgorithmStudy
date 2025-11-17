package divide.sortList;

/**
 * @author LuoXuanwei
 * @version 1.0
 * @description 排序链表
 * @date 2025/11/17 20:29
 * <p>
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 */
public class SortList {
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * 对链表进行排序
     * 使用归并排序算法实现链表排序
     *
     * @param head 链表的头节点
     * @return 排序后的链表头节点
     */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 使用快慢指针找到链表中点
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 分割链表为两部分
        ListNode mid = slow.next;
        slow.next = null;
        // 递归排序左右两部分
        ListNode left = sortList(head);
        ListNode right = sortList(mid);
        // 合并已排序的两部分
        return merge(left, right);
    }

    /**
     * 合并两个已排序的链表
     *
     * @param l1 第一个已排序链表的头节点
     * @param l2 第二个已排序链表的头节点
     * @return 合并后已排序链表的头节点
     */
    private ListNode merge(ListNode l1, ListNode l2) {
        // 创建虚拟头节点简化操作
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;

        // 比较两个链表的节点值，按顺序连接较小的节点
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }

        // 连接剩余的节点(只要有一个链表已经遍历空了，那么另一个链表一定是最后面的那一串)
        cur.next = l1 != null ? l1 : l2;

        return dummy.next;
    }

}
