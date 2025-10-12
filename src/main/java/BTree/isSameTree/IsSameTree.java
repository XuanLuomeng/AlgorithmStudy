package BTree.isSameTree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author LuoXuanwei
 * @version 1.0
 * @description 相同的树
 * @date 2025/10/12 15:52
 * <p>
 * 给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。
 * <p>
 * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 */
public class IsSameTree {
    public static void main(String[] args) {

    }

    // 思路: 广度遍历
    public boolean isSameTreeTwo(TreeNode p, TreeNode q) {
        // 两个树均为空，则返回true；一个树为空，一个树不为空，则返回false
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null) {
            return false;
        }

        Queue<TreeNode> queueP = new LinkedList<>();
        Queue<TreeNode> queueQ = new LinkedList<>();
        queueQ.offer(q);
        queueP.offer(p);

        // 当队列不为空时，进行比较，直到有一个队列为空
        while (!queueP.isEmpty() && !queueQ.isEmpty()) {
            int size = queueP.size();

            for (int i = 0; i < size; i++) {
                TreeNode nodeP = queueP.poll();
                TreeNode nodeQ = queueQ.poll();

                // 节点值不相等，返回false
                if (nodeP.val != nodeQ.val) {
                    return false;
                }

                // 两个树节点只要有一个存在左/右子节点就需要进行判断处理
                // 第一个判断能够保证两个树节点至少有一个存在左/右节点
                // 第二个判断假如有个树节点的左/右节点不存在，结合第一个判断说明两个树节点左/右节点不相等
                // 通过前两个判断说明两个树节点都存在左/右节点，则将两个树节点的左/右节点加入对应的队列
                if (nodeP.left != null || nodeQ.left != null) {
                    if (nodeP.left == null || nodeQ.left == null) {
                        return false;
                    }
                    queueP.offer(nodeP.left);
                    queueQ.offer(nodeQ.left);
                }

                if (nodeP.right != null || nodeQ.right != null) {
                    if (nodeP.right == null || nodeQ.right == null) {
                        return false;
                    }
                    queueP.offer(nodeP.right);
                    queueQ.offer(nodeQ.right);
                }
            }
        }

        // 最终两个队列都为空则两个二叉树相同
        return queueP.isEmpty() && queueQ.isEmpty();
    }

    // 思路: 深度遍历
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // 递归终止条件: 两个节点都为空，节点相同，返回true
        if (p == null && q == null) {
            return true;
        }

        // 递归终止条件: 一个节点为空，两个节点不同，返回false
        if (p == null || q == null) {
            return false;
        }

        // 递归条件: 节点值相同，并且左右子树相同
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
