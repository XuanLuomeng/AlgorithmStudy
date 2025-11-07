package map.minMutation;

import java.util.*;

/**
 * @author LuoXuanwei
 * @version 1.0
 * @description 最小基因变化
 * @date 2025/11/7 19:21
 * <p>
 * 基因序列可以表示为一条由 8 个字符组成的字符串，其中每个字符都是 'A'、'C'、'G' 和 'T' 之一。
 * <p>
 * 假设我们需要调查从基因序列 start 变为 end 所发生的基因变化。一次基因变化就意味着这个基因序列中的一个字符发生了变化。
 * <p>
 * 例如，"AACCGGTT" --> "AACCGGTA" 就是一次基因变化。
 * 另有一个基因库 bank 记录了所有有效的基因变化，只有基因库中的基因才是有效的基因序列。（变化后的基因必须位于基因库 bank 中）
 * <p>
 * 给你两个基因序列 start 和 end ，以及一个基因库 bank ，请你找出并返回能够使 start 变化为 end 所需的最少变化次数。如果无法完成此基因变化，返回 -1 。
 * <p>
 * 注意：起始基因序列 start 默认是有效的，但是它并不一定会出现在基因库中。
 */
public class MinMutation {
    /**
     * 计算基因序列从起始基因变异到目标基因所需的最小变异次数
     * 使用广度优先搜索(BFS)算法来寻找最短路径
     *
     * @param startGene 起始基因序列
     * @param endGene   目标基因序列
     * @param bank      基因库，包含所有有效的基因序列
     * @return 返回最小变异次数，如果无法变异到目标基因则返回-1
     */
    public int minMutation(String startGene, String endGene, String[] bank) {
        // 如果起始基因和目标基因相同，不需要变异
        if (startGene.equals(endGene)) {
            return 0;
        }

        // 将基因库转换为HashSet以提高查找效率
        Set<String> bankSet = new HashSet<>(Arrays.asList(bank));
        Set<String> visited = new HashSet<>();

        // 如果目标基因不在基因库中，无法完成变异
        if (!bankSet.contains(endGene)) {
            return -1;
        }

        // 定义基因字符集合和初始化变量
        String[] chars = new String[]{"A", "C", "G", "T"};
        int step = 0;
        Queue<String> queue = new LinkedList<>();
        queue.offer(startGene);
        visited.add(startGene);

        // BFS搜索过程
        while (!queue.isEmpty()) {
            int size = queue.size();
            // 遍历当前层的所有基因序列
            for (int i = 0; i < size; i++) {
                String cur = queue.poll();
                // 对当前基因序列的每一位尝试四种可能的变异
                for (int j = 0; j < 8; j++) {
                    for (int k = 0; k < 4; k++) {
                        StringBuffer newGene = new StringBuffer(cur);
                        newGene.setCharAt(j, chars[k].charAt(0));

                        // 如果变异后的基因序列等于目标基因，返回步数
                        if (newGene.toString().equals(endGene)) {
                            return step + 1;
                        }

                        // 如果变异后的基因序列未访问过且在基因库中，则加入队列
                        if (!visited.contains(newGene.toString()) && bankSet.contains(newGene.toString())) {
                            queue.offer(newGene.toString());
                            visited.add(newGene.toString());
                        }
                    }
                }
            }
            step++;
        }

        // 无法变异到目标基因
        return -1;
    }

}
