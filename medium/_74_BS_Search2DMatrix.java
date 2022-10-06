package leetcode.medium;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

/**
 * Success
 * Details
 * Runtime: 21 ms, faster than 5.19% of Java online submissions for Search a 2D Matrix.
 * Memory Usage: 43.3 MB, less than 11.30% of Java online submissions for Search a 2D Matrix.
 * Next challenges:
 * Search a 2D Matrix II
 *
 * 74. Search a 2D Matrix
 * Medium
 *
 * 9776
 *
 * 306
 *
 * Add to List
 *
 * Share
 * Write an efficient algorithm that searches for a value target in an m x n integer matrix matrix. This matrix has the following properties:
 *
 * Integers in each row are sorted from left to right.
 * The first integer of each row is greater than the last integer of the previous row.
 *
 *
 * Example 1:
 *
 *
 * Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
 * Output: true
 * Example 2:
 *
 *
 * Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
 * Output: false
 *
 *
 * Constraints:
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 100
 * -104 <= matrix[i][j], target <= 104
 *
 */
public class _74_BS_Search2DMatrix {

    class Solution {
        public boolean searchMatrix(int[][] matrix, int target) {
            if (matrix == null || matrix.length < 1 || matrix[0].length < 1) {
                return false;
            }

            int m =  matrix.length;
            int n = matrix[0].length;

            var list = new LinkedList<Integer>();
            for (int i = 0; i < m; i++) {
                list.add(matrix[i][0]);
            }

            int row = Collections.binarySearch(list, target);
            System.out.printf("row: %s of list: %s\n", row, String.valueOf(list));
            if (row >= 0) {
                return true;
            } else {
                row = -row - 1;
                if (row <= m && row > 0) {
                    row -= 1;
                }
                int col = Arrays.binarySearch(matrix[row], target);
                System.out.printf("col: %s by row: %s\n", col, row);
                return col >= 0;
            }

        }
    }
}
