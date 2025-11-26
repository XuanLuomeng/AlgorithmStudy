package bit.addBinary;

/**
 * @author LuoXuanwei
 * @version 1.0
 * @description 二进制求和
 * @date 2025/11/26 17:29
 * <p>
 * 给你两个二进制字符串 a 和 b ，以二进制字符串的形式返回它们的和。
 */
public class AddBinary {
    /**
     * 将两个二进制字符串相加，返回它们的和（也是二进制字符串）
     *
     * @param a 第一个二进制字符串
     * @param b 第二个二进制字符串
     * @return 两个二进制字符串的和
     */
    public String addBinary(String a, String b) {
        StringBuilder res = new StringBuilder();
        int carry = 0;

        // 从右到左逐位相加两个二进制数
        for (int i = a.length() - 1, j = b.length() - 1; i >= 0 || j >= 0 || carry != 0; i--, j--) {
            int sum = carry;
            sum += i >= 0 ? a.charAt(i) - '0' : 0;
            sum += j >= 0 ? b.charAt(i) - '0' : 0;
            res.append(sum % 2);
            carry = sum / 2;
        }

        // 反转结果字符串得到正确的顺序
        return res.reverse().toString();
    }

}
