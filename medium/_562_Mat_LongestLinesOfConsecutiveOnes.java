package leetcode.medium;

/**
 * Success
 * Details
 * Runtime: 35 ms, faster than 27.57% of Java online submissions for Longest Line of Consecutive One in Matrix.
 * Memory Usage: 72.4 MB, less than 5.09% of Java online submissions for Longest Line of Consecutive One in Matrix.
 * Next challenges:
 * Bus Routes
 * Path with Maximum Gold
 * Task Scheduler II
 *
 * 562. Longest Line of Consecutive One in Matrix
 * Medium
 *
 * 781
 *
 * 104
 *
 * Add to List
 *
 * Share
 * Given an m x n binary matrix mat, return the length of the longest line of consecutive one in the matrix.
 *
 * The line could be horizontal, vertical, diagonal, or anti-diagonal.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: mat = [[0,1,1,0],[0,1,1,0],[0,0,0,1]]
 * Output: 3
 * Example 2:
 *
 *
 * Input: mat = [[1,1,1,1],[0,1,1,0],[0,0,0,1]]
 * Output: 4
 *
 *
 * Constraints:
 *
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 104
 * 1 <= m * n <= 104
 * mat[i][j] is either 0 or 1.
 */
public class _562_Mat_LongestLinesOfConsecutiveOnes {

    class Solution {

        public int longestLine(int[][] mat) {
            if (mat == null) {
                return 0;
            }

            int m = mat.length;
            int n = mat[0].length;

            int[] x = new int[] {1, 1, 1, 0};
            int[] y = new int[] {-1, 0, 1, 1};

            int[][][] lengths = new int[m][n][4];
            int max = 0;
            for (int i = m - 1; i >= 0; i--) {
                for (int j = n - 1; j >= 0; j--) {
                    if (mat[i][j] == 1) {
                        int[] len = new int[] {1, 1, 1, 1};
                        for (int k = 0; k < 4; k++) {
                            int[] connected = getConnected(lengths, new int[] {i + x[k], j + y[k]}, m, n);
                            len[k] += connected[k];
                            if (len[k] > max) {
                                max = len[k];
                            }
                        }

                        lengths[i][j] = len;
                    }
                }
            }

            return max;
        }

        private int[] getConnected(int[][][] lengths, int[] id, int m, int n) {
            return inbound(id, m, n) ? lengths[id[0]][id[1]] : new int[]{0,0,0,0};
        }

        private boolean inbound(int[] id, int m, int n) {
            return id[0] >= 0 && id[0] < m && id[1] >= 0 && id[1] < n;
        }

    }
}
