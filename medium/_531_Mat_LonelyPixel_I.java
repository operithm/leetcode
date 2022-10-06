package leetcode.medium;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Runtime 17 ms Beats 5.82% Memory 68.2 MB Beats 5.50%
 *
 * Next question
 *
 * 532. K-diff Pairs in an Array
 * More challenges
 * 533. Lonely Pixel II
 *
 * 531. Lonely Pixel I
 * Medium
 *
 * company
 * Google
 *
 * Given an m x n picture consisting of black 'B' and white 'W' pixels, return the number of black lonely pixels.
 *
 * A black lonely pixel is a character 'B' that located at a specific position where the same row and same column don't have any other black pixels.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: picture = [["W","W","B"],["W","B","W"],["B","W","W"]]
 * Output: 3
 * Explanation: All the three 'B's are black lonely pixels.
 * Example 2:
 *
 *
 * Input: picture = [["B","B","B"],["B","B","W"],["B","B","B"]]
 * Output: 0
 *
 *
 * Constraints:
 *
 * m == picture.length
 * n == picture[i].length
 * 1 <= m, n <= 500
 * picture[i][j] is 'W' or 'B'.
 */
public class _531_Mat_LonelyPixel_I {
    class Solution {

        public int findLonelyPixel(char[][] picture) {
            if (picture == null || picture.length < 1 || picture[0].length < 1) {
                return 0;
            }

            int m = picture.length;
            int n = picture[0].length;
            var rowMap = new ConcurrentHashMap<Integer, Set<Integer>>();
            var colMap = new ConcurrentHashMap<Integer, Set<Integer>>();

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (picture[i][j] == 'B') {
                        put(rowMap, i, j);
                        put(colMap, j, i);
                    }
                }
            }

            removeIf(rowMap, 1);
            removeIf(colMap, 1);

            int count = 0;
            for (int row : rowMap.keySet()) {
                int col = rowMap.get(row).iterator().next();
                if (colMap.keySet().contains(col)) {
                    count++;
                }
            }

            return count;
        }

        private void put(ConcurrentHashMap<Integer, Set<Integer>> map, int key, int val) {
            var set = map.getOrDefault(key, new HashSet<>());
            set.add(val);
            map.put(key, set);
        }

        private void removeIf(ConcurrentHashMap<Integer, Set<Integer>> map, int size) {
            for (int j : map.keySet()) {
                if (map.get(j).size() > size) {
                    map.remove(j);
                }
            }
        }

    }
}
