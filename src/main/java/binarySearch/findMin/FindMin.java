package binarySearch.findMin;

/**
 * @author LuoXuanwei
 * @version 1.0
 * @description 寻找旋转排序数组中的最小值
 * @date 2025/11/22 17:42
 * <p>
 * 已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,2,4,5,6,7] 在变化后可能得到：
 * 若旋转 4 次，则可以得到 [4,5,6,7,0,1,2]
 * 若旋转 7 次，则可以得到 [0,1,2,4,5,6,7]
 * 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
 * <p>
 * 给你一个元素值 互不相同 的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
 * <p>
 * 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
 */
public class FindMin {
    /**
     * 在旋转排序数组中查找最小值
     *
     * @param nums 输入的旋转排序数组
     * @return 数组中的最小值
     */
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;

        // 如果数组没有旋转，则第一个元素就是最小值
        if (nums[left] < nums[right]) {
            return nums[left];
        }

        // 使用二分查找寻找最小值
        while (left < right) {
            // 处理只剩两个元素的情况
            if (left + 1 == right) {
                return Math.min(nums[left], nums[right]);
            }

            int mid = left + (right - left) / 2;

            // 根据数组的有序性判断最小值所在区间
            if (nums[left] < nums[mid]) {
                // 左边有序，最小值在右半部分
                left = mid;
            } else {
                // 右边有序，最小值在左半部分（包括mid位置）
                right = mid;
            }
        }
        return nums[left];
    }
}
