package BTree.maxDepth;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author LuoXuanwei
 * @version 1.0
 * @description 二叉树的最大深度
 * @date 2025/10/12 15:37
 *
 * 思路:
 * 深度优先算法/广度优先算法
 * 深度优先算法利用向下递归遍历二叉树，求最大深度
 * 广度优先算法利用队列遍历每一层二叉树，求最大深度
 */
public class MaxDepth {
    // 广度优先遍历
    public int maxDepthTwo(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int depth = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        // 每次循环将当前深度的节点从队列中删除，同时将他们的子节点加入队列，直到队列为空(当前深度的节点均没有子节点为止)
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {

                TreeNode node = queue.poll();

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            depth++;
        }

        return depth;
    }

    // 深度优先遍历
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}
