package leetcode.medium;

import java.util.Arrays;
import java.util.LinkedList;

/**
 *
 * Return the maximum number of white tiles that can be covered by the carpet.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: tiles = [[1,5],[10,11],[12,18],[20,25],[30,32]], carpetLen = 10
 * Output: 9
 * Explanation: Place the carpet starting on tile 10.
 * It covers 9 white tiles, so we return 9.
 * Note that there may be other places where the carpet covers 9 white tiles.
 * It can be shown that the carpet cannot cover more than 9 white tiles.
 * Example 2:
 *
 *
 * Input: tiles = [[10,11],[1,1]], carpetLen = 2
 * Output: 2
 * Explanation: Place the carpet starting on tile 10.
 * It covers 2 white tiles, so we return 2.
 *
 */
public class TODO_2271_Interval_MaximumWhiteTilesCoveredByACarpet {

    class Solution {
        public int maximumWhiteTiles(int[][] tiles, int carpetLen) {
            if (tiles == null || tiles.length < 1) {
                return 0;
            }

            Arrays.sort(tiles, (a, b) -> Integer.compare(a[0], b[0]));
            LinkedList<int[]> merged = new LinkedList<>();

            for (int[] tile : tiles) {
                int[] peek = merged.peekLast();
                if (peek != null && peek[1] >= tile[0] - 1) {
                    peek[1] = Math.max(tile[1], peek[1]);
                } else {
                    merged.add(tile);
                }
            }

            int len = merged.size();
            int[] starts = new int[merged.size()];
            int[] gaps = new int[merged.size()];

            for (int i = 0; i < len; i++) {
                starts[i] = merged.get(i)[0];
                if (i < len - 1) {
                    gaps[i+1] = (merged.get(i+1)[0] - merged.get(i)[1] - 1) + gaps[i];
                }
            }

            System.out.printf("start: %s\ngaps: %s\n", Arrays.toString(starts), Arrays.toString(gaps));
            int max = 0;

            for (int i = 0; i < starts.length - 1; i++) {

                int start = starts[i];
                int end = start + carpetLen;
                int idx = Arrays.binarySearch(starts, end);


                if (idx < 0) {
                    idx = -idx - 1;
                }

                System.out.printf("i-idx:[%s %s], [%s - %s]\n", i, idx, start, end);

                int x = carpetLen - (gaps[idx-1] - gaps[i]);
                if (x > max) {
                    System.out.printf("max: %s, x: %s\n", max, x);
                    max = x;
                }
            }

            return max;

        }

    }


}
