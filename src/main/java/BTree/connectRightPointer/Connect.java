package BTree.connectRightPointer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author LuoXuanwei
 * @version 1.0
 * @description 填充每个节点的下一个右侧节点指针 II
 * @date 2025/10/17 14:11
 * <p>
 * 给定一个二叉树：
 * <p>
 * struct Node {
 * int val;
 * Node *left;
 * Node *right;
 * Node *next;
 * }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL 。
 * <p>
 * 初始状态下，所有 next 指针都被设置为 NULL 。
 */
public class Connect {
    public static void main(String[] args) {

    }

    /**
     * 思路: 广度优先搜索
     * 1、创建一个队列存储当前层的节点
     * 2、遍历队列中的节点，将节点的左右子节点加入临时队列并设置节点的 next 指针为队列中的下一个节点
     * 3、将临时队列中的节点加入队列
     * 4、重复步骤2、3、4，直到队列为空
     * 5、返回根节点
     * @param root
     * @return
     */
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }

        // 创建队列
        Queue<Node> queue = new LinkedList<>();
        // 将根节点加入队列
        queue.offer(root);

        // 当队列为空时，结束循环
        while (!queue.isEmpty()) {
            // 创建临时队列
            Queue<Node> temp = new LinkedList<>();

            // 遍历队列中的节点，将节点的左右子节点加入临时队列并设置节点的 next 指针为队列中的下一个节点
            while (!queue.isEmpty()) {
                Node node = queue.poll();

                if (node.left != null) {
                    temp.offer(node.left);
                }

                if (node.right != null) {
                    temp.offer(node.right);
                }

                // 设置节点的 next 指针为队列中的下一个节点(假如队列为空 peek() 也能正常返回 null)
                node.next = queue.peek();
            }

            // 将临时队列中的节点加入队列中
            queue = temp;
        }

        return root;
    }
}
