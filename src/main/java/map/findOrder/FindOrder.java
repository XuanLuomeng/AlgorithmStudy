package map.findOrder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author LuoXuanwei
 * @version 1.0
 * @description 课程表 II
 * @date 2025/11/5 18:32
 * <p>
 * 现在你总共有 numCourses 门课需要选，记为 0 到 numCourses - 1。
 * 给你一个数组 prerequisites ，其中 prerequisites[i] = [ai, bi] ，表示在选修课程 ai 前 必须 先选修 bi 。
 * <p>
 * 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示：[0,1] 。
 * 返回你为了学完所有课程所安排的学习顺序。可能会有多个正确的顺序，你只要返回 任意一种 就可以了。如果不可能完成所有课程，返回 一个空数组 。
 */
public class FindOrder {
    // 存储图的邻接表结构
    private List<List<Integer>> edges;
    // 记录每个节点的访问状态：0-未访问，1-正在访问，2-已完成访问
    private int[] visited;
    // 记录每门课程的入度（即先修课程数量）
    private int[] studyNum;
    // 标志是否可以完成所有课程（是否存在环）
    private boolean flag;
    // 存储最终的学习顺序结果
    private int[] result;
    // 用于填充结果数组的索引指针
    private int index;

    /**
     * 使用深度优先搜索实现拓扑排序，返回课程学习顺序
     *
     * @param numCourses    总课程数
     * @param prerequisites 课程依赖关系数组，每个元素为 [ai, bi] 表示 ai 依赖于 bi
     * @return 可行的课程学习顺序数组，若无法完成所有课程则返回空数组
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // 初始化邻接表
        edges = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<>());
        }

        result = new int[numCourses];
        flag = true;
        index = numCourses - 1;

        // 构建邻接表：bi -> ai
        for (int[] prerequisite : prerequisites) {
            edges.get(prerequisite[1]).add(prerequisite[0]);
        }

        visited = new int[numCourses];
        // 对每个未访问的节点进行DFS
        for (int i = 0; i < numCourses && flag; i++) {
            if (visited[i] == 0) {
                dfs(i);
            }
        }

        return flag ? result : new int[0];
    }

    /**
     * 深度优先搜索遍历图，并检测是否存在环
     *
     * @param i 当前访问的课程编号
     */
    private void dfs(int i) {
        // 标记当前节点为正在访问
        visited[i] = 1;
        // 遍历当前节点的所有邻接节点
        for (int j : edges.get(i)) {
            if (visited[j] == 0) {
                // 如果邻接节点未访问，则递归访问
                dfs(j);
                if (!flag) {
                    return;
                }
            } else if (visited[j] == 1) {
                // 如果邻接节点正在访问中，说明存在环
                flag = false;
                return;
            }
        }

        // 标记当前节点为已完成访问，并将其加入结果数组
        visited[i] = 2;
        result[index--] = i;
    }

    /**
     * 使用广度优先搜索（Kahn算法）实现拓扑排序，返回课程学习顺序
     *
     * @param numCourses    总课程数
     * @param prerequisites 课程依赖关系数组，每个元素为 [ai, bi] 表示 ai 依赖于 bi
     * @return 可行的课程学习顺序数组，若无法完成所有课程则返回空数组
     */
    public int[] findOrderTwo(int numCourses, int[][] prerequisites) {
        // 初始化邻接表
        edges = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<>());
        }

        result = new int[numCourses];
        flag = true;

        // 初始化入度数组
        studyNum = new int[numCourses];
        // 构建邻接表和入度数组
        for (int[] prerequisite : prerequisites) {
            edges.get(prerequisite[1]).add(prerequisite[0]);
            studyNum[prerequisite[0]]++;
        }

        // 将所有入度为0的节点加入队列
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (studyNum[i] == 0) {
                queue.offer(i);
            }
        }

        index = 0;

        // BFS处理队列中的节点
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            result[index++] = cur;
            // 更新邻接节点的入度
            for (int next : edges.get(cur)) {
                studyNum[next]--;
                if (studyNum[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        // 如果处理的节点数等于总课程数，说明可以完成所有课程
        return index == numCourses ? result : new int[0];
    }
}
