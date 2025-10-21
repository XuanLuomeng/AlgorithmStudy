package BTree.BSTIterator;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author LuoXuanwei
 * @version 1.0
 * @description 二叉搜索树迭代器
 * @date 2025/10/21 12:41
 * <p>
 * 实现一个二叉搜索树迭代器类BSTIterator ，表示一个按中序遍历二叉搜索树（BST）的迭代器：
 * BSTIterator(TreeNode root) 初始化 BSTIterator 类的一个对象。BST 的根节点 root 会作为构造函数的一部分给出。指针应初始化为一个不存在于 BST 中的数字，且该数字小于 BST 中的任何元素。
 * boolean hasNext() 如果向指针右侧遍历存在数字，则返回 true ；否则返回 false 。
 * int next()将指针向右移动，然后返回指针处的数字。
 * 注意，指针初始化为一个不存在于 BST 中的数字，所以对 next() 的首次调用将返回 BST 中的最小元素。
 * <p>
 * 你可以假设 next() 调用总是有效的，也就是说，当调用 next() 时，BST 的中序遍历中至少存在一个下一个数字。
 *
 * 思路:
 * 使用Queue队列或List集合实现
 * 两者区别 :
 * 特征         |  Queue (LinkedList)   |       List (ArrayList)
 * 插入/删除效率 | O(1)                  |      O(1) amortized
 * 访问方式     |   FIFO only           |       支持索引访问
 * 语义契合度   |   高（更贴合迭代器行为）  |        较低
 * 实际运行速度 |   略慢或持平            |        略快或持平
 *
 * 构造函数中:
 * 1. 创建一个队列，将根节点加入队列中
 * 2. 遍历队列中的节点，将节点的按中序遍历顺序加入队列中
 * next() 函数:
 * 1. 从队列中取出节点并返回节点的值
 * hasNext() 函数:
 * 1. 判断队列是否为空
 */
public class BSTIterator {
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

    private Queue<TreeNode> queue;

    public BSTIterator(TreeNode root) {
        if (root ==  null){
            return;
        }

        queue = new LinkedList<>();

        inorder(root);
    }

    public void inorder(TreeNode root){
        if (root == null){
            return;
        }

        inorder(root.left);
        queue.offer(root);
        inorder(root.right);
    }

    public int next() {
        TreeNode node = queue.poll();
        return Math.min(Integer.MIN_VALUE, node.val);
    }

    public boolean hasNext() {
        return !queue.isEmpty();
    }
}
