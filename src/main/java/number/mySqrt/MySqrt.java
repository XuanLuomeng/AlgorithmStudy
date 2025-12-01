package number.mySqrt;

/**
 * @author LuoXuanwei
 * @version 1.0
 * @description x 的平方根
 * @date 2025/12/1 23:19
 * <p>
 * 给你一个非负整数 x ，计算并返回 x 的 算术平方根 。
 * 由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。
 * 注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5 。
 */
public class MySqrt {
    /**
     * 计算非负整数x的平方根，结果只保留整数部分（向下取整）
     * 使用二分查找算法实现，避免使用内置的指数函数或运算符
     *
     * @param x 非负整数，范围在0到2^31-1之间
     * @return 返回x的平方根的整数部分，如果x为负数则返回-1
     */
    public int mySqrt(int x) {
        // 初始化二分查找的左右边界
        int left = 0;
        int right = x;
        int ans = -1;

        // 二分查找过程：寻找最大的整数ans，使得ans*ans <= x
        while (left <= right) {
            // 计算中间位置，避免整数溢出
            int mid = left + (right - left) / 2;

            // 判断mid的平方与x的关系
            if ((long)mid * mid > x) {
                // mid的平方大于x，说明答案在左半部分
                right = mid - 1;
            } else {
                // mid的平方小于等于x，记录当前可能的答案，继续在右半部分查找
                ans = mid;
                left = mid + 1;
            }
        }
        return ans;
    }
}
