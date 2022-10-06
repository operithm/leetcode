package leetcode.easy;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Success
 * Details
 * Runtime: 11 ms, faster than 12.07% of Java online submissions for Lucky Numbers in a Matrix.
 * Memory Usage: 50.9 MB, less than 5.38% of Java online submissions for Lucky Numbers in a Matrix.
 * Next challenges:
 * Find Common Characters
 * Smallest Sufficient Team
 * Longest Subsequence With Limited Sum
 *
 * 1380. Lucky Numbers in a Matrix
 * Easy
 *
 * 1178
 *
 * 69
 *
 * Add to List
 *
 * Share
 * Given an m x n matrix of distinct numbers, return all lucky numbers in the matrix in any order.
 *
 * A lucky number is an element of the matrix such that it is the minimum element in its row and maximum in its column.
 *
 *
 *
 * Example 1:
 *
 * Input: matrix = [[3,7,8],[9,11,13],[15,16,17]]
 * Output: [15]
 * Explanation: 15 is the only lucky number since it is the minimum in its row and the maximum in its column.
 * Example 2:
 *
 * Input: matrix = [[1,10,4,2],[9,3,8,7],[15,16,17,12]]
 * Output: [12]
 * Explanation: 12 is the only lucky number since it is the minimum in its row and the maximum in its column.
 * Example 3:
 *
 * Input: matrix = [[7,8],[1,2]]
 * Output: [7]
 * Explanation: 7 is the only lucky number since it is the minimum in its row and the maximum in its column.
 *
 *
 * Constraints:
 *
 * m == mat.length
 * n == mat[i].length
 * 1 <= n, m <= 50
 * 1 <= matrix[i][j] <= 105.
 * All elements in the matrix are distinct.
 */
public class _1380_Mat_LuckyNumbersInMatrix {

    class Solution {
        public List<Integer> luckyNumbers (int[][] matrix) {
            if (matrix == null || matrix.length < 1 || matrix[0] == null || matrix[0].length < 1) {
                return List.of();
            }

            int m = matrix.length;
            int n = matrix[0].length;
            int[] min = new int[m];
            Arrays.fill(min, Integer.MAX_VALUE);

            for (int i = 0; i < m; i++) {
                int[] row = matrix[i];
                for (int j = 0; j < n; j++) {
                    if (min[i] > row[j]) {
                        min[i] = row[j];
                    }
                }
            }
            System.out.printf("min: %s\n", Arrays.toString(min));
            var result = new LinkedList<Integer>();

            for (int j = 0; j < n; j++) {
                int idx = -1;
                int max = Integer.MIN_VALUE;
                for (int i = 0; i < m; i++) {
                    if (matrix[i][j] > max) {
                        max = matrix[i][j];
                        idx = i;
                    }
                }
                if (max == min[idx]) {
                    result.add(max);
                }
            }

            return result;
        }
    }
}
