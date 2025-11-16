package backtrack.generateParenthesis;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LuoXuanwei
 * @version 1.0
 * @description 括号生成
 * @date 2025/11/16 19:19
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 */
public class GenerateParenthesis {
    private List<String> res;
    private StringBuilder sb;

    /**
     * 生成所有可能的并且有效的括号组合
     *
     * @param n 括号对数
     * @return 包含所有有效括号组合的字符串列表
     */
    public List<String> generateParenthesis(int n) {
        res = new ArrayList<>();
        sb = new StringBuilder();
        backtrack(0, 0, n);
        return res;
    }

    /**
     * 使用回溯算法生成有效括号组合
     *
     * @param left  当前已使用的左括号数量
     * @param right 当前已使用的右括号数量
     * @param n     目标括号对数
     */
    private void backtrack(int left, int right, int n) {
        // 递归终止条件：左右括号都已使用完
        if (left == n && right == n) {
            res.add(sb.toString());
            return;
        }
        // 添加左括号的条件：左括号数量小于n
        if (left < n) {
            sb.append("(");
            backtrack(left + 1, right, n);
            sb.deleteCharAt(sb.length() - 1);
        }
        // 添加右括号的条件：右括号数量小于左括号数量
        if (left > right) {
            sb.append(")");
            backtrack(left, right + 1, n);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
