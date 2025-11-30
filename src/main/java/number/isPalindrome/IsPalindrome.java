package number.isPalindrome;

/**
 * @author LuoXuanwei
 * @version 1.0
 * @description 回文字
 * @date 2025/11/30 17:20
 * <p>
 * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
 * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * 例如，121 是回文，而 123 不是。
 */
public class IsPalindrome {
    /**
     * 判断一个整数是否为回文数
     * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数
     *
     * @param x 待判断的整数
     * @return 如果是回文数返回true，否则返回false
     */
    public boolean isPalindrome(int x) {
        // 负数不是回文数，末尾为0但不是0本身的数也不是回文数
        if (x < 0 || x % 10 == 0 && x != 0) {
            return false;
        }

        int revertNum = 0;
        // 将数字的后半部分反转，直到后半部分大于等于前半部分
        while (revertNum < x) {
            revertNum = revertNum * 10 + x % 10;
            x /= 10;
        }

        // 判断奇数位和偶数位的情况
        return revertNum == x || revertNum / 10 == x;
    }
}
