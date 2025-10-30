package BSearchTree.isValidBST;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LuoXuanwei
 * @version 1.0
 * @description 验证二叉搜索树
 * @date 2025/10/30 18:18
 *
 * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 *
 * 有效 二叉搜索树定义如下：
 *
 * 节点的左子树只包含 严格小于 当前节点的数。
 * 节点的右子树只包含 严格大于 当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 *
 * 思路:
 * 中序遍历的结果序列，必须严格递增
 */
public class IsValidBST {
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
     * 判断给定的二叉树是否为有效的二叉搜索树
     * 通过递归方式验证每个节点的值是否在合法范围内
     *
     * @param root 二叉树的根节点
     * @param min 当前节点允许的最小值（不包含）
     * @param max 当前节点允许的最大值（不包含）
     * @return 如果是有效的二叉搜索树返回true，否则返回false
     */
    public boolean isValidBST(TreeNode root, long min, long max) {
        if (root == null) {
            return true;
        }

        if (root.val <= min || root.val >= max) {
            return false;
        }

        return isValidBST(root.left, min, root.val) && isValidBST(root.right, root.val, max);
    }

    private List<Integer> list;

    /**
     * 判断给定的二叉树是否为有效的二叉搜索树
     * 使用中序遍历的方式，将节点值存储到列表中，然后检查列表是否严格递增
     *
     * @param root 二叉树的根节点
     * @return 如果是有效的二叉搜索树返回true，否则返回false
     */
    public boolean isValidBST(TreeNode root) {
        // return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);

        list = new ArrayList<>();
        dfs(root);

        // 检查中序遍历结果是否严格递增
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) <= list.get(i - 1)) {
                return false;
            }
        }

        return true;
    }

    /**
     * 对二叉树进行中序遍历，并将节点值添加到列表中
     *
     * @param root 当前遍历的节点
     */
    public void dfs(TreeNode root) {
        if (root == null) {
            return;
        }

        dfs(root.left);
        list.add(root.val);
        dfs(root.right);
    }

}
