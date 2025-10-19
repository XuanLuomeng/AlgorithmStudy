package BTree.hasPathSum;

/**
 * @author LuoXuanwei
 * @version 1.0
 * @description
 * @date 2025/10/19 15:49
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
