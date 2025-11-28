package bit.singleNumber;

/**
 * @author LuoXuanwei
 * @version 1.0
 * @description 只出现一次的数字 II
 * @date 2025/11/28 18:49
 * <p>
 * 给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。
 * 你必须设计并实现线性时间复杂度的算法且使用常数级空间来解决此问题。
 */
public class singleNumberTwo {
    public int singleNumber(int[] nums) {
        int res0 = 0, res1 = 0;
        for (int num : nums) {
            // ^ 进行位异或运算，出现两次的数字的异或运算会互相抵消变为0，最终结果就是只出现一次的数字
            // 只使用 ^ 运算，不使用 & 运算的话，只出现一次的数字最终会变出 ~num
            res0 = res0 ^ num & ~res1;
            res1 = res1 ^ num & ~res0;
        }
        return res0;
    }
}
