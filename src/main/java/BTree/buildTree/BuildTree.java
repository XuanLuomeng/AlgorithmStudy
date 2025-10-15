package BTree.buildTree;

import java.util.HashMap;
import java.util.Map;

/**
 * @author LuoXuanwei
 * @version 1.0
 * @description 从前序与中序遍历序列构造二叉树
 * @date 2025/10/15 20:31
 * <p>
 * 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
 * <p>
 * 注意: preorder 和 inorder 均 无重复 元素
 * <p>
 * 思路:
 * 先序 -> 中左右
 * 中序 -> 左中右
 * 先从先序遍历中获取根节点并对其进行构建，然后再从中序遍历中获取根节点的左右子树进行递归构造
 */
public class BuildTree {
    private static Map<Integer, Integer> treeMap;

    public static void main(String[] args) {
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        TreeNode treeNode = buildTree(preorder, inorder);

        printTree(treeNode);
    }

    public static void printTree(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.println(root.val);
        printTree(root.left);
        printTree(root.right);
    }

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        int preorder_len = preorder.length;

        treeMap = new HashMap<>();

        for (int i = 0; i < preorder_len; i++) {
            treeMap.put(inorder[i], i);
        }

        return myBuildTree(preorder, 0, preorder_len - 1, 0);
    }

    /**
     * 递归构造二叉树
     *
     * @param preorder       先序遍历数组
     * @param inorder_start  中序遍历的起始索引
     * @param inorder_end    中序遍历的结束索引
     * @param preorder_start 先序遍历的起始索引(根节点): 用于构建根节点并获取根节点在中序遍历中的索引位置
     * @return 根节点
     */
    public static TreeNode myBuildTree(int[] preorder, int inorder_start, int inorder_end,
                                int preorder_start) {
        // 当中序遍历的索引超出范围时，返回 null
        if (inorder_start > inorder_end) {
            return null;
        }
        // 获取根节点在中序遍历中的索引位置, 配合 inorder_start 和 inorder_end 获取根节点的中序遍历中左右子树的范围
        int inorder_root = treeMap.get(preorder[preorder_start]);

        // 创建根节点
        TreeNode root = new TreeNode(preorder[preorder_start]);

        // 从中序遍历中获取左子树的长度, 用于获取先序遍历中下一次递归的右子树根节点索引位置
        int left_len = inorder_root - inorder_start;

        // 递归构造左子树
        root.left = myBuildTree(preorder, inorder_start, inorder_root - 1, preorder_start + 1);

        // 递归构造右子树
        root.right = myBuildTree(preorder, inorder_root + 1, inorder_end, preorder_start + left_len + 1);

        return root;
    }
}
