package backtrack.combinationSum;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LuoXuanwei
 * @version 1.0
 * @description 组合总和
 * @date 2025/11/14 23:24
 * <p>
 * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
 * <p>
 * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
 * <p>
 * 对于给定的输入，保证和为 target 的不同组合数少于 150 个。
 */
public class CombinationSum {
    private int sum;

    private List<List<Integer>> ans;

    /**
     * 组合总和
     *
     * @param candidates 候选数组，包含可重复使用的数字
     * @param target     目标值，需要找到和为目标值的所有组合
     * @return 返回所有和等于目标值的组合列表
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        ans = new ArrayList<>();
        sum = 0;

        backtrack(candidates, target, new ArrayList<Integer>(), 0);

        return ans;
    }

    /**
     * 回溯算法实现组合总和
     *
     * @param candidates 候选数组
     * @param target     目标值
     * @param list       当前正在构建的组合
     * @param index      当前遍历的起始索引，用于避免重复组合
     */
    private void backtrack(int[] candidates, int target, List<Integer> list, int index) {
        // 剪枝：如果当前和已经超过或等于目标值
        if (sum >= target) {
            // 如果正好等于目标值，将当前组合加入结果集
            if (sum == target) {
                ans.add(new ArrayList<>(list));
            }
            return;
        }

        // 遍历候选数组，从指定索引开始避免重复组合
        for (int i = index; i < candidates.length; i++) {
            int candidate = candidates[i];
            list.add(candidate);
            sum += candidate;

            // 递归调用，注意索引传入i而不是i+1，允许重复使用当前元素，不允许访问索引i之前的元素(好汉不吃回头草)
            backtrack(candidates, target, list, i);

            // 回溯操作：移除最后一个元素并恢复sum值
            list.remove(list.size() - 1);
            sum -= candidate;
        }
    }

}
