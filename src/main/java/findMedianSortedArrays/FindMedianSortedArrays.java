package findMedianSortedArrays;

/**
 * @author LuoXuanwei
 * @version 1.0
 * @description 寻找两个正序数组的中位数
 * @date 2025/11/22 19:34
 * <p>
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 * 算法的时间复杂度应该为 O(log (m+n)) 。
 */
public class FindMedianSortedArrays {
    /**
     * 找到两个已排序数组的中位数。
     * 时间复杂度要求为 O(log(m+n))，其中 m 和 n 分别是两个数组的长度。
     *
     * @param nums1 第一个已排序的整数数组
     * @param nums2 第二个已排序的整数数组
     * @return 返回两个数组合并后的中位数（double类型）
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int len = len1 + len2;
        // 判断总长度是否为奇数，分别处理奇数和偶数情况
        if (len % 2 == 1) {
            // 奇数时直接查找第 (len/2 + 1) 小的元素
            return findKth(nums1, 0, len1 - 1, nums2, 0, len2 - 1, len / 2 + 1);
        } else {
            // 偶数时取中间两个数的平均值
            return (findKth(nums1, 0, len1 - 1, nums2, 0, len2 - 1, len / 2)
                    + findKth(nums1, 0, len1 - 1, nums2, 0, len2 - 1, len / 2 + 1)) * 0.5;
        }
    }

    /**
     * 在两个有序数组中查找第 k 小的元素。
     * 使用二分法递归实现，在每一步排除一部分不可能包含第 k 小元素的数据。
     *
     * @param nums1  第一个有序数组
     * @param start1 nums1 的起始索引
     * @param end1   nums1 的结束索引
     * @param nums2  第二个有序数组
     * @param start2 nums2 的起始索引
     * @param end2   nums2 的结束索引
     * @param k      要找的第 k 小的元素（从1开始计数）
     * @return 返回第 k 小的元素值
     */
    private int findKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
        // 如果其中一个数组已经遍历完，则直接在另一个数组中取第k小的元素
        if (start1 > end1) {
            return nums2[start2 + k - 1];
        }
        if (start2 > end2) {
            return nums1[start1 + k - 1];
        }

        // 当 k=1 时，即寻找当前最小的元素
        if (k == 1) {
            return Math.min(nums1[start1], nums2[start2]);
        }

        // 计算两个数组中用于比较的中间位置，避免越界
        int mid1 = Math.min(start1 + k / 2 - 1, end1);
        int mid2 = Math.min(start2 + k / 2 - 1, end2);

        // 比较两个中间元素，决定舍弃哪一部分数据
        if (nums1[mid1] < nums2[mid2]) {
            // nums1 中较小的部分可以被舍弃，同时更新 k 值
            return findKth(nums1, mid1 + 1, end1, nums2, start2, end2, k - (mid1 - start1 + 1));
        } else {
            // nums2 中较小的部分可以被舍弃，同时更新 k 值
            return findKth(nums1, start1, end1, nums2, mid2 + 1, end2, k - (mid2 - start2 + 1));
        }
    }

}
