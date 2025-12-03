package number.maxPoints;

/**
 * @author LuoXuanwei
 * @version 1.0
 * @description 直线上最多的点数
 * @date 2025/12/3 20:59
 *
 * 给你一个数组 points ，其中 points[i] = [xi, yi] 表示 X-Y 平面上的一个点。求最多有多少个点在同一条直线上。
 */
public class MaxPoints {
    /**
     * 计算最多有多少个点在同一条直线上
     *
     * @param points 二维数组，每个元素表示一个点的坐标 [x, y]
     * @return 返回在同一条直线上的最大点数
     */
    public int maxPoints(int[][] points) {
        int n = points.length;
        // 如果点数小于等于2，所有点都在同一条直线上
        if (n <= 2) {
            return n;
        }
        int ans = 0;

        // 遍历所有可能的点对，作为直线上的两个基准点
        for (int i = 0; i < n; i++) {
            int[] x = points[i];
            for (int j = i + 1; j < n; j++) {
                int cnt = 2; // 初始包含两个基准点
                int[] y = points[j];

                // 检查其他点是否与当前两点共线
                for (int k = j + 1; k < n; k++) {
                    int[] z = points[k];
                    // 通过向量叉积判断三点是否共线
                    // 向量(x,y)和向量(x,z)平行则三点共线
                    int dx = x[0] - y[0];
                    int dy = x[1] - y[1];
                    int dz = x[0] - z[0];
                    int dw = x[1] - z[1];
                    if (dx * dw == dy * dz) {
                        cnt++;
                    }
                }
                ans = Math.max(ans, cnt);
            }
        }
        return ans;
    }
}
