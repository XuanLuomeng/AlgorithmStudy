package trie.findWords;

import java.util.*;

/**
 * @author LuoXuanwei
 * @version 1.0
 * @description 单词搜索 II
 * @date 2025/11/10 19:00
 * <p>
 * 给定一个 m x n 二维字符网格 board 和一个单词（字符串）列表 words， 返回所有二维网格上的单词 。
 * <p>
 * 单词必须按照字母顺序，通过 相邻的单元格 内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
 * 同一个单元格内的字母在一个单词中不允许被重复使用。
 */
public class FindWords {
    /**
     * 前缀树（Trie）数据结构，用于高效存储和检索单词前缀
     */
    private class Trie {
        // 存储完整的单词（如果是叶子节点）
        String word;
        // 子节点映射，键为字符，值为对应的子Trie节点
        Map<Character, Trie> children;

        /**
         * 构造函数，初始化空的Trie节点
         */
        public Trie() {
            word = "";
            children = new HashMap<>();
        }

        /**
         * 将单词插入到Trie树中
         *
         * @param word 需要插入的单词
         */
        public void insert(String word) {
            Trie trie = this;
            // 遍历单词中的每个字符，逐层构建Trie树
            for (char ch : word.toCharArray()) {
                if (!trie.children.containsKey(ch)) {
                    trie.children.put(ch, new Trie());
                }
                trie = trie.children.get(ch);
            }
            // 在单词结尾的节点存储完整单词
            trie.word = word;
        }
    }

    // 四个方向的移动向量：右、左、下、上
    private static final int[][] DIRECTIONS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    /**
     * 在二维字符网格中查找所有存在于给定单词列表中的单词
     *
     * @param board 二维字符网格
     * @param words 需要查找的单词数组
     * @return 在网格中找到的所有单词列表
     */
    public List<String> findWords(char[][] board, String[] words) {
        // 构建包含所有目标单词的Trie树
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }

        // 使用Set避免重复结果
        Set<String> res = new HashSet<>();
        // 遍历网格的每个位置作为搜索起点
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                // 如果当前字符是某个单词的起始字符，则开始深度优先搜索
                if (trie.children.containsKey(board[i][j])) {
                    dfs(board, i, j, trie, res);
                }
            }
        }

        // 将结果转换为List返回
        return new ArrayList<>(res);
    }

    /**
     * 深度优先搜索函数，在网格中查找以当前位置为起点的单词
     *
     * @param board 二维字符网格
     * @param i     当前行索引
     * @param j     当前列索引
     * @param trie  当前Trie节点
     * @param res   存储找到的单词结果集合
     */
    private void dfs(char[][] board, int i, int j, Trie trie, Set<String> res) {
        char ch = board[i][j];
        // 如果当前字符不在Trie的子节点中，说明无法构成有效单词前缀，直接返回
        if (!trie.children.containsKey(ch)) {
            return;
        }
        // 移动到对应的子Trie节点
        trie = trie.children.get(ch);
        // 如果当前节点存储了完整单词，将其添加到结果集中
        if (!trie.word.equals("")) {
            res.add(trie.word);
        }
        // 标记当前位置已访问，防止重复使用
        board[i][j] = '#';
        // 向四个方向继续搜索
        for (int[] direction : DIRECTIONS) {
            int x = i + direction[0];
            int y = j + direction[1];
            // 检查新位置是否在网格范围内
            if (x >= 0 && x < board.length && y >= 0 && y < board[0].length) {
                dfs(board, x, y, trie, res);
            }
        }
        // 回溯：恢复当前位置的字符
        board[i][j] = ch;
    }

}
