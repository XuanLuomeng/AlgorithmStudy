package backtrack.exist;

import java.util.HashMap;
import java.util.Map;

/**
 * @author LuoXuanwei
 * @version 1.0
 * @description 单词搜索
 * @date 2025/11/16 19:45
 * <p>
 * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。
 * 如果 word 存在于网格中，返回 true ；否则，返回 false 。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，
 * 其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 */
public class Exist {
    private static final int[][] DIRECTIONS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private char[] wordArray;
    private boolean result;

    /**
     * 判断给定的二维字符网格中是否存在指定的单词
     * 使用回溯算法在网格中搜索单词，单词必须按照字母顺序通过相邻单元格构成
     *
     * @param board 二维字符网格，表示搜索空间
     * @param word  要搜索的字符串
     * @return 如果单词存在于网格中则返回true，否则返回false
     */
    public boolean exist(char[][] board, String word) {
        result = false;
            if (!optimize(board, word)) {
            return result;
        }

        // 检查输入网格的有效性
        if (board == null || board.length == 0 || board[0].length == 0) {
            return result;
        }

        // 遍历网格中的每个位置作为起始点进行搜索
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == wordArray[0]) {
                    backtrack(board, i, j, 0);
                    if (result) {
                        return true;
                    }
                }
            }
        }

        return result;
    }

    /**
     * 回溯算法实现，在网格中深度优先搜索单词
     *
     * @param board 二维字符网格
     * @param i     当前行坐标
     * @param j     当前列坐标
     * @param index 当前正在匹配的单词字符索引
     */
    private void backtrack(char[][] board, int i, int j, int index) {
        // 如果已匹配到单词的最后一个字符，则搜索成功
        if (++index == wordArray.length) {
            result = true;
            return;
        }

        // 标记当前位置已访问，避免重复使用
        char temp = board[i][j];
        board[i][j] = '#';

        // 向四个方向进行探索
        for (int[] direction : DIRECTIONS) {
            int newI = i + direction[0];
            int newJ = j + direction[1];
            // 检查新位置是否有效且字符匹配
            if (newI >= 0 && newI < board.length && newJ >= 0
                    && newJ < board[0].length && board[newI][newJ] == wordArray[index]) {
                backtrack(board, newI, newJ, index);
            }
        }

        // 回溯：恢复当前位置的原始字符
        board[i][j] = temp;
    }

    /**
     * 对单词搜索进行优化预处理 (缩短最短耗时和最长耗时的方差值)
     *
     * 此段代码为查找资料后添加的优化代码
     *
     * @param board 二维字符数组，表示字母板
     * @param word  待搜索的字符串
     * @return 如果字母板包含足够数量的字符来构成单词则返回true，否则返回false
     */
    private boolean optimize(char[][] board, String word) {
        // 统计字母板中每个字符的数量
        Map<Character, Integer> boardMap = new HashMap<>();
        for (char[] boardRow : board) {
            for (char c : boardRow) {
                boardMap.put(c, boardMap.getOrDefault(c, 0) + 1);
            }
        }

        // 统计单词中每个字符的数量
        Map<Character, Integer> wordMap = new HashMap<>();
        for (char c : word.toCharArray()) {
            wordMap.put(c, wordMap.getOrDefault(c, 0) + 1);
        }

        // 检查字母板是否包含足够的字符来构成单词
        for (Map.Entry<Character, Integer> entry : wordMap.entrySet()) {
            if (!boardMap.containsKey(entry.getKey()) || boardMap.get(entry.getKey()) < entry.getValue()) {
                return false;
            }
        }

        // 优化：如果首字符数量多于尾字符，则反转单词以提高搜索效率
        StringBuilder sb = new StringBuilder(word);
        if (wordMap.get(sb.charAt(0)) > wordMap.get(sb.charAt(sb.length() - 1))) {
            sb.reverse();
        }

        wordArray = sb.toString().toCharArray();
        return true;
    }
}
