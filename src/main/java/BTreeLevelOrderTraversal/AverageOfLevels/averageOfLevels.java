package BTreeLevelOrderTraversal.AverageOfLevels;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LuoXuanwei
 * @version 1.0
 * @description 二叉树的层平均值
 * @date 2025/10/25 13:42
 * <p>
 * 给定一个非空二叉树的根节点 root , 以数组的形式返回每一层节点的平均值。
 * 与实际答案相差 10-5 以内的答案可以被接受。
 */
public class averageOfLevels {
    /**
     * 二叉树节点定义
     */
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
     * 计算二叉树每层节点的平均值
     * 使用广度优先搜索(BFS)遍历二叉树，按层计算节点值的平均值
     *
     * @param root 二叉树的根节点
     * @return 包含每层节点平均值的列表
     */
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> res = new ArrayList<>();

        if (root == null) {
            return res;
        }

        // 使用列表模拟队列进行层次遍历(因为 jdk8 开始往后 Queue 和 Stack 两个类型注解被淘汰)
        List<TreeNode> queue = new ArrayList<>();

        queue.add(root);

        // 层次遍历二叉树
        while (!queue.isEmpty()) {
            // 临时列表存储下一层的节点
            List<TreeNode> temp = new ArrayList<>();
            // 当前层节点值的总和
            double sum = 0;

            // 遍历当前层的所有节点
            for (TreeNode node : queue){
                sum += node.val;
                // 将子节点加入临时列表
                if (node.left != null) {
                    temp.add(node.left);
                }
                if (node.right != null) {
                    temp.add(node.right);
                }
            }

            // 计算当前层的平均值并添加到结果列表
            res.add(sum / queue.size());

            // 更新队列为下一层的节点
            queue = temp;
        }

        return res;
    }
}

