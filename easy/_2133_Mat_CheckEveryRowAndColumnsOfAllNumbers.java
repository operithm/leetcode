package leetcode.easy;

import java.util.BitSet;

/**
 * Success
 * Details
 * Runtime: 11 ms, faster than 77.67% of Java online submissions for Check if Every Row and Column Contains All Numbers.
 * Memory Usage: 43.4 MB, less than 91.65% of Java online submissions for Check if Every Row and Column Contains All Numbers.
 * Next challenges:
 * Matrix Diagonal Sum
 *
 * 2133. Check if Every Row and Column Contains All Numbers
 * Easy
 *
 * 530
 *
 * 31
 *
 * Add to List
 *
 * Share
 * An n x n matrix is valid if every row and every column contains all the integers from 1 to n (inclusive).
 *
 * Given an n x n integer matrix matrix, return true if the matrix is valid. Otherwise, return false.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: matrix = [[1,2,3],[3,1,2],[2,3,1]]
 * Output: true
 * Explanation: In this case, n = 3, and every row and column contains the numbers 1, 2, and 3.
 * Hence, we return true.
 * Example 2:
 *
 *
 * Input: matrix = [[1,1,1],[1,2,3],[1,2,3]]
 * Output: false
 * Explanation: In this case, n = 3, but the first row and the first column do not contain the numbers 2 or 3.
 * Hence, we return false.
 *
 *
 * Constraints:
 *
 * n == matrix.length == matrix[i].length
 * 1 <= n <= 100
 * 1 <= matrix[i][j] <= n
 */
public class _2133_Mat_CheckEveryRowAndColumnsOfAllNumbers {

    class Solution {
        public boolean checkValid(int[][] matrix) {
            if (matrix == null || matrix.length < 1 || matrix[0] == null || matrix[0].length < 1) {
                return false;
            }

            int n = matrix.length;
            for (int j = 0; j < n; j++) {
                if (!containsAll(matrix[j], n)) {
                    return false;
                }
                int[] col = new int[n];
                for (int k = 0; k < n; k++) {
                    col[k] = matrix[k][j];
                }
                if (!containsAll(col, n)) {
                    return false;
                }
            }

            return true;
        }

        private boolean containsAll(int[] row, int n) {
            BitSet set = new BitSet(n);
            for (int i = 0; i < n; i++) {
                set.set(row[i] - 1);
            }
            for(int i = 0; i < n; i++) {
                if (!set.get(i)) {
                    return false;
                }
            }
            return true;
        }
    }
}
