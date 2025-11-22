package binarySearch.search;

/**
 * @author LuoXuanwei
 * @version 1.0
 * @description 搜索旋转排序数组
 * @date 2025/11/22 17:12
 * <p>
 * 整数数组 nums 按升序排列，数组中的值 互不相同 。
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 向左旋转，使数组变为 [nums[k], nums[k+1], ...,
 * nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 下标 3 上向左旋转后可能变为
 * [4,5,6,7,0,1,2] 。
 * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
 * 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
 */
public class Search {
    /**
     * 在旋转排序数组中搜索目标值
     *
     * @param nums   旋转排序数组
     * @param target 要搜索的目标值
     * @return 目标值在数组中的索引，如果不存在则返回-1
     */
    public int search(int[] nums, int target) {
        // 找到数组中最小元素的索引，即旋转点
        int minIndex = findMinIndex(nums);
        int len = nums.length;
        int left = 0, right = len - 1;

        // 在逻辑上有序的数组段中进行二分查找
        while (left <= right) {
            int mid = (left + right) / 2;
            // 将逻辑索引转换为实际数组索引
            int midIndex = (mid + minIndex) % len;
            if (nums[midIndex] == target) {
                return midIndex;
            } else if (nums[midIndex] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return -1;
    }

    /**
     * 查找数组中最小元素的索引
     *
     * @param nums 输入数组
     * @return 最小元素的索引位置
     */
    private int findMinIndex(int[] nums) {
        int minIndex = 0;
        int minNum = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < minNum) {
                minNum = nums[i];
                minIndex = i;
            }
        }
        return minIndex;
    }
}
