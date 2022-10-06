package leetcode.easy;

/**
 * Success
 * Details
 * Runtime: 23 ms, faster than 6.11% of Java online submissions for Convert 1D Array Into 2D Array.
 * Memory Usage: 121.4 MB, less than 12.71% of Java online submissions for Convert 1D Array Into 2D Array.
 * Next challenges:
 * Reshape the Matrix
 *
 *
 */
public class _2022_Mat_Convert1DArrayTo2D {

    class Solution {
        public int[][] construct2DArray(int[] original, int m, int n) {
            if (original == null || m <= 0 || n <= 0 || original.length != (m * n) ) {
                return new int[0][0];
            }

            int[][] result = new int[m][n];
            int len = original.length;
            for (int i = 0; i < len; i++) {
                int x = i / n;
                int y = (i - n * x) % n;
                //System.out.printf("x: %s, y: %s\n", x, y);
                result[x][y] = original[i];
            }
            return result;
        }
    }
}
