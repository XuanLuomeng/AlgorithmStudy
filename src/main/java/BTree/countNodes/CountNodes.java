package BTree.countNodes;

/**
 * @author LuoXuanwei
 * @version 1.0
 * @description 完全二叉树的节点个数
 * @date 2025/10/22 19:05
 * <p>
 * 给你一棵 完全二叉树 的根节点 root ，求出该树的节点个数。
 * <p>
 * 完全二叉树 的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，
 * 并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层（从第 0 层开始），则该层包含 1~ 2h 个节点。
 * <p>
 * 提示：
 * <p>
 * 树中节点的数目范围是[0, 5 * 104]
 * 0 <= Node.val <= 5 * 104
 * 题目数据保证输入的树是 完全二叉树
 * <p>
 * 思路: 二分查找
 * 1、创建一个函数，用于计算当前根节点下的最左边节点个数
 * 2、循环判断当前节点的左右子树的最左边节点个数是否相等
 * 3、相等说明当前节点的左子树为满二叉树，不相等说明当前节点的右子树为满二叉树
 * 4、节点数 += 2^left_height
 */
public class CountNodes {
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

    public int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = 0;

        while (root.left != null) {
            left++;
            root = root.left;
        }

        return left;
    }

    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = getHeight(root);
        int count = 0;

        while (root != null){
            int right = getHeight(root.right);
            if (left == right) {
                // 左子树为满二叉树，无需继续计算左子树
                count += 1 << left;
                root = root.right;
            } else {
                // 右子树为满二叉树，无需继续计算右子树
                count += 1 << right;
                root = root.left;
            }
            // 减去当前节点高度
            left--;
        }

        return count;
    }
}
