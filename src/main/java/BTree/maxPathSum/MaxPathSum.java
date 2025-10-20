package BTree.maxPathSum;

/**
 * @author LuoXuanwei
 * @version 1.0
 * @description 二叉树中的最大路径和
 * @date 2025/10/20 19:45
 *
 * 二叉树中的 路径 被定义为一条节点序列，序列中每对相邻节点之间都存在一条边。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
 *
 * 路径和 是路径中各节点值的总和。
 *
 * 给你一个二叉树的根节点 root ，返回其 最大路径和 。
 *
 * 思路: 深度优先遍历 + 贪心算法
 * 1、创建一个函数用于递归实现深度优先遍历
 * 2、递归求解左子树和右子树的最大路径和(低于0则抛弃该子树)
 * 3、求以当前节点为根的子树加上左右子树的路径最大和
 * 4、每次递归都需要进行一次答案更新到全局变量中
 * 5、返回当前节点 + max(left, right)
 *
 * 例如:
 *      -10
 *      / \
 *     9   20
 *        /  \
 *       15   7
 * 递归计算顺序遵循后序遍历
 * 更新 maxSum 为 Math.max(maxSum, root + left + right)
 * 递归函数返回 root + Math.max(left, right)
 * 1、第一次 maxSum 为 9 (9 + 0 + 0 > Integer.MIN_VALUE), 返回 9 + 0
 * 2、第二次 maxSum 为 15 (15 + 0 + 0 > 9), 返回 15 + 0
 * 3、第三次 maxSum 为 15 (7 + 0 + 0 < 15), 返回 7 + 0
 * 4、第四次 maxSum 为 42 (20 + 15 + 7 > 15), 返回 15 + 20 (15 + 20 > 7 + 20)
 * 5、第五次 maxSum 为 42 (35 + 9 - 10 < 42), 返回 35 - 10
 * 6、已遍历完所有节点，递归结束
 */
public class MaxPathSum {
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

    private int maxSum = Integer.MIN_VALUE;

    public static void main(String[] args) {

    }

    public int maxPathSum(TreeNode root) {
        // dfs 递归函数返回的最终结果不是算法的最终结果
        dfs(root);
        // 正确的结果在 maxSum 中
        return maxSum;
    }

    /**
     * 每次递归返回的结果都是当前节点为根的子树中，路径和最大的一条路径的值
     * 每次递归都会更新 maxSum
     * @param root
     * @return
     */
    public int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 递归求解左右子树最大路径和
        int left = Math.max(dfs(root.left), 0);
        int right = Math.max(dfs(root.right), 0);

        // 获取当前节点为根的子树的最大路径和
        int priceNewpath = root.val + left + right;

        // 每次递归都需要进行一次答案更新
        maxSum = Math.max(maxSum, priceNewpath);

        // 返回当前节点为根的子树中，路径和最大的一条路径的值
        return root.val + Math.max(left, right);
    }
}
