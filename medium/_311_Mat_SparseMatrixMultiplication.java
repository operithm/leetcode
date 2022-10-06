package leetcode.medium;

import java.util.Arrays;

/**
 * Success
 * Details
 * Runtime: 371 ms, faster than 5.09% of Java online submissions for Sparse Matrix Multiplication.
 * Memory Usage: 71.4 MB, less than 6.97% of Java online submissions for Sparse Matrix Multiplication.
 * Next challenges:
 *
 * Longest Substring with At Most K Distinct Characters
 * Permutation in String
 * Find Subarrays With Equal Sum
 *
 * 311. Sparse Matrix Multiplication
 * Medium
 *
 * 903
 *
 * 320
 *
 * Add to List
 *
 * Share
 * Given two sparse matrices mat1 of size m x k and mat2 of size k x n, return the result of mat1 x mat2. You may assume that multiplication is always possible.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: mat1 = [[1,0,0],[-1,0,3]], mat2 = [[7,0,0],[0,0,0],[0,0,1]]
 * Output: [[7,0,0],[-7,0,3]]
 * Example 2:
 *
 * Input: mat1 = [[0]], mat2 = [[0]]
 * Output: [[0]]
 *
 *
 * Constraints:
 *
 * m == mat1.length
 * k == mat1[i].length == mat2.length
 * n == mat2[i].length
 * 1 <= m, n, k <= 100
 * -100 <= mat1[i][j], mat2[i][j] <= 100
 *
 */
public class _311_Mat_SparseMatrixMultiplication {

    class Solution {
        public int[][] multiply(int[][] mat1, int[][] mat2) {
            int m = mat1.length;
            int n = mat2[0].length;
            System.out.printf("m: %s, n: %s\n", m, n);
            var result = new int[m][n];
            for (int i = 0; i < m; i++) {
                var row = mat1[i];
                for (int j = 0; j < n; j++) {
                    var column = getColumn(mat2, j);
                    System.out.printf("row: %s, col: %s\n", Arrays.toString(row), Arrays.toString(column));
                    for (int k = 0; k < mat1[0].length; k++) {
                        result[i][j] += row[k]*column[k];
                    }
                }
            }
            return result;
        }

        private int[] getColumn(int[][] mat2, int col) {
            int n = mat2.length;
            var result = new int[n];
            for (int i = 0; i < n; i++) {
                result[i] = mat2[i][col];
            }
            return result;
        }
    }
}
