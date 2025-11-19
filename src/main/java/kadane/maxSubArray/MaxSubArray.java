package kadane.maxSubArray;

/**
 * @author LuoXuanwei
 * @version 1.0
 * @description 最大子数组和
 * @date 2025/11/19 18:46
 *
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 子数组是数组中的一个连续部分。
 */
public class MaxSubArray {
    public int maxSubArray(int[] nums) {
        int ans = Integer.MIN_VALUE;
        int sum = 0;
        for (int num : nums) {
            sum = Math.max(sum + num, num);
            ans = Math.max(ans, sum);
        }
        return ans;
    }
}
