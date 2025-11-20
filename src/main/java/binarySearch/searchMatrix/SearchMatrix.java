package binarySearch.searchMatrix;

/**
 * @author LuoXuanwei
 * @version 1.0
 * @description 搜索二维矩阵
 * @date 2025/11/20 17:34
 * 给你一个满足下述两条属性的 m x n 整数矩阵：
 * <p>
 * 每行中的整数从左到右按非严格递增顺序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 * 给你一个整数 target ，如果 target 在矩阵中，返回 true ；否则，返回 false 。
 */
public class SearchMatrix {
    /**
     * 在二维矩阵中搜索目标值
     * 矩阵满足以下条件：
     * 1. 每行中的整数从左到右按非严格递增顺序排列
     * 2. 每行的第一个整数大于前一行的最后一个整数
     *
     * @param matrix 二维整数矩阵
     * @param target 要搜索的目标值
     * @return 如果找到目标值返回true，否则返回false
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        // 将二维矩阵视为一维数组进行二分搜索
        return binarySearch(matrix, target, 0, m * n - 1);
    }

    /**
     * 在二维矩阵中执行二分搜索
     *
     * @param matrix 二维整数矩阵
     * @param target 要搜索的目标值
     * @param left   搜索范围的左边界（一维索引）
     * @param right  搜索范围的右边界（一维索引）
     * @return 如果找到目标值返回true，否则返回false
     */
    private boolean binarySearch(int[][] matrix, int target, int left, int right) {
        // 搜索范围无效，未找到目标值
        if (left > right) {
            return false;
        }

        int n = matrix[0].length;
        int mid = (left + right) / 2;
        // 将一维索引转换为二维坐标
        int midRow = mid / n;
        int midCol = mid % n;

        if (matrix[midRow][midCol] == target) {
            return true;
        } else if (matrix[midRow][midCol] > target) {
            // 目标值在左半部分
            return binarySearch(matrix, target, left, mid - 1);
        } else {
            // 目标值在右半部分
            return binarySearch(matrix, target, mid + 1, right);
        }
    }

}
