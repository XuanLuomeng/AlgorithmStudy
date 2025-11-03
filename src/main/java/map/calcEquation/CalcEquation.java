package map.calcEquation;

import java.util.*;

/**
 * @author LuoXuanwei
 * @version 1.0
 * @description 除法求值
 * @date 2025/11/3 15:45
 * <p>
 * 给你一个变量对数组 equations 和一个实数值数组 values 作为已知条件，其中 equations[i] = [Ai, Bi] 和 values[i] 共同表示等式 Ai / Bi = values[i] 。每个 Ai 或 Bi 是一个表示单个变量的字符串。
 * <p>
 * 另有一些以数组 queries 表示的问题，其中 queries[j] = [Cj, Dj] 表示第 j 个问题，请你根据已知条件找出 Cj / Dj = ? 的结果作为答案。
 * <p>
 * 返回 所有问题的答案 。如果存在某个无法确定的答案，则用 -1.0 替代这个答案。如果问题中出现了给定的已知条件中没有出现的字符串，也需要用 -1.0 替代这个答案。
 * <p>
 * 注意：输入总是有效的。你可以假设除法运算中不会出现除数为 0 的情况，且不存在任何矛盾的结果。
 * <p>
 * 注意：未在等式列表中出现的变量是未定义的，因此无法确定它们的答案。
 */
public class CalcEquation {
    /**
     * 根据给定的方程和值，计算查询中变量之间的比值。
     *
     * @param equations 方程列表，每个元素是一个包含两个字符串的列表，表示两个变量之间的等式关系（如 a/b）
     * @param values    每个方程对应的数值结果（即 a/b 的值）
     * @param queries   查询列表，每个元素是一个包含两个字符串的列表，表示要查询的两个变量之间的比值（如 c/d）
     * @return 返回一个数组，对应每个查询的结果；如果无法确定或变量不存在则返回 -1.0
     */
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        int equationsSize = equations.size();
        // 如果没有方程，则直接返回空数组
        if (equationsSize == 0) {
            return new double[0];
        }

        // 构建图结构：节点是变量名，边是它们之间的比值
        Map<String, Map<String, Double>> graph = new HashMap<>();

        // 遍历所有方程，构建有向图
        for (int i = 0; i < equationsSize; i++) {
            String leftNode = equations.get(i).get(0);
            String rightNode = equations.get(i).get(1);
            Double v = values[i];

            // 初始化节点
            if (!graph.containsKey(leftNode)) {
                graph.put(leftNode, new HashMap<>());
            }

            if (!graph.containsKey(rightNode)) {
                graph.put(rightNode, new HashMap<>());
            }

            // 添加双向边及其权重（正向与反向）
            graph.get(leftNode).put(rightNode, v);
            graph.get(rightNode).put(leftNode, 1 / v);
            // 自身到自身的权重为1.0
            graph.get(leftNode).put(leftNode, 1.0);
            graph.get(rightNode).put(rightNode, 1.0);
        }

        int queriesSize = queries.size();
        double[] ans = new double[queriesSize];
        // 默认填充为-1.0，表示不可达或未找到路径
        Arrays.fill(ans, -1.0);

        // 处理每一个查询
        for (int i = 0; i < queriesSize; i++) {
            String start = queries.get(i).get(0);
            String end = queries.get(i).get(1);
            // 如果起始或终点不在图中，则无法求解
            if (!graph.containsKey(start) || !graph.containsKey(end)) {
                ans[i] = -1.0;
                continue;
            }
            // 使用深度优先搜索查找从start到end的路径，并计算乘积权重
            ans[i] = dfs(start, end, graph, new HashSet<String>(), 1.0);
        }

        return ans;
    }

    /**
     * 使用深度优先搜索在图中寻找从起点到终点的路径，并计算路径上的权重乘积。
     *
     * @param start   当前遍历的起始节点
     * @param end     目标节点
     * @param graph   图结构，存储了各节点间的连接关系及权重
     * @param visited 已访问过的节点集合，防止重复访问形成环路
     * @param weight  到当前节点为止累积的权重乘积
     * @return 若能找到路径则返回最终的权重乘积，否则返回 -1.0
     */
    private double dfs(String start, String end, Map<String, Map<String, Double>> graph,
                       HashSet<String> visited, Double weight) {
        // 找到了目标节点，返回累计权重
        if (start.equals(end)) {
            return weight;
        }
        visited.add(start);
        double ans = -1.0;

        // 遍历当前节点的所有邻居
        for (Map.Entry<String, Double> entry : graph.get(start).entrySet()) {
            String nextNode = entry.getKey();
            double nextWeight = entry.getValue();

            // 只处理尚未访问的节点
            if (!visited.contains(nextNode)) {
                ans = dfs(nextNode, end, graph, visited, weight * nextWeight);
                // 如果找到了有效路径就提前结束循环
                if (ans != -1.0) {
                    break;
                }
            }
        }

        return ans;
    }
}
