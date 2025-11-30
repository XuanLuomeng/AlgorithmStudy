package number.plusOne;

/**
 * @author LuoXuanwei
 * @version 1.0
 * @description 加一
 * @date 2025/11/30 17:30
 * <p>
 * 给定一个表示 大整数 的整数数组 digits，其中 digits[i] 是整数的第 i 位数字。
 * 这些数字按从左到右，从最高位到最低位排列。这个大整数不包含任何前导 0。
 * 将大整数加 1，并返回结果的数字数组。
 */
public class PlusOne {
    /**
     * 对表示数字的数组进行加一操作
     *
     * @param digits 表示数字的数组，每个元素是0-9的数字，从高位到低位排列
     * @return 加一后的新数组，如果最高位进位则返回长度+1的新数组
     */
    public int[] plusOne(int[] digits) {
        int n = digits.length;
        // 从最低位开始逐位加一处理
        for (int i = n - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                break;
            }
            digits[i] = 0;
        }
        // 处理最高位进位的情况，如999+1=1000
        if (digits[0] == 0) {
            int[] newDigits = new int[n + 1];
            newDigits[0] = 1;
            for (int i = 1; i < n + 1; i++) {
                newDigits[i] = digits[i - 1];
            }
            return newDigits;
        }

        return digits;
    }
}
