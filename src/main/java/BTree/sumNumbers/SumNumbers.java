package BTree.sumNumbers;

/**
 * @author LuoXuanwei
 * @version 1.0
 * @description 求根节点到叶节点数字之和
 * @date 2025/10/19 15:58
 *
 * 给你一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。
 * 每条从根节点到叶节点的路径都代表一个数字：
 *
 * 例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。
 * 计算从根节点到叶节点生成的 所有数字之和 。
 *
 * 叶节点 是指没有子节点的节点。
 *
 * 思路:
 * 深度优先遍历
 * 1、创建一个递归函数，传入根节点和数字
 * 2、判断根节点是否为空
 * 3、判断根节点是否为叶子节点
 * 4、如果根节点为叶子节点，返回总值
 * 5、递归调用左子树和右子树，并传入当前总值
 */
public class SumNumbers {
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

    public static void main(String[] args) {

    }

    public int sumNumbers(TreeNode root) {
        return sumNumbersPath(root, 0);
    }

    public int sumNumbersPath(TreeNode root, int num){
        if(root == null){
            return 0;
        }

        // 计算节点值
        num = num * 10 + root.val;

        // 判断是否为叶子节点
        if(root.left == null && root.right == null){
            return num;
        }

        // 递归调用，遍历左右节点
        return sumNumbersPath(root.left, num) + sumNumbersPath(root.right, num);
    }
}
