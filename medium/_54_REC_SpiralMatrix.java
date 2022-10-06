package leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an m x n matrix, return all elements of the matrix in spiral order.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [1,2,3,6,9,8,7,4,5]
 * Example 2:
 *
 *
 * Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 *
 *
 * Constraints:
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 10
 * -100 <= matrix[i][j] <= 100
 */
public class _54_REC_SpiralMatrix {

    public static class Solution {
        public List<Integer> spiralOrder(int[][] matrix) {
            if (matrix == null || matrix[0] == null || matrix.length < 1 || matrix[0].length < 1) {
                return new ArrayList<>();
            }

            List<Integer> result = new ArrayList<>();
            int beginX = 0, endX = matrix[0].length - 1;
            int beginY = 0, endY = matrix.length - 1;
            while (true) {
                // From left to right
                for (int j = beginX; j <= endX; ++j) result.add(matrix[beginY][j]);
                if (++beginY > endY) break;
                // From top to bottom
                for (int i = beginY; i <= endY; ++i) result.add(matrix[i][endX]);
                if (beginX > --endX) break;
                // From right to left
                for (int j = endX; j >= beginX; --j) result.add(matrix[endY][j]);
                if (beginY > --endY) break;
                // From bottom to top
                for (int i = endY; i >= beginY; --i) result.add(matrix[i][beginX]);
                if (++beginX > endX) break;
            }

            return result;

        }

    }
}
