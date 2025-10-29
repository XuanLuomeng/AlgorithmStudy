package BSearchTree.kthSmallest;

/**
 * @author LuoXuanwei
 * @version 1.0
 * @description 二叉搜索树中第 K 小的元素
 * @date 2025/10/29 13:05
 */
public class KthSmallest {
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

    private int ans = 0;
    private int count = 0;

    /**
     * 查找二叉搜索树中第k小的元素
     * 思路: 中序遍历
     *
     * @param root 二叉搜索树的根节点
     * @param k 要查找的第k小元素的序号(从1开始)
     * @return 第k小的元素值
     */
    public int kthSmallest(TreeNode root, int k) {
        dfs(root, k);
        return ans;
    }

    /**
     * 通过中序遍历查找第k小的元素
     * 利用二叉搜索树的性质，中序遍历得到的是递增序列
     *
     * @param root 当前遍历的节点
     * @param k 目标位置
     */
    public void dfs (TreeNode root, int k) {
        if (root == null) {
            return ;
        }

        // 先遍历左子树
        dfs(root.left, k);

        // 访问当前节点，更新计数
        count += 1;
        int level = count;

        // 如果当前节点是第k小的元素，则记录结果并返回
        if (level == k) {
            ans = root.val;
            return ;
        }
        // 继续遍历右子树
        dfs(root.right, k);
    }

}
