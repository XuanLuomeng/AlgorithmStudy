package binarySearch.searchInsert;

/**
 * @author LuoXuanwei
 * @version 1.0
 * @description 搜索插入位置
 * @date 2025/11/20 17:30
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * <p>
 * 请必须使用时间复杂度为 O(log n) 的算法。
 */
public class SearchInsert {
    /**
     * 在有序数组中搜索目标值的位置，如果目标值不存在则返回其应该插入的位置
     *
     * @param nums   有序数组（升序）
     * @param target 要搜索的目标值
     * @return 目标值在数组中的索引位置，或应该插入的位置
     */
    public int searchInsert(int[] nums, int target) {
        return binarySearch(nums, target, 0, nums.length - 1);
    }

    /**
     * 使用二分查找在有序数组中搜索目标值
     *
     * @param nums   有序数组（升序）
     * @param target 要搜索的目标值
     * @param left   搜索范围的左边界
     * @param right  搜索范围的右边界
     * @return 目标值在数组中的索引位置，或应该插入的位置
     */
    private int binarySearch(int[] nums, int target, int left, int right) {
        // 递归终止条件：当左边界超过右边界时，说明未找到目标值，返回插入位置
        if (left > right) {
            return left;
        }
        // 计算中间位置
        int mid = (left + right) / 2;
        // 找到目标值，直接返回索引
        if (nums[mid] == target) {
            return mid;
            // 中间值大于目标值，在左半部分继续查找
        } else if (nums[mid] > target) {
            return binarySearch(nums, target, left, mid - 1);
            // 中间值小于目标值，在右半部分继续查找
        } else {
            return binarySearch(nums, target, mid + 1, right);
        }
    }
}
