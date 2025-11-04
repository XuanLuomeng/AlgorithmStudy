package map.canFinish;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author LuoXuanwei
 * @version 1.0
 * @description 课程表
 * @date 2025/11/4 19:21
 * <p>
 * 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
 * <p>
 * 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。
 * <p>
 * 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
 * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
 * <p>
 */
public class CanFinish {
    private int[] visited;
    private int[] studyNum;
    private List<List<Integer>> edges;
    private boolean flag;

    /**
     * 判断是否可以完成所有课程的学习
     *
     * @param numCourses    课程总数
     * @param prerequisites 课程依赖关系数组，每个元素[ai, bi]表示学习课程ai前必须先完成课程bi
     * @return 如果可以完成所有课程返回true，否则返回false
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 初始化邻接表，用于存储课程之间的依赖关系
        edges = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<>());
        }
        // 初始化访问状态数组和结果标志
        visited = new int[numCourses];
        flag = true;

        // 构建邻接表，将依赖关系转换为有向图
        for (int[] prerequisite : prerequisites) {
            edges.get(prerequisite[1]).add(prerequisite[0]);
        }

        // 对每个未访问的节点进行深度优先搜索，检测是否存在环
        for (int i = 0; i < numCourses && flag; i++) {
            if (visited[i] == 0) {
                dfs(i);
            }
        }

        return flag;
    }

    /**
     * 深度优先搜索检测图中是否存在环
     *
     * @param i 当前访问的节点（课程）
     */
    private void dfs(int i) {
        // 将当前节点标记为正在搜索中
        visited[i] = 1;
        // 遍历当前节点的所有邻接节点
        for (int j : edges.get(i)) {
            // 如果邻接节点未访问，则递归搜索
            if (visited[j] == 0) {
                dfs(j);
                if (!flag) {
                    return;
                }
                // 如果邻接节点正在搜索中，说明存在环，设置标志为false
            } else if (visited[j] == 1) {
                flag = false;
                return;
            }
        }

        // 当前节点搜索完成，标记为已完成
        visited[i] = 2;
    }

    /**
     * 判断是否可以完成所有课程的学习
     * 使用拓扑排序算法解决课程表问题
     *
     * @param numCourses    课程总数
     * @param prerequisites 课程依赖关系数组，每个元素[ai, bi]表示学习课程ai前必须先完成课程bi
     * @return 如果可以完成所有课程返回true，否则返回false
     */
    public boolean canFinishTwo(int numCourses, int[][] prerequisites) {
        // 初始化邻接表，用于存储每个课程的后续课程
        edges = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<>());
        }

        // 初始化每个课程的入度数组（需要先修的课程数量）
        studyNum = new int[numCourses];

        // 构建图的邻接表和入度数组
        for (int[] prerequisite : prerequisites) {
            edges.get(prerequisite[1]).add(prerequisite[0]);
            studyNum[prerequisite[0]]++;
        }

        // 将所有入度为0的课程加入队列（可以立即开始学习的课程）
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (studyNum[i] == 0) {
                queue.offer(i);
            }
        }

        // 拓扑排序过程：逐个处理可以学习的课程（最终循环次数必然 count <= numCourses）
        // 一直无法开始学习的那几门课必然是成环的
        int count = 0;
        while (!queue.isEmpty()) {
            count++;
            int cur = queue.poll();
            // 遍历当前课程的所有后续课程
            for (int next : edges.get(cur)) {
                studyNum[next]--;
                // 如果后续课程的入度变为0，说明可以开始学习了
                if (studyNum[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        // 如果处理的课程数量等于总课程数，说明可以完成所有课程
        return count == numCourses;
    }
}
