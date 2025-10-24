package BTreeLevelOrderTraversal;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LuoXuanwei
 * @version 1.0
 * @description 二叉树的右视图
 * @date 2025/10/24 22:42
 * <p>
 * 给定一个二叉树的 根节点 root，想象自己站在它的右侧，
 * 按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 */
public class RightSideView {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 获取二叉树的右视图节点值列表
     * 使用广度优先搜索（BFS）逐层遍历二叉树，在每层中将最右边的节点值加入结果列表。
     *
     * @param root 二叉树的根节点
     * @return 包含从右侧可见节点值的列表，按从上到下的顺序排列
     */
    public List<Integer> rightSideView(TreeNode root) {
        // 如果根节点为空，则直接返回空列表
        if (root == null) {
            return new ArrayList<>();
        }

        // 存储最终结果的列表
        List<Integer> res = new ArrayList<>();

        // 当前层级待处理的节点队列
        List<TreeNode> queue = new ArrayList<>();

        queue.add(root);

        // 层序遍历整个二叉树
        while (!queue.isEmpty()) {
            int size = queue.size();
            // 临时存储下一层级节点的列表
            List<TreeNode> temp = new ArrayList<>();

            // 遍历当前层级的所有节点，并收集下一层级的节点
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.get(i);

                if (node.left != null) {
                    temp.add(node.left);
                }
                if (node.right != null) {
                    temp.add(node.right);
                }
            }

            // 将当前层级最后一个节点的值添加到结果中（即右视图看到的节点）
            res.add(queue.get(size - 1).val);

            // 更新队列为下一层级的节点
            queue = temp;
        }

        return res;
    }
}
