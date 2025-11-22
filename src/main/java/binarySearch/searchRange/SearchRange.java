package binarySearch.searchRange;

/**
 * @author LuoXuanwei
 * @version 1.0
 * @description 在排序数组中查找元素的第一个和最后一个位置
 * @date 2025/11/22 17:33
 *
 * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 * 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
 */
public class SearchRange {
    public int[] searchRange(int[] nums, int target) {
        int[] ans = new int[]{-1, -1};
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                ans[0] = mid;
                break;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        left = ans[0];
        right = ans[0];
        while (left != -1) {
            if (left > 0 && nums[left - 1] == target) {
                left--;
            } else if (right < nums.length - 1 && nums[right + 1] == target) {
                right++;
            } else {
                break;
            }
        }
        ans[0] = left;
        ans[1] = right;
        return ans;
    }
}
