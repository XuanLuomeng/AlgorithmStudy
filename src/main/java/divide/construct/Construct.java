package divide.construct;

/**
 * @author LuoXuanwei
 * @version 1.0
 * @description 建立四叉树
 * @date 2025/11/18 16:24
 * <p>
 * 给你一个 n * n 矩阵 grid ，矩阵由若干 0 和 1 组成。请你用四叉树表示该矩阵 grid 。
 * <p>
 * 你需要返回能表示矩阵 grid 的 四叉树 的根结点。
 * <p>
 * 四叉树数据结构中，每个内部节点只有四个子节点。此外，每个节点都有两个属性：
 * <p>
 * val：储存叶子结点所代表的区域的值。1 对应 True，0 对应 False。注意，当 isLeaf 为 False 时，你可以把 True 或者 False 赋值给节点，两种值都会被判题机制 接受 。
 * isLeaf: 当这个节点是一个叶子结点时为 True，如果它有 4 个子节点则为 False 。
 * class Node {
 * public boolean val;
 * public boolean isLeaf;
 * public Node topLeft;
 * public Node topRight;
 * public Node bottomLeft;
 * public Node bottomRight;
 * }
 * 我们可以按以下步骤为二维区域构建四叉树：
 * <p>
 * 如果当前网格的值相同（即，全为 0 或者全为 1），将 isLeaf 设为 True ，将 val 设为网格相应的值，并将四个子节点都设为 Null 然后停止。
 * 如果当前网格的值不同，将 isLeaf 设为 False， 将 val 设为任意值，然后如下图所示，将当前网格划分为四个子网格。
 * 使用适当的子网格递归每个子节点。
 * <p>
 * 你不需要阅读本节来解决这个问题。只有当你想了解输出格式时才会这样做。输出为使用层序遍历后四叉树的序列化形式，其中 null 表示路径终止符，其下面不存在节点。
 * <p>
 * 它与二叉树的序列化非常相似。唯一的区别是节点以列表形式表示 [isLeaf, val] 。
 * <p>
 * 如果 isLeaf 或者 val 的值为 True ，则表示它在列表 [isLeaf, val] 中的值为 1 ；如果 isLeaf 或者 val 的值为 False ，则表示值为 0 。
 */
public class Construct {
    class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;


        public Node() {
            this.val = false;
            this.isLeaf = false;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = topLeft;
            this.topRight = topRight;
            this.bottomLeft = bottomLeft;
            this.bottomRight = bottomRight;
        }
    }

    /**
     * 构造四叉树
     *
     * @param grid 输入的二维网格数组，只包含0和1
     * @return 返回构造好的四叉树的根节点
     */
    public Node construct(int[][] grid) {
        return dfs(grid, 0, 0, grid.length, grid[0].length);
    }

    /**
     * 使用深度优先搜索递归构造四叉树
     *
     * @param grid 输入的二维网格数组
     * @param x    当前处理区域的起始行索引
     * @param y    当前处理区域的起始列索引
     * @param n    当前处理区域的结束行索引（不包含）
     * @param m    当前处理区域的结束列索引（不包含）
     * @return 返回当前区域对应的四叉树节点
     */
    private Node dfs(int[][] grid, int x, int y, int n, int m) {
        // 检查当前区域是否为叶子节点（区域内所有值相同）
        boolean isLeaf = true;
        int val = grid[x][y];
        for (int i = x; i < n; i++) {
            for (int j = y; j < m; j++) {
                if (grid[i][j] != val) {
                    isLeaf = false;
                    break;
                }
            }
            if (!isLeaf) break;
        }

        // 如果是叶子节点，直接创建并返回叶子节点
        if (isLeaf) {
            return new Node(val == 1, true);
        }

        // 如果不是叶子节点，递归创建四个子节点
        Node node = new Node(
                true,
                false,
                dfs(grid, x, y, (x + n) / 2, (y + m) / 2),
                dfs(grid, x, (y + m) / 2, (x + n) / 2, m),
                dfs(grid, (x + n) / 2, y, n, (y + m) / 2),
                dfs(grid, (x + n) / 2, (y + m) / 2, n, m)
        );

        return node;
    }
}
