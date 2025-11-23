package stack.findMaximizedCapital;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author LuoXuanwei
 * @version 1.0
 * @description IPO
 * @date 2025/11/23 16:47
 * <p>
 * 假设 力扣（LeetCode）即将开始 IPO 。为了以更高的价格将股票卖给风险投资公司，力扣 希望在 IPO 之前开展一些项目以增加其资本。 由于资源有限，
 * 它只能在 IPO 之前完成最多 k 个不同的项目。帮助 力扣 设计完成最多 k 个不同项目后得到最大总资本的方式。
 * 给你 n 个项目。对于每个项目 i ，它都有一个纯利润 profits[i] ，和启动该项目需要的最小资本 capital[i] 。
 * 最初，你的资本为 w 。当你完成一个项目时，你将获得纯利润，且利润将被添加到你的总资本中。
 * 总而言之，从给定项目中选择 最多 k 个不同项目的列表，以 最大化最终资本 ，并输出最终可获得的最多资本。
 * 答案保证在 32 位有符号整数范围内。
 */
public class FindMaximizedCapital {
    /**
     * 找到最大化资本的投资策略
     * 给定k个投资机会，初始资本w，以及每个项目的利润和所需资本，计算能获得的最大资本
     *
     * @param k       最多可以进行的投资项目数量
     * @param w       初始资本
     * @param profits 每个项目的利润数组
     * @param capital 每个项目所需的启动资本数组
     * @return 完成最多k个项目后能够获得的最大资本
     */
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        // 将项目按所需资本从小到大排序，便于后续处理
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < profits.length; i++) {
            list.add(new int[]{capital[i], profits[i]});
        }
        list.sort((a, b) -> a[0] - b[0]);

        // 使用最大堆存储当前可投资项目的所有利润，每次选择利润最大的项目
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        int i = 0;

        // 进行最多k轮投资选择
        while (k-- > 0) {
            // 将所有当前资本可以启动的项目加入优先队列
            while (i < list.size() && list.get(i)[0] <= w) {
                pq.offer(list.get(i++)[1]);
            }
            // 如果没有可投资的项目，则提前结束
            if (pq.isEmpty()) {
                break;
            }
            // 选择利润最大的项目进行投资，并更新当前资本
            w += pq.poll();
        }
        return w;
    }
}
