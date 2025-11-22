package binarySearch.findPeakElement;

/**
 * @author LuoXuanwei
 * @version 1.0
 * @description 寻找峰值
 * @date 2025/11/22 15:50
 * 题目:
 * 峰值元素是指其值严格大于左右相邻值的元素。
 * 给你一个整数数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
 * 你可以假设 nums[-1] = nums[n] = -∞ 。
 * 你必须实现时间复杂度为 O(log n) 的算法来解决此问题。
 */
public class FindPeakElement {
    /**
     * 查找峰值元素的索引
     * 峰值元素是指其值严格大于左右相邻值的元素
     * 因为只需要获取一个峰值而不是最大值，且要求时间复杂度为O(log n)，因此使用二分查找
     *
     * @param nums 输入的整数数组，假设nums[-1] = nums[n] = -∞
     * @return 返回任意一个峰值元素的索引
     */
    public int findPeakElement(int[] nums) {
        int left = 0, right = nums.length - 1;
        // 二分查找寻找峰值
        while (left < right) {
            int mid = (left + right) / 2;
            // 如果中间元素大于右邻元素，说明峰值在左半部分（包括mid）
            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            } else {
                // 否则峰值在右半部分
                left = mid + 1;
            }
        }

        return left;
    }
}
