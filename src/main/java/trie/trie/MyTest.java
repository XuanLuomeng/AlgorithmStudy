package trie.trie;

/**
 * @author LuoXuanwei
 * @version 1.0
 * @description 实现 Trie 前缀树启动类
 * @date 2025/11/9 15:38
 */
public class MyTest {
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));// 返回 True
        System.out.println(trie.search("app"));     // 返回 False
        System.out.println(trie.startsWith("app")); // 返回 True
        trie.insert("app");
        System.out.println(trie.search("app"));     // 返回 True
    }
}
