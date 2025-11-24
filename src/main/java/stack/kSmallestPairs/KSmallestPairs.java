package stack.kSmallestPairs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author LuoXuanwei
 * @version 1.0
 * @description 查找和最小的 K 对数字
 * @date 2025/11/24 10:17
 *
 * 给定两个以 非递减顺序排列 的整数数组 nums1 和 nums2 , 以及一个整数 k 。
 * 定义一对值 (u,v)，其中第一个元素来自 nums1，第二个元素来自 nums2 。
 * 请找到和最小的 k 个数对 (u1,v1),  (u2,v2)  ...  (uk,vk) 。
 */
public class KSmallestPairs {
    /**
     * 找到两个有序数组中和最小的k个数对
     *
     * @param nums1 第一个有序数组
     * @param nums2 第二个有序数组
     * @param k 需要返回的数对数量
     * @return 包含最小和的k个数对的列表
     */
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        // 使用优先队列维护当前最小的数对和
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        // 初始化优先队列，将nums1中前k个元素与nums2[0]组成的数对加入队列
        for (int i = 0; i < Math.min(nums1.length, k); i++) {
            pq.offer(new int[]{nums1[i] + nums2[0], i, 0});
        }

        List<List<Integer>> ans = new ArrayList<>();

        // 依次取出k个最小的数对
        while (k-- > 0 && !pq.isEmpty()) {
            int[] cur = pq.poll();
            int i = cur[1], j = cur[2];

            // 如果nums2中还有后续元素，则将当前nums1[i]与nums2[j+1]组成的数对加入队列
            if (j + 1 < nums2.length) {
                pq.offer(new int[]{nums1[i] + nums2[j + 1], i, j + 1});
            }

            // 将当前数对添加到结果列表中
            ans.add(new ArrayList<>(Arrays.asList(nums1[i], nums2[j])));
        }

        return ans;
    }
}
