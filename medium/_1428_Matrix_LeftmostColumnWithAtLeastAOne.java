package leetcode.medium;

import java.util.LinkedList;
import java.util.List;

/**
 * 1428. Leftmost Column with at Least a One
 * Medium
 *
 * 974
 *
 * 116
 *
 * Add to List
 *
 * Share
 * A row-sorted binary matrix means that all elements are 0 or 1 and each row of the matrix is sorted in non-decreasing order.
 *
 * Given a row-sorted binary matrix binaryMatrix, return the index (0-indexed) of the leftmost column with a 1 in it. If such an index does not exist, return -1.
 *
 * You can't access the Binary Matrix directly. You may only access the matrix using a BinaryMatrix interface:
 *
 * BinaryMatrix.get(row, col) returns the element of the matrix at index (row, col) (0-indexed).
 * BinaryMatrix.dimensions() returns the dimensions of the matrix as a list of 2 elements [rows, cols], which means the matrix is rows x cols.
 * Submissions making more than 1000 calls to BinaryMatrix.get will be judged Wrong Answer. Also, any solutions that attempt to circumvent the judge will result in disqualification.
 *
 * For custom testing purposes, the input will be the entire binary matrix mat. You will not have access to the binary matrix directly.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: mat = [[0,0],[1,1]]
 * Output: 0
 * Example 2:
 *
 *
 * Input: mat = [[0,0],[0,1]]
 * Output: 1
 * Example 3:
 *
 *
 * Input: mat = [[0,0],[0,0]]
 * Output: -1
 *
 *
 * Constraints:
 *
 * rows == mat.length
 * cols == mat[i].length
 * 1 <= rows, cols <= 100
 * mat[i][j] is either 0 or 1.
 * mat[i] is sorted in non-decreasing order.
 *
 * Success
 * Details
 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Leftmost Column with at Least a One.
 * Memory Usage: 42.7 MB, less than 94.39% of Java online submissions for Leftmost Column with at Least a One.
 * Next challenges:
 * Largest Sum of Averages
 * Maximum Font to Fit a Sentence in a Screen
 * Search Suggestions System
 */
public class _1428_Matrix_LeftmostColumnWithAtLeastAOne {

    private static interface BinaryMatrix {
        List<Integer> dimensions();
        int get(int x, int y);
    }

    class Solution {
        public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
            //System.out.printf("dimentions: %s\n", binaryMatrix.dimensions());
            var dim = binaryMatrix.dimensions();
            int m = dim.get(0);
            int n = dim.get(1);

            List<Integer> vals = new LinkedList<>();

            return binarySearch(0, n-1, m, binaryMatrix);

        }

        private int binarySearch(int start, int end, int rows, BinaryMatrix binaryMatrix) {
            int col = -1;
            if (start <= end) {
                int y = start + (end - start) / 2;
                for (int x = 0; x < rows; x++) {
                    if (binaryMatrix.get(x, y) == 1) {
                        col = y;
                        break;
                    }
                }

                if (col >= 0) {
                    int left = binarySearch(start, y - 1, rows, binaryMatrix);
                    if (left >= 0) {
                        col = left;
                    }
                } else {
                    int right = binarySearch(y + 1, end, rows, binaryMatrix);
                    if (right >= 0) {
                        col = right;
                    }
                }
            }

            return col;
        }
    }
}
