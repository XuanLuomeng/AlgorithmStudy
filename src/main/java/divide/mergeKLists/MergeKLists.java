package divide.mergeKLists;

/**
 * @author LuoXuanwei
 * @version 1.0
 * @description 合并 K 个升序链表
 * @date 2025/11/18 18:54
 * <p>
 * 给你一个链表数组，每个链表都已经按升序排列。
 * <p>
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 */
public class MergeKLists {
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
     * 合并k个已排序的链表
     *
     * @param lists 包含k个已排序链表的数组，每个链表都按升序排列
     * @return 返回合并后的升序链表，如果输入数组为空或null则返回null
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists != null && lists.length != 0) {
            return merge(lists, 0, lists.length - 1);
        } else {
            return null;
        }
    }

    /**
     * 使用分治法递归合并链表数组中指定范围内的链表
     *
     * @param lists 链表数组
     * @param left  合并范围的左边界（包含）
     * @param right 合并范围的右边界（包含）
     * @return 返回合并后的新链表头节点
     */
    private ListNode merge(ListNode[] lists, int left, int right) {
        // 递归终止条件：当左右边界相等时，直接返回该位置的链表
        if (left == right) {
            return lists[left];
        }

        // 分治：将链表数组分成两半分别处理
        int mid = (left + right) / 2;
        ListNode leftNode = merge(lists, left, mid);
        ListNode rightNode = merge(lists, mid + 1, right);

        // 合并两个已排序的链表
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (leftNode != null && rightNode != null) {
            if (leftNode.val < rightNode.val) {
                cur.next = leftNode;
                leftNode = leftNode.next;
            } else {
                cur.next = rightNode;
                rightNode = rightNode.next;
            }
            cur = cur.next;
        }

        // 将剩余的节点连接到结果链表末尾
        cur.next = leftNode != null ? leftNode : rightNode;

        return dummy.next;
    }

}
