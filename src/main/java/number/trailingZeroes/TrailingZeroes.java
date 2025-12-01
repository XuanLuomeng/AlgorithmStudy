package number.trailingZeroes;

/**
 * @author LuoXuanwei
 * @version 1.0
 * @description 阶乘后的零
 * @date 2025/12/1 22:15
 * <p>
 * 给定一个整数 n ，返回 n! 结果中尾随零的数量。
 * 提示 n! = n * (n - 1) * (n - 2) * ... * 3 * 2 * 1
 */
public class TrailingZeroes {
    /**
     * 计算n!（n的阶乘）结果末尾连续0的个数
     * <p>
     * 解题思路：末尾的0是由因子2和5相乘得到的，而因子2的个数总是比因子5多，
     * 所以只需要统计因子5的个数即可。通过不断除以5并累加商值，可以统计出
     * 所有包含因子5的数字贡献的因子5总个数。
     *
     * @param n 非负整数，表示要求阶乘的数
     * @return n!末尾连续0的个数
     */
    public int trailingZeroes(int n) {
        int ans = 0;
        // 统计因子5的个数：每次将n除以5，商值表示当前轮次中能被5^i整除的数的个数
        while (n > 0) {
            n /= 5;
            ans += n;
        }
        return ans;
    }
}
