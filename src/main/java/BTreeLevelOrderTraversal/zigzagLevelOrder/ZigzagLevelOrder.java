package BTreeLevelOrderTraversal.zigzagLevelOrder;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author LuoXuanwei
 * @version 1.0
 * @description 二叉树的锯齿形层序遍历
 * @date 2025/10/27 18:29
 * <p>
 * 给你二叉树的根节点 root ，返回其节点值的 锯齿形层序遍历 。
 * （即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 */
public class ZigzagLevelOrder {
    /**
     * 二叉树节点定义
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

    /**
     * 实现二叉树的锯齿形层序遍历（Zigzag Level Order Traversal）
     * 遍历方式为：第一层从左到右，第二层从右到左，第三层从左到右，以此类推。
     *
     * @param root 二叉树的根节点
     * @return 按照锯齿形顺序遍历得到的每层节点值组成的列表
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();

        if (root == null) {
            return res;
        }

        Deque<TreeNode> curLevel = new ArrayDeque<>();
        curLevel.add(root);
        boolean leftToRight = true; // 标识当前层是否从左向右遍历

        while (!curLevel.isEmpty()) {
            Deque<TreeNode> nextLevel = new ArrayDeque<>();
            List<Integer> curLevelVal = new ArrayList<>();

            while (!curLevel.isEmpty()) {
                if (leftToRight) {
                    // 从左向右时
                    TreeNode node = curLevel.pollFirst();
                    curLevelVal.add(node.val);
                    // 左到右时按正常顺序加入孩子节点
                    // 尾插: 因为下一个循环是从右到左访问
                    if (node.left != null) {
                        nextLevel.addLast(node.left);
                    }
                    if (node.right != null) {
                        nextLevel.addLast(node.right);
                    }
                } else {
                    // 从右向左时
                    TreeNode node = curLevel.pollLast();
                    curLevelVal.add(node.val);
                    // 右到左时反向加入孩子节点保证下次正向出队正确
                    // 头插: 因为下一个循环是从左到右访问
                    if (node.right != null) {
                        nextLevel.addFirst(node.right);
                    }
                    if (node.left != null) {
                        nextLevel.addFirst(node.left);
                    }
                }
            }

            leftToRight = !leftToRight;
            res.add(curLevelVal);
            curLevel = nextLevel;
        }

        return res;
    }
}
