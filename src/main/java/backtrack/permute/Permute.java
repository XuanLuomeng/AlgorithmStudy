package backtrack.permute;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LuoXuanwei
 * @version 1.0
 * @description 全排列
 * @date 2025/11/12 15:14
 *
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 */
public class Permute {
    private List<List<Integer>> ans;

    /**
     * 生成数组的所有全排列
     *
     * @param nums 输入的整数数组，用于生成全排列
     * @return 包含所有可能排列的二维列表，每个子列表代表一种排列
     */
    public List<List<Integer>> permute(int[] nums) {
        ans = new ArrayList<>();

        backtrack(nums, new ArrayList<Integer>());

        return ans;
    }

    /**
     * 使用回溯算法递归生成全排列
     *
     * @param nums 原始数组
     * @param list 当前正在构建的排列
     */
    private void backtrack(int[] nums, ArrayList<Integer> list) {
        // 递归终止条件：当前排列长度等于原数组长度时，找到一个完整排列
        if (list.size() == nums.length) {
            ans.add(new ArrayList<>(list));
            return;
        }

        // 遍历数组中的每个数字，尝试将其加入当前排列
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            // 跳过已使用过的数字
            if (list.contains(num)) {
                continue;
            }
            list.add(num);
            backtrack(nums, list);
            list.remove(list.size() - 1);
        }
    }

}
