package BTree.flatten;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author LuoXuanwei
 * @version 1.0
 * @description 二叉树展开为链表
 * @date 2025/10/18 17:28
 * <p>
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
 * <p>
 * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
 * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
 *
 * 思路:
 * 1、先序遍历二叉树，将节点依次加入队列中
 * 2、将队列中的节点依次展开为链表
 */
public class Flatten {
    private Queue<TreeNode> queue;

    public static void main(String[] args) {

    }

    public void flatten(TreeNode root) {
        queue = new LinkedList<>();

        // 先序遍历二叉树，将节点依次加入队列中
        buildQueue(root);

        // 将队列中的节点依次展开为链表
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            // 如果队列中还有节点，则将当前节点的 right 指针指向队列中的下一个节点，并设置当前节点的 left 指针为 null
            if (!queue.isEmpty()) {
                node.right = queue.peek();
                node.left = null;
            }
        }
    }

    /**
     * 1、中序遍历将节点加入到队列
     * 2、将当前节点加入队列中
     * 3、先处理左子树，再处理右子树
     * 4、递归 2 3 4 步骤
     * 5、当节点为空时，递归结束
     * @param root
     */
    public void buildQueue(TreeNode root) {
        if (root == null) {
            return;
        }

        queue.add(root);

        buildQueue(root.left);
        buildQueue(root.right);
    }
}
