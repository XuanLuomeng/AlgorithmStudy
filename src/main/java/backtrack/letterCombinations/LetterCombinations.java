package backtrack.letterCombinations;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LuoXuanwei
 * @version 1.0
 * @description 电话号码的字母组合
 * @date 2025/11/11 18:22
 * <p>
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 * <p>
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 */
public class LetterCombinations {
    /**
     * 数字到字母映射数组，索引对应数字2-9
     * abc(2), def(3), ghi(4), jkl(5), mno(6), pqrs(7), tuv(8), wxyz(9)
     */
    private final static String[] strs = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    /**
     * 根据输入的数字字符串，返回所有可能的字母组合
     *
     * @param digits 输入的数字字符串，只包含2-9的数字
     * @return 所有可能的字母组合列表
     */
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();

        // 空字符串直接返回空结果
        if (digits.length() == 0) {
            return res;
        }

        // 使用回溯法生成所有组合
        backtrack(digits, 0, "", res);

        return res;
    }

    /**
     * 回溯算法生成字母组合
     *
     * @param digits    输入的数字字符串
     * @param index     当前处理的数字位置
     * @param curString 当前已生成的字母字符串
     * @param res       结果列表，用于存储所有可能的组合
     */
    private void backtrack(String digits, int index, String curString, List<String> res) {
        // 递归终止条件：已处理完所有数字
        if (index == digits.length()) {
            res.add(curString);
            return;
        }

        // 获取当前数字对应的字母字符串
        String letters = strs[digits.charAt(index) - '2'];
        char[] lettersArr = letters.toCharArray();

        // 遍历当前数字对应的所有字母，递归生成后续组合
        for (char letter : lettersArr) {
            backtrack(digits, index + 1, curString + letter, res);
        }
    }

}
