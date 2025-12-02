package number.myPow;

/**
 * @author LuoXuanwei
 * @version 1.0
 * @description Pow(x, n)
 * @date 2025/12/2 21:20
 *
 * 实现 pow(x, n) ，即计算 x 的整数 n 次幂函数（即，xn ）。
 */
public class MyPow {
    /**
     * 计算x的n次幂
     * 使用快速幂算法实现，时间复杂度为O(log n)
     *
     * @param x 底数
     * @param n 指数
     * @return x的n次幂的结果
     */
    public double myPow(double x, int n) {
        // 处理底数为0的特殊情况
        if (x == 0.0d) {
            return 0.0d;
        }

        // 将指数转换为long类型，避免负数取反时溢出
        long b = n;
        double res = 1.0;

        // 如果指数为负数，转换为正数计算，并将底数取倒数
        if (b < 0) {
            x = 1 / x;
            b = -b;
        }

        // 快速幂算法核心逻辑
        while (b > 0) {
            // 如果指数的最低位为1，则将当前底数乘入结果
            if ((b & 1) == 1) {
                res *= x;
            }
            // 底数平方，指数右移一位（相当于除以2）
            x *= x;
            b >>= 1;
        }

        return res;
    }
}
