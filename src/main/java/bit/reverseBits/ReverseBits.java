package bit.reverseBits;

/**
 * @author LuoXuanwei
 * @version 1.0
 * @description 颠倒二进制位
 * @date 2025/11/27 17:47
 *
 * 颠倒给定的 32 位有符号整数的二进制位。
 */
public class ReverseBits {
    public int reverseBits(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            res += (n & 1) == 1 ? 1 << (31 - i) : 0;
            n = n >> 1;
        }
        return res;
    }
}
