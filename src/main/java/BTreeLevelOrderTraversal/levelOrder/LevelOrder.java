package BTreeLevelOrderTraversal.levelOrder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LuoXuanwei
 * @version 1.0
 * @description 二叉树的层序遍历
 * @date 2025/10/26 13:30
 * <p>
 * 给你二叉树的根节点 root ，返回其节点值的 层序遍历 。
 * （即逐层地，从左到右访问所有节点）。
 * <p>
 * 提示：
 * 树中节点数目在范围 [0, 2000] 内
 * -1000 <= Node.val <= 1000
 */
public class LevelOrder {
    class TreeNode {
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
     * 层序遍历(深度优先搜索)
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderTwo(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(root, 0, res);
        return res;
    }

    public void dfs(TreeNode root, int level, List<List<Integer>> res) {
        if (root == null) {
            return;
        }

        if (res.size() == level) {
            res.add(new ArrayList<>());
        }

        res.get(level).add(root.val);

        dfs(root.left, level + 1, res);
        dfs(root.right, level + 1, res);
    }

    /**
     * 层序遍历(广度优先搜索)
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();

        if (root == null) {
            return res;
        }

        List<TreeNode> curLevel = new ArrayList<>();
        curLevel.add(root);

        while (!curLevel.isEmpty()) {
            List<TreeNode> nextLevel = new ArrayList<>();
            List<Integer> curLevelValues = new ArrayList<>();

            for (TreeNode node : curLevel) {
                curLevelValues.add(node.val);

                if (node.left != null) {
                    nextLevel.add(node.left);
                }
                if (node.right != null) {
                    nextLevel.add(node.right);
                }
            }

            curLevel = nextLevel;
            res.add(curLevelValues);
        }

        return res;
    }
}
