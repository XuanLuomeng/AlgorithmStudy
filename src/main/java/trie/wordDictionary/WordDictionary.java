package trie.wordDictionary;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author LuoXuanwei
 * @version 1.0
 * @description 添加与搜索单词 - 数据结构设计
 * @date 2025/11/9 19:23
 * <p>
 * 请你设计一个数据结构，支持 添加新单词 和 查找字符串是否与任何先前添加的字符串匹配 。
 * <p>
 * 实现词典类 WordDictionary ：
 * <p>
 * WordDictionary() 初始化词典对象
 * void addWord(word) 将 word 添加到数据结构中，之后可以对它进行匹配
 * bool search(word) 如果数据结构中存在字符串与 word 匹配，则返回 true ；否则，返回  false 。word 中可能包含一些 '.' ，每个 . 都可以表示任何一个字母。
 */
public class WordDictionary {
    /**
     * Trie树节点类，用于实现字典树结构
     */
    private class Trie {
        public Trie[] children;
        public boolean isWord;

        /**
         * 构造函数，初始化Trie节点
         * 创建大小为26的子节点数组，表示对应26个英文字母
         * 初始化isWord标志为false
         */
        public Trie() {
            children = new Trie[26];
            isWord = false;
        }
    }

    private Trie root;

    /**
     * WordDictionary构造函数
     * 初始化根节点
     */
    public WordDictionary() {
        root = new Trie();
    }

    /**
     * 添加单词到字典中
     *
     * @param word 需要添加的单词字符串
     */
    public void addWord(String word) {
        Trie cur = root;
        char[] chs = word.toCharArray();
        // 遍历单词中的每个字符，构建Trie树路径
        for (char ch : chs) {
            if (cur.children[ch - 'a'] == null) {
                cur.children[ch - 'a'] = new Trie();
            }
            cur = cur.children[ch - 'a'];
        }
        // 标记单词结尾节点
        cur.isWord = true;
    }

    /**
     * 在字典中搜索指定的单词
     * 支持通配符'.'匹配任意字符
     *
     * @param word 需要搜索的单词，可能包含通配符'.'
     * @return 如果找到匹配的单词返回true，否则返回false
     */
    public boolean search(String word) {
        // 使用队列进行广度优先搜索
        Queue<Trie> queue = new LinkedList<>();
        queue.offer(root);
        char[] chs = word.toCharArray();

        // 逐个字符进行匹配
        for (int i = 0; i < chs.length; i++) {
            int size = queue.size();
            // 处理当前层的所有节点
            for (int j = 0; j < size; j++) {
                Trie cur = queue.poll();
                if (chs[i] == '.') {
                    // 处理通配符'.'的情况，遍历所有非空子节点
                    for (Trie trie : cur.children) {
                        if (trie == null) {
                            continue;
                        }
                        // 如果是最后一个字符且是单词结尾，返回true
                        if (i == chs.length - 1 && trie.isWord) {
                            return true;
                        }
                        queue.offer(trie);
                    }
                } else {
                    // 处理普通字符的情况
                    if (cur.children[chs[i] - 'a'] == null) {
                        continue;
                    } else if (i == chs.length - 1 && cur.children[chs[i] - 'a'].isWord) {
                        // 如果是最后一个字符且是单词结尾，返回true
                        return true;
                    }
                    queue.offer(cur.children[chs[i] - 'a']);
                }
            }
        }

        return false;
    }

}
