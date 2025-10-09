package linked.deleteDuplicates;

/**
 * @author LuoXuanwei
 * @version 1.0
 * @description 删除排序链表中的重复元素 II
 * @date 2025/10/9 15:24
 *
 * 给定一个已排序的链表的头 head ， 删除原始链表中所有重复数字的节点，只留下不同的数字 。返回 已排序的链表 。
 *
 * 提示：
 * 链表中节点数目在范围 [0, 300] 内
 * -100 <= Node.val <= 100
 * 题目数据保证链表已经按升序 排列
 */
public class DeleteDuplicates {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next.next = new ListNode(5);

        ListNode res = deleteDuplicates(head);
        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }

    /**
     * 思路:双指针，前后节点相同通过跳过指针删除
     *
     * @param head
     * @return
     */
    public static ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0, head);
        // pre 指针所在的节点以及之前的节点的 val 值均不相同
        ListNode pre = dummy;
        ListNode cur = head;

        while (cur != null) {
            // 跳过重复节点，cur 指针最终停留在重复 val 的最后一个节点上
            while (cur != null && cur.next != null && cur.val == cur.next.val) {
                cur = cur.next;
            }

            if (pre.next == cur) {
                // 假如 pre 指针的下一个节点是 cur 指针所在节点
                // 说明 cur 没有遇到 val 重复的节点，pre 需要将 cur 节点保存下来
                pre = pre.next;
            }else {
                // pre 指针的下一个节点不是 cur 指针所在节点
                // 说明 cur 遇到 val 重复的节点，pre.next 需要跳过这些重复的节点
                pre.next = cur.next;
            }

            cur = cur.next;
        }

        return dummy.next;
    }
}
