package BTree.invertTree;

/**
 * @author LuoXuanwei
 * @version 1.0
 * @description 翻转二叉树
 * @date 2025/10/13 23:49
 *
 * 给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
 */
public class InvertTree {
    public static void main(String[] args) {

    }

    /**
     * 思路: 递归将所有节点的左右子节点进行替换
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root != null) {
            // 左右子节点替换
            TreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;

            // 递归处理左右子节点
            invertTree(root.left);
            invertTree(root.right);

            return root;
        }

        return null;
    }
}
