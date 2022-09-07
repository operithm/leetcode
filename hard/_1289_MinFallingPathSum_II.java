package leetcode.hard;

import java.util.Arrays;

/**
 * 1289. Minimum Falling Path Sum II
 * Hard
 *
 * 845
 *
 * 68
 *
 * Add to List
 *
 * Share
 * Given an n x n integer matrix grid, return the minimum sum of a falling path with non-zero shifts.
 *
 * A falling path with non-zero shifts is a choice of exactly one element from each row of grid such that no two elements
 * chosen in adjacent rows are in the same column.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: arr = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: 13
 * Explanation:
 * The possible falling paths are:
 * [1,5,9], [1,5,7], [1,6,7], [1,6,8],
 * [2,4,8], [2,4,9], [2,6,7], [2,6,8],
 * [3,4,8], [3,4,9], [3,5,7], [3,5,9]
 * The falling path with the smallest sum is [1,5,7], so the answer is 13.
 * Example 2:
 *
 * Input: grid = [[7]]
 * Output: 7
 *
 *
 * Constraints:
 *
 * n == grid.length == grid[i].length
 * 1 <= n <= 200
 * -99 <= grid[i][j] <= 99
 * Success
 * Details
 * Runtime: 53 ms, faster than 49.05% of Java online submissions for Minimum Falling Path Sum II.
 * Memory Usage: 55.1 MB, less than 68.12% of Java online submissions for Minimum Falling Path Sum II.
 * Next challenges:
 * Minimum Falling Path Sum
 */
public class _1289_MinFallingPathSum_II {

    class Solution {
        public int minFallingPathSum(int[][] grid) {
            if (grid == null || grid.length < 1 || grid[0] == null || grid[0].length < 1) {
                return 0;
            }
            int m = grid.length;
            int n = grid[0].length;

            int[][] result = new int[m][n];
            for (int i = m-1; i >= 0; i--) {
                for (int j = 0; j < n; j++) {
                    result[i][j] = grid[i][j];
                    if (i < m-1) {
                        result[i][j]+= minRow(i,j, result);
                    }
                }
            }
            return Arrays.stream(result[0]).min().getAsInt();
        }

        /**
         *
         * @param i
         * @param j
         * @param result
         * @return
         */
        private int minRow(int i, int j, int[][] result) {
            int y = Integer.MAX_VALUE;
            if (i == result.length - 1) {
                return 0;
            } else {
                int[] xRow = result[i+1];
                for (int k = 0; k < result[0].length; k++) {
                    if (k != j && xRow[k] < y) {
                        y = xRow[k];
                    }
                }
            }
            return y;
        }
    }
}
