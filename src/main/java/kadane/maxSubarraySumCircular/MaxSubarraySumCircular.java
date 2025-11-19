package kadane.maxSubarraySumCircular;

/**
 * @author LuoXuanwei
 * @version 1.0
 * @description 环形子数组的最大和
 * @date 2025/11/19 18:59
 * <p>
 * 给定一个长度为 n 的环形整数数组 nums ，返回 nums 的非空 子数组 的最大可能和 。
 * <p>
 * 环形数组 意味着数组的末端将会与开头相连呈环状。形式上， nums[i] 的下一个元素是 nums[(i + 1) % n] ， nums[i] 的前一个元素是 nums[(i - 1 + n) % n] 。
 * <p>
 * 子数组 最多只能包含固定缓冲区 nums 中的每个元素一次。形式上，对于子数组 nums[i], nums[i + 1], ..., nums[j] ，不存在 i <= k1, k2 <= j 其中 k1 % n == k2 % n 。
 */
public class MaxSubarraySumCircular {
    /**
     * 计算环形数组中连续子数组的最大和
     *
     * 解题思路：
     * 1. 对于环形数组，最大子数组要么在中间（普通情况），要么跨越首尾（环形情况）
     * 2. 普通情况：使用Kadane算法求最大子数组和
     * 3. 环形情况：总和减去最小子数组和即为跨越首尾的最大和
     * 4. 特殊情况：当所有元素都为负数时，直接返回最大元素
     *
     * @param nums 输入的整数数组
     * @return 环形数组中连续子数组的最大和
     */
    public int maxSubarraySumCircular(int[] nums) {
        // 初始化最大值和最小值变量
        int maxAns = Integer.MIN_VALUE;  // 全局最大子数组和
        int maxSum = 0;                  // 当前最大子数组和
        int minAns = Integer.MAX_VALUE;  // 全局最小子数组和
        int minSum = 0;                  // 当前最小子数组和
        int sum = 0;                     // 数组总和

        // 遍历数组，同时计算最大子数组和、最小子数组和以及总和
        for (int num : nums) {
            // 更新当前最大子数组和（Kadane算法核心）
            maxSum = Math.max(maxSum + num, num);
            // 更新全局最大子数组和
            maxAns = Math.max(maxAns, maxSum);

            // 更新当前最小子数组和
            minSum = Math.min(minSum + num, num);
            // 更新全局最小子数组和
            minAns = Math.min(minAns, minSum);

            // 累加数组总和
            sum += num;
        }

        // 如果最大值小于0，说明所有元素都是负数，直接返回最大元素
        // 否则返回普通情况和环形情况的最大值
        return maxAns < 0 ? maxAns : Math.max(maxAns, sum - minAns);
    }

}
