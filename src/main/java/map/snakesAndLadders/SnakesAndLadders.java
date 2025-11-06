package map.snakesAndLadders;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author LuoXuanwei
 * @version 1.0
 * @description 蛇梯棋
 * @date 2025/11/6 19:21
 * <p>
 * 给你一个大小为 n x n 的整数矩阵 board ，方格按从 1 到 n2 编号，编号遵循 转行交替方式 ，从左下角开始 （即，从 board[n - 1][0] 开始）的每一行改变方向。
 * <p>
 * 你一开始位于棋盘上的方格  1。每一回合，玩家需要从当前方格 curr 开始出发，按下述要求前进：
 * <p>
 * 选定目标方格 next ，目标方格的编号在范围 [curr + 1, min(curr + 6, n2)] 。
 * 该选择模拟了掷 六面体骰子 的情景，无论棋盘大小如何，玩家最多只能有 6 个目的地。
 * 传送玩家：如果目标方格 next 处存在蛇或梯子，那么玩家会传送到蛇或梯子的目的地。否则，玩家传送到目标方格 next 。
 * 当玩家到达编号 n2 的方格时，游戏结束。
 * 如果 board[r][c] != -1 ，位于 r 行 c 列的棋盘格中可能存在 “蛇” 或 “梯子”。那个蛇或梯子的目的地将会是 board[r][c]。编号为 1 和 n2 的方格不是任何蛇或梯子的起点。
 * <p>
 * 注意，玩家在每次掷骰的前进过程中最多只能爬过蛇或梯子一次：就算目的地是另一条蛇或梯子的起点，玩家也 不能 继续移动。
 * <p>
 * 举个例子，假设棋盘是 [[-1,4],[-1,3]] ，第一次移动，玩家的目标方格是 2 。那么这个玩家将会顺着梯子到达方格 3 ，但 不能 顺着方格 3 上的梯子前往方格 4 。（简单来说，类似飞行棋，玩家掷出骰子点数后移动对应格数，遇到单向的路径（即梯子或蛇）可以直接跳到路径的终点，但如果多个路径首尾相连，也不能连续跳多个路径）
 * 返回达到编号为 n2 的方格所需的最少掷骰次数，如果不可能，则返回 -1。
 */
public class SnakesAndLadders {
    /**
     * 计算在蛇梯棋游戏中从起点1到达终点n*n所需的最少移动次数
     * 使用BFS算法寻找最短路径
     *
     * @param board n×n的整数矩阵，表示游戏棋盘
     *              -1表示普通格子，其他正整数表示蛇或梯子的目的地
     * @return 到达终点所需的最少移动次数，如果无法到达则返回-1
     */
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        // 记录每个位置是否已访问，避免重复访问
        int[] dist = new int[n * n + 1];
        // BFS队列，存储{当前位置, 已移动步数}
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{1, 0});

        // BFS搜索最短路径
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            // 模拟掷骰子，可能前进1-6步
            for (int i = 1; i <= 6; i++) {
                int next = cur[0] + i;

                if (next > n * n) {
                    break;
                }

                // 获取下一个位置在棋盘上的行列坐标
                int[] pos = getIndex(next, n);
                // 如果是蛇或梯子，则跳转到相应位置；否则停留在当前位置
                int nextPos = board[pos[0]][pos[1]] == -1 ? next : board[pos[0]][pos[1]];

                // 到达终点，返回步数
                if (nextPos == n * n) {
                    return cur[1] + 1;
                }

                // 如果该位置未访问过，则加入队列继续搜索
                if (dist[nextPos] == 0) {
                    dist[nextPos] = 1;
                    q.offer(new int[]{nextPos, cur[1] + 1});
                }
            }
        }

        return -1;
    }

    /**
     * 将一维位置编号转换为二维棋盘坐标
     * 蛇梯棋的编号规则是从左下角开始，奇数行从右到左，偶数行从左到右
     *
     * @param num 一维位置编号(从1开始)
     * @param n   棋盘边长
     * @return 对应的二维坐标数组{行索引, 列索引}
     */
    private int[] getIndex(int num, int n) {
        int row = (num - 1) / n;
        int cow = (num - 1) % n;
        // 奇数行需要反向排列
        if (row % 2 == 1) {
            cow = n - cow - 1;
        }

        return new int[]{n - 1 - row, cow};
    }
}
