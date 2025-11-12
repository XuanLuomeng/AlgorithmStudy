package backtrack.combine;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LuoXuanwei
 * @version 1.0
 * @description 组合
 * @date 2025/11/12 11:55
 *
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 * 你可以按 任何顺序 返回答案。
 */
public class Combine {
    private List<List<Integer>> ans;

    /**
     * 生成从1到n中选择k个数的所有组合
     *
     * @param n 可选择的数字范围上限（包含）
     * @param k 需要选择的数字个数
     * @return 所有可能的k个数的组合列表
     */
    public List<List<Integer>> combine(int n, int k) {
        ans = new ArrayList<>();
        backtrack(n, k, 1, new ArrayList<Integer>());
        return ans;
    }

    /**
     * 使用回溯算法生成所有组合
     *
     * @param n    可选择的数字范围上限（包含）
     * @param k    需要选择的数字个数
     * @param i    当前考虑的起始数字
     * @param path 当前正在构建的组合路径
     */
    private void backtrack(int n, int k, int i, List<Integer> path) {
        // 当路径长度等于k时，找到一个有效组合
        if (path.size() == k) {
            ans.add(new ArrayList<>(path));
            return;
        }

        // 从当前起始位置开始，尝试添加每个可能的数字
        for (int j = i; j <= n; j++) {
            path.add(j);
            backtrack(n, k, j + 1, path);
            path.remove(path.size() - 1);
        }
    }

}
