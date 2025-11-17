package divide.sortedArrayToBST;

/**
 * @author LuoXuanwei
 * @version 1.0
 * @description 将有序数组转换为二叉搜索树
 * @date 2025/11/17 20:14
 *
 * 给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 平衡 二叉搜索树。
 */
public class SortedArrayToBST {
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
     * 将有序数组转换为高度平衡的二叉搜索树
     *
     * @param nums 输入的有序整数数组
     * @return 返回构建的二叉搜索树的根节点
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return buildTree(nums, 0, nums.length - 1);
    }

    /**
     * 递归构建二叉搜索树
     *
     * @param nums  输入的有序整数数组
     * @param left  当前处理区间的左边界（包含）
     * @param right 当前处理区间的右边界（包含）
     * @return 返回当前区间构建的子树的根节点
     */
    private TreeNode buildTree(int[] nums, int left, int right) {
        // 递归终止条件：左边界大于右边界时返回null
        if (left > right) {
            return null;
        }

        // 选择中间元素作为根节点，保证左右子树高度平衡
        int mid = (left + right) / 2;

        // 构建当前根节点
        TreeNode root = new TreeNode(nums[mid]);
        // 递归构建左子树
        root.left = buildTree(nums, left, mid - 1);
        // 递归构建右子树
        root.right = buildTree(nums, mid + 1, right);

        return root;
    }
}
