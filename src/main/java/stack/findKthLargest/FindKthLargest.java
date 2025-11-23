package stack.findKthLargest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author LuoXuanwei
 * @version 1.0
 * @description 数组中的第K个最大元素
 * @date 2025/11/23 15:05
 * <p>
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
 */
public class FindKthLargest {
    /**
     * 查找数组中第k大的元素
     *
     * @param nums 整数数组
     * @param k    要查找的第k大元素的位置（从1开始计数）
     * @return 数组中第k大的元素
     */
    public int findKthLargest(int[] nums, int k) {
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }

        return findKthLargest(list, k);
    }

    /**
     * 使用快速选择算法查找列表中第k大的元素
     *
     * @param nums 整数列表
     * @param k    要查找的第k大元素的位置（从1开始计数）
     * @return 列表中第k大的元素
     */
    private int findKthLargest(List<Integer> nums, int k) {
        Random random = new Random();
        // 随机选择基准元素
        int pivot = nums.get(random.nextInt(nums.size()));
        List<Integer> bigger = new ArrayList<>();
        List<Integer> smaller = new ArrayList<>();
        List<Integer> equal = new ArrayList<>();

        // 将元素分为三部分：大于基准、小于基准、等于基准
        for (int num : nums) {
            if (num > pivot) {
                bigger.add(num);
            } else if (num < pivot) {
                smaller.add(num);
            } else {
                equal.add(num);
            }
        }

        // 递归查找第k大元素所在的分区
        if (bigger.size() >= k) {
            return findKthLargest(bigger, k);
        } else if (nums.size() - smaller.size() < k) {
            return findKthLargest(smaller, k - (nums.size() - smaller.size()));
        } else {
            return pivot;
        }
    }
}
