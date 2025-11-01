package map.solve;

/**
 * @author LuoXuanwei
 * @version 1.0
 * @description 被围绕的区域
 * @date 2025/11/1 20:26
 * <p>
 * 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' 组成，捕获 所有 被围绕的区域：
 * <p>
 * 连接：一个单元格与水平或垂直方向上相邻的单元格连接。
 * 区域：连接所有 'O' 的单元格来形成一个区域。
 * 围绕：如果您可以用 'X' 单元格 连接这个区域，并且区域中没有任何单元格位于 board 边缘，则该区域被 'X' 单元格围绕。
 * 通过 原地 将输入矩阵中的所有 'O' 替换为 'X' 来 捕获被围绕的区域。你不需要返回任何值。
 * <p>
 * 思路:
 * 将所有边缘岛屿置为中间值 A, 然后将所有 O, 转为 X, 最后将所有 A, 转为 O
 */
public class Solve {
    /**
     * 解决被围绕的区域问题
     * 将所有被'X'围绕的'O'替换为'X'
     *
     * @param board 二维字符数组，包含'O'和'X'字符
     */
    public void solve(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }

        int m = board.length;
        int n = board[0].length;

        // 从边界上的'O'开始进行DFS，标记所有与边界相连的'O'为'A'
        for (int j = 0; j < n; j++) {
            if (board[0][j] == 'O') {
                dfs(board, 0, j);
            }
            if (board[m - 1][j] == 'O') {
                dfs(board, m - 1, j);
            }
        }

        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O') {
                dfs(board, i, 0);
            }
            if (board[i][n - 1] == 'O') {
                dfs(board, i, n - 1);
            }
        }

        // 遍历整个矩阵，将未标记的'O'替换为'X'，将标记的'A'恢复为'O'
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == 'A') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    /**
     * 深度优先搜索函数
     * 从指定位置开始，将相连的'O'标记为'A'
     *
     * @param board 二维字符数组
     * @param i 行索引
     * @param j 列索引
     */
    public void dfs(char[][] board, int i, int j) {
        // 边界检查和字符检查，如果越界或不是'O'则返回
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != 'O') {
            return;
        }

        // 标记当前节点为已访问
        board[i][j] = 'A';
        // 递归搜索四个方向的相邻节点
        dfs(board, i - 1, j);
        dfs(board, i + 1, j);
        dfs(board, i, j - 1);
        dfs(board, i, j + 1);
    }
}
