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
 *
 * 实现方式: 广度优先搜索/深度优先搜索
 * 广度优先搜索在这个题目中是最常用的方法，因为广度优先搜索会按照层级顺序访问节点，
 *
 * 两者区别:
 * 广度优先搜索在本题中耗时会比深度优先搜索耗时稍微长；相对的来说，深度优先搜索会占用更多的内存空间。
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
     * 计算二叉树每层节点的平均值（方法一：广度优先搜索）
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
            for (TreeNode node : queue) {
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

    /**
     * 存储每层节点值总和的结果列表
     */
    private List<Double> res = new ArrayList<>();

    /**
     * 存储每层节点数量的计数列表
     */
    private List<Integer> levelCount = new ArrayList<>();

    /**
     * 计算二叉树每层节点的平均值（方法二：深度优先搜索）
     * 使用深度优先搜索(DFS)递归遍历二叉树，统计每层节点值总和和节点数量，最后计算平均值
     *
     * @param root 二叉树的根节点
     * @return 包含每层节点平均值的列表
     */
    public List<Double> averageOfLevelsTwo(TreeNode root) {

        if (root == null) {
            return res;
        }

        int level = 0;

        dfs(root, level);

        // 根据每层节点值总和和节点数量计算平均值
        for (int i = 0; i < res.size(); i++) {
            res.set(i, res.get(i) / levelCount.get(i));
        }

        return res;
    }

    /**
     * 深度优先搜索递归函数
     * 递归遍历二叉树，统计每层节点值总和和节点数量
     *
     * @param root  当前遍历的节点
     * @param level 当前节点所在的层级（从0开始）
     */
    public void dfs(TreeNode root, int level) {
        if (root == null) {
            return;
        }

        // 如果是新的一层，初始化该层的数据
        if (level == res.size()) {
            res.add((double) 0);
            levelCount.add(0);
        }

        Double value = res.get(level);
        int count = levelCount.get(level);

        // 累加当前节点值到对应层的总和中，并增加该层节点计数
        res.set(level, value + root.val);
        levelCount.set(level, count + 1);

        // 递归处理左右子树
        dfs(root.left, level + 1);
        dfs(root.right, level + 1);
    }
}
