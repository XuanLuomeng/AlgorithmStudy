package map.numIslands;

/**
 * @author LuoXuanwei
 * @version 1.0
 * @description 岛屿数量
 * @date 2025/10/31 12:02
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 *
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 *
 * 此外，你可以假设该网格的四条边均被水包围。
 *
 * 提示：
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * grid[i][j] 的值为 '0' 或 '1'
 *
 * 思路:深度优先算法(岛屿淹没法)
 * 1、创建一个二维数组，记录每个节点是否被访问过
 * 2、遍历二维数组，如果当前节点为1，则进行深度优先算法，将当前节点及其相邻的节点都标记为已访问
 * 3、返回已访问的节点数量
 */
public class NumIslands {
    /**
     * 使用深度优先搜索(DFS)遍历并标记相连的陆地
     * 将访问过的陆地('1')标记为水('0')，避免重复计算
     *
     * @param grid 二维网格数组，'1'表示陆地，'0'表示水
     * @param r    当前行坐标
     * @param c    当前列坐标
     */
    public void dfs(char[][] grid, int r, int c) {
        int nr = grid.length;
        int nc = grid[0].length;

        // 边界检查：坐标越界或当前位置不是陆地时直接返回
        if (r < 0 || c < 0 || r >= nr || c >= nc || grid[r][c] != '1') {
            return;
        }

        // 标记当前位置为已访问
        grid[r][c] = '0';
        // 递归搜索上下左右四个方向的相邻位置
        dfs(grid, r - 1, c);
        dfs(grid, r + 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1);
    }

    /**
     * 计算二维网格中的岛屿数量
     * 岛屿由相邻的'1'组成，相邻指水平或垂直方向
     *
     * @param grid 二维网格数组，'1'表示陆地，'0'表示水
     * @return 岛屿的数量
     */
    public int numIslands(char[][] grid) {
        // 处理空网格的边界情况
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int nr = grid.length;
        int nc = grid[0].length;

        int num_islands = 0;

        // 遍历整个网格
        for (int r = 0; r < nr; ++r) {
            for (int c = 0; c < nc; ++c) {
                // 发现未访问的陆地时，岛屿计数加1并进行DFS标记整片岛屿
                if (grid[r][c] == '1') {
                    ++num_islands;
                    dfs(grid, r, c);
                }
            }
        }

        return num_islands;
    }
}
