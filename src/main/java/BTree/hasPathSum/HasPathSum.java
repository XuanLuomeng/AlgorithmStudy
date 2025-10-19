package BTree.hasPathSum;

/**
 * @author LuoXuanwei
 * @version 1.0
 * @description 路径总和
 * @date 2025/10/19 15:49
 *
 * 给你二叉树的根节点 root 和一个表示目标和的整数 targetSum 。判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和 targetSum 。如果存在，返回 true ；否则，返回 false 。
 *
 * 叶子节点 是指没有子节点的节点。
 *
 * <p>
 * 思路:
 * 深度优先遍历
 * 1、创建一个递归函数，传入根节点和目标值
 * 2、判断根节点是否为空
 * 3、判断根节点的值是否等于目标值
 * 4、如果根节点的值等于目标值，返回true
 * 5、如果根节点的值不等于目标值，返回false
 * 6、递归调用左子树和右子树，并传入目标值减去根节点的值
 * 7、返回左子树和右子树中任意一个为true的
 * 8、递归调用结束
 */
public class HasPathSum {
    public static void main(String[] args) {

    }

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }

        if (root.left == null && root.right == null) {
            return targetSum == root.val;
        }

        return hasPathSum(root.left, targetSum - root.val) ||
                hasPathSum(root.right, targetSum - root.val);
    }
}
