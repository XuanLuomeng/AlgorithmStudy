package map.ladderLength;

import java.util.Arrays;
import java.util.List;

/**
 * @author LuoXuanwei
 * @version 1.0
 * @description TODO
 * @date 2025/11/8 15:24
 */
public class Test {
    public static void main(String[] args) {
        LadderLength ladderLength = new LadderLength();
        String beginWord = "ymain";
        String endWord = "oecij";
        List<String> wordList = Arrays.asList("ymann","yycrj","oecij",
                "ymcnj","yzcrj","yycij","xecij","yecij","ymanj","yzcnj","ymain");
        System.out.println(ladderLength.ladderLength(beginWord, endWord, wordList));
    }
}
