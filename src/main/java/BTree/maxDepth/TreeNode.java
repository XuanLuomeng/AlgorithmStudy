package BTree.maxDepth;

/**
 * @author LuoXuanwei
 * @version 1.0
 * @description TODO
 * @date 2025/10/12 15:37
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
