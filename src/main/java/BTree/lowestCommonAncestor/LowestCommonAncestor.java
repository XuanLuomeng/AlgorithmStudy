package BTree.lowestCommonAncestor;

/**
 * @author LuoXuanwei
 * @version 1.0
 * @description 二叉树的最近公共祖先
 * @date 2025/10/23 16:06
 * <p>
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * <p>
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，
 * 最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 * <p>
 * 提示：
 * <p>
 * 树中节点数目在范围 [2, 105] 内。
 * -109 <= Node.val <= 109
 * 所有 Node.val 互不相同 。
 * p != q
 * p 和 q 均存在于给定的二叉树中。
 * <p>
 * 思路:
 * 1、从根节点开始
 * 2、递归判断左右子树是否存在 p 和 q 节点
 * 3、如果左右子树分别存在 p 或 q 节点，则当前节点为最近公共祖先
 * 4、如果左右子树只有一个子树存在一个 p 或 q 节点，则当前节点不是最近公共祖先
 */
public class LowestCommonAncestor {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 寻找二叉树中两个节点的最近公共祖先
     *
     * @param root 二叉树的根节点
     * @param p    目标节点p
     * @param q    目标节点q
     * @return 返回节点p和节点q的最近公共祖先节点
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 基础情况：如果当前节点为空，或当前节点就是p或q，则直接返回当前节点
        if (root == null || root == p || root == q) {
            return root;
        }

        // 递归查找左子树和右子树中p和q的最近公共祖先
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // 如果左右子树都找到了目标节点，说明当前节点就是最近公共祖先
        if (left != null && right != null) {
            return root;
        }

        // 如果只有一侧子树找到了目标节点，则返回该侧的结果
        return left != null ? left : right;
    }

}
