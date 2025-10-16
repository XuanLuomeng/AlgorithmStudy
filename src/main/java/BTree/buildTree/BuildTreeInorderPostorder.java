package BTree.buildTree;

import java.util.HashMap;
import java.util.Map;

/**
 * @author LuoXuanwei
 * @version 1.0
 * @description 从中序与后序遍历序列构造二叉树
 * @date 2025/10/16 21:14
 * <p>
 * 给定两个整数数组 inorder 和 postorder ，其中 inorder 是二叉树的中序遍历， postorder 是同一棵树的后序遍历，请你构造并返回这颗 二叉树 。
 * <p>
 * 注意: preorder 和 inorder 均 无重复 元素
 * <p>
 * 思路:
 * 后序 -> 左右中
 * 中序 -> 左中右
 * 先从后序遍历中获取根节点并对其进行构建，然后再从中序遍历中获取根节点的左右子树进行递归构造
 */
public class BuildTreeInorderPostorder {
    private static Map<Integer, Integer> treeMap;

    public static void main(String[] args) {
        int[] inorder = {9, 3, 15, 20, 7};
        int[] postorder = {9, 15, 7, 20, 3};

        TreeNode treeNode = buildTree(inorder, postorder);

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

    public static TreeNode buildTree(int[] inorder, int[] postorder) {
        int postorder_len = postorder.length;

        treeMap = new HashMap<>();

        for (int i = 0; i < postorder_len; i++) {
            treeMap.put(inorder[i], i);
        }

        return myBuildTree(postorder, 0, postorder_len - 1, postorder_len - 1);
    }

    /**
     * 递归构造二叉树
     *
     * @param postorder       后序遍历数组
     * @param inorder_start   中序遍历的起始索引
     * @param inorder_end     中序遍历的结束索引
     * @param postorder_end   后序遍历的结束索引(根节点): 用于获取根节点在中序遍历中的索引位置
     * @return 根节点
     */
    public static TreeNode myBuildTree(int[] postorder, int inorder_start, int inorder_end,
                                int postorder_end) {
        if (inorder_start > inorder_end) {
            return null;
        }

        // 获取根节点在中序遍历中的索引位置, 配合 inorder_start 和 inorder_end 获取根节点的中序遍历中左右子树的范围
        int inorder_root = treeMap.get(postorder[postorder_end]);
        TreeNode root = new TreeNode(postorder[postorder_end]);

        // 从中序遍历中获取右子树的长度, 用于获取后序遍历中下下一个递归的左子树根节点索引位置
        int right_len = inorder_end - inorder_root;

        // 递归构造右子树
        root.left = myBuildTree(postorder, inorder_start, inorder_root - 1, postorder_end - right_len - 1);
        root.right = myBuildTree(postorder, inorder_root + 1, inorder_end, postorder_end - 1);

        return root;
    }
}
