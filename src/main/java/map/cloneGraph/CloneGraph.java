package map.cloneGraph;

import java.util.*;

/**
 * @author LuoXuanwei
 * @version 1.0
 * @description 克隆图
 * @date 2025/11/2 12:51
 * <p>
 * 给你无向 连通 图中一个节点的引用，请你返回该图的 深拷贝（克隆）。
 * <p>
 * 图中的每个节点都包含它的值 val（int） 和其邻居的列表（list[Node]）。
 * <p>
 * class Node {
 * public int val;
 * public List<Node> neighbors;
 * }
 * <p>
 * <p>
 * 测试用例格式：
 * <p>
 * 简单起见，每个节点的值都和它的索引相同。例如，第一个节点值为 1（val = 1），第二个节点值为 2（val = 2），以此类推。该图在测试用例中使用邻接列表表示。
 * <p>
 * 邻接列表 是用于表示有限图的无序列表的集合。每个列表都描述了图中节点的邻居集。
 * <p>
 * 给定节点将始终是图中的第一个节点（值为 1）。你必须将 给定节点的拷贝 作为对克隆图的引用返回。
 */
public class CloneGraph {
    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    /**
     * 克隆图结构
     *
     * @param node 原始图的节点
     * @return 克隆后的图的新节点
     */
    private Map<Node, Node> visited = new HashMap<>();

    /**
     * 递归克隆图(深度优先遍历)
     *
     * @param node 原始图的节点
     * @return 克隆后的图
     */
    public Node cloneGraph(Node node) {
        // 如果节点为空，直接返回空
        if (node == null) {
            return node;
        }

        // 如果该节点已经访问过，直接返回克隆过的节点
        if (visited.containsKey(node)) {
            return visited.get(node);
        }

        // 创建当前节点的克隆节点
        Node cloneNode = new Node(node.val, new ArrayList<>());

        // 将原节点和克隆节点存入已访问映射中
        visited.put(node, cloneNode);

        // 递归克隆所有邻居节点
        for (Node neighbor : node.neighbors) {
            cloneNode.neighbors.add(cloneGraph(neighbor));
        }

        return cloneNode;
    }

    /**
     * 使用广度优先搜索克隆无向连通图
     *
     * @param node 图的起始节点，如果为null则表示空图
     * @return 克隆后的图的起始节点，与原图结构相同但节点为全新创建
     */
    public Node cloneGraph2(Node node) {
        if (node == null) {
            return node;
        }

        // 使用哈希表记录已访问的节点及其克隆节点的对应关系
        Map<Node, Node> visited = new HashMap<>();
        // 使用队列进行广度优先遍历
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);
        // 创建起始节点的克隆节点并标记为已访问
        visited.put(node, new Node(node.val, new ArrayList<>()));

        // 广度优先遍历整个图
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            // 遍历当前节点的所有邻居节点
            for (Node neighbor : cur.neighbors) {
                // 如果邻居节点尚未被访问过，则创建其克隆节点并加入队列
                if (!visited.containsKey(neighbor)) {
                    visited.put(neighbor, new Node(neighbor.val, new ArrayList<>()));
                    queue.offer(neighbor);
                }
                // 在克隆图中建立节点间的连接关系
                visited.get(cur).neighbors.add(visited.get(neighbor));
            }
        }

        return visited.get(node);
    }

}
