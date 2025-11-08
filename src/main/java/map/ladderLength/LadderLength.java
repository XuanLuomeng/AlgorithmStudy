package map.ladderLength;

import java.util.*;

/**
 * @author LuoXuanwei
 * @version 1.0
 * @description 单词接龙
 * @date 2025/11/8 14:47
 * <p>
 * 字典 wordList 中从单词 beginWord 到 endWord 的 转换序列 是一个按下述规格形成的序列 beginWord -> s1 -> s2 -> ... -> sk：
 * <p>
 * 每一对相邻的单词只差一个字母。
 * 对于 1 <= i <= k 时，每个 si 都在 wordList 中。注意， beginWord 不需要在 wordList 中。
 * sk == endWord
 * 给你两个单词 beginWord 和 endWord 和一个字典 wordList ，返回 从 beginWord 到 endWord 的 最短转换序列 中的 单词数目 。如果不存在这样的转换序列，返回 0 。
 */
public class LadderLength {
    /**
     * 计算从起始单词到目标单词的最短转换序列长度
     * 转换规则：每次只能改变一个字母，且转换后的单词必须在单词列表中
     *
     * @param beginWord 起始单词
     * @param endWord   目标单词
     * @param wordList  单词列表，包含所有可转换的中间单词
     * @return 最短转换序列的长度，如果无法转换则返回0
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        // 检查目标单词是否在单词列表中
        if (!wordSet.contains(endWord)) {
            return 0;
        }
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        int step = 1;
        queue.offer(beginWord);
        visited.add(beginWord);
        // 使用BFS搜索最短路径
        while (!queue.isEmpty()) {
            int size = queue.size();
            // 遍历当前层的所有节点
            for (int i = 0; i < size; i++) {
                String cur = queue.poll();
                List<String> nextString = getNextString(cur, wordSet);
                // 检查下一个可能的单词
                for (String next : nextString) {
                    if (next.equals(endWord)) {
                        return step + 1;
                    }
                    if (!visited.contains(next)) {
                        queue.offer(next);
                        visited.add(next);
                    }
                }
            }
            step++;
        }
        return 0;
    }

    /**
     * 获取与当前单词相差一个字母的所有可能单词
     *
     * @param cur     当前单词
     * @param wordSet 单词集合，用于验证生成的单词是否有效
     * @return 与当前单词相差一个字母的有效单词列表
     */
    private List<String> getNextString(String cur, Set<String> wordSet) {
        List<String> res = new ArrayList<>();
        char[] chars = cur.toCharArray();
        // 遍历每个字符位置，尝试替换为其他字母
        for (int i = 0; i < cur.length(); i++) {
            char temp = chars[i];
            for (char j = 'a'; j <= 'z'; j++) {
                if (temp == j) {
                    continue;
                }
                chars[i] = j;
                String next = new String(chars);
                // 检查生成的单词是否在单词集合中
                if (wordSet.contains(next)) {
                    res.add(next);
                }
            }
            chars[i] = temp;
        }
        return res;
    }

}
