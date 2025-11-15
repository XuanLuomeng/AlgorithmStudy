package backtrack.totalNQueens;

import java.util.HashSet;

/**
 * @author LuoXuanwei
 * @version 1.0
 * @description N 皇后 II
 * @date 2025/11/15 20:28
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n × n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 给你一个整数 n ，返回 n 皇后问题 不同的解决方案的数量。
 * <p>
 * 特点:
 * 左向右斜线特点: 所有皇后的行坐标和列坐标之差相同
 * 右向左斜线特点: 所有皇后的行坐标和列坐标之和相同
 */
public class TotalNQueens {
    /**
     * 计算n皇后问题的解决方案总数
     *
     * @param n 皇后的数量，同时也是棋盘的大小(n×n)
     * @return 返回n皇后问题的所有可行解决方案数量
     */
    public int totalNQueens(int n) {
        return backtrack(n, 0, new HashSet<>(), new HashSet<>(), new HashSet<>());
    }

    /**
     * 使用回溯算法递归计算n皇后问题的解法数量
     *
     * @param n     皇后的数量
     * @param row   当前正在处理的行号
     * @param cols  记录已占用的列位置
     * @param left  记录已占用的左对角线(行号-列号)
     * @param right 记录已占用的右对角线(行号+列号)
     * @return 从当前状态开始能够得到的解决方案数量
     */
    private int backtrack(int n, int row, HashSet<Integer> cols, HashSet<Integer> left, HashSet<Integer> right) {
        int count = 0;
        // 递归终止条件：所有行都已成功放置皇后
        if (row == n) {
            return 1;
        } else {
            // 遍历当前行的所有列，尝试放置皇后
            for (int i = 0; i < n; i++) {
                // 检查当前位置是否与已放置的皇后冲突(列冲突或对角线冲突)
                if (cols.contains(i) || left.contains(row - i) || right.contains(row + i)) {
                    continue;
                }
                // 放置皇后并更新约束条件
                cols.add(i);
                left.add(row - i);
                right.add(row + i);
                // 递归处理下一行，并累加返回的解法数量
                count += backtrack(n, row + 1, cols, left, right);
                // 回溯：移除当前行放置的皇后，恢复约束条件
                cols.remove(i);
                left.remove(row - i);
                right.remove(row + i);
            }
        }
        return count;
    }

}
