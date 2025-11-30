package bit.rangeBitwiseAnd;

/**
 * @author LuoXuanwei
 * @version 1.0
 * @description 数字范围按位与
 * @date 2025/11/30 10:49
 *
 * 给你两个整数 left 和 right ，表示区间 [left, right] ，
 * 返回此区间内所有数字 按位与 的结果（包含 left 、right 端点）。
 */
public class RangeBitwiseAnd {
    public int rangeBitwiseAnd(int left, int right) {
        // 使用 for 循环的i--方式在力扣中会超时
        while(left < right) {
            right &= (right - 1);
        }
        return right;
    }
}
