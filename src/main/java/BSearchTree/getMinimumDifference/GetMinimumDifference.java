package BSearchTree.getMinimumDifference;

/**
 * @author LuoXuanwei
 * @version 1.0
 * @description 二叉搜索树的最小绝对差
 * @date 2025/10/28 12:58
 * <p>
 * 给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。
 * <p>
 * 差值是一个正数，其数值等于两值之差的绝对值。
 */
public class GetMinimumDifference {
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

    private int pre = -1;
    private int ans = Integer.MAX_VALUE;

    /**
     * 获取二叉搜索树中任意两个不同节点值之间的最小绝对差
     *
     * @param root 二叉搜索树的根节点
     * @return 树中任意两不同节点值之间的最小差值
     */
    public int getMinimumDifference(TreeNode root) {
        inOrder(root);
        return ans;
    }

    /**
     * 通过中序遍历计算最小绝对差
     * 利用二叉搜索树的性质，中序遍历得到有序序列，相邻元素差值最小即为所求
     *
     * @param root 当前遍历的节点
     */
    private void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        // 处理当前节点，计算与前一个节点的差值
        if (pre != -1) {
            ans = Math.min(ans, root.val - pre);
            pre = root.val;
        } else {
            pre = root.val;
        }
        inOrder(root.right);
    }
}

