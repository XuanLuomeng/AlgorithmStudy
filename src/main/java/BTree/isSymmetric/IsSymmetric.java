package BTree.isSymmetric;

/**
 * @author LuoXuanwei
 * @version 1.0
 * @description 对称二叉树
 * @date 2025/10/14 19:32
 * <p>
 * 思路:
 * 深度遍历
 */
public class IsSymmetric {
    public static void main(String[] args) {

    }

    public boolean isSymmetric(TreeNode root) {
        return check(root, root);
    }

    public boolean check(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }

        // 只要左右子树有一个为空另一个不为空，则返回false，导致整个结果为false
        if (left == null || right == null) {
            return false;
        }

        // 递归判断，先判断当前两个节点的值是否相等，再判断左右子树是否对称
        return left.val == right.val && check(left.left, right.right) && check(left.right, right.left);
    }
}
