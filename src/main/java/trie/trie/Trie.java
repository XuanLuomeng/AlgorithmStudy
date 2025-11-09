package trie.trie;

/**
 * @author LuoXuanwei
 * @version 1.0
 * @description 实现 Trie (前缀树)
 * @date 2025/11/9 15:37
 * Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。
 * 这一数据结构有相当多的应用情景，例如自动补全和拼写检查。
 * <p>
 * 请你实现 Trie 类：
 * <p>
 * Trie() 初始化前缀树对象。
 * void insert(String word) 向前缀树中插入字符串 word 。
 * boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
 * boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。
 */
public class Trie {
    /**
     * Trie树的节点类
     * 用于表示Trie树中的每个节点，包含指向子节点的数组和标记是否为单词结尾的布尔值
     */
    private class Node {
        public Node[] next = new Node[26];
        public boolean isWord;
    }

    private Node root;

    /**
     * 构造函数
     * 初始化Trie树，创建根节点
     */
    public Trie() {
        root = new Node();
    }

    /**
     * 向Trie树中插入一个单词
     *
     * @param word 需要插入的单词字符串
     */
    public void insert(String word) {
        Node cur = root;
        char[] chs = word.toCharArray();
        // 遍历单词中的每个字符，构建Trie树路径
        for (char ch : chs) {
            if (cur.next[ch - 'a'] == null) {
                cur.next[ch - 'a'] = new Node();
            }
            cur = cur.next[ch - 'a'];
        }
        // 标记单词结尾
        cur.isWord = true;
    }

    /**
     * 在Trie树中搜索一个完整的单词
     *
     * @param word 需要搜索的单词字符串
     * @return 如果单词存在于Trie树中返回true，否则返回false
     */
    public boolean search(String word) {
        Node cur = root;
        char[] chs = word.toCharArray();
        // 遍历单词中的每个字符，检查路径是否存在
        for (char ch : chs) {
            if (cur.next[ch - 'a'] == null) {
                return false;
            }
            cur = cur.next[ch - 'a'];
        }
        // 检查是否为完整的单词结尾
        return cur.isWord;
    }

    /**
     * 检查Trie树中是否存在以指定前缀开头的单词
     *
     * @param prefix 需要检查的前缀字符串
     * @return 如果存在以该前缀开头的单词返回true，否则返回false
     */
    public boolean startsWith(String prefix) {
        Node cur = root;
        char[] chs = prefix.toCharArray();
        // 遍历前缀中的每个字符，检查路径是否存在
        for (char ch : chs) {
            if (cur.next[ch - 'a'] == null) {
                return false;
            }
            cur = cur.next[ch - 'a'];
        }
        // 只要前缀路径存在即返回true
        return true;
    }

}
