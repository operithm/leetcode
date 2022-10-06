package leetcode.easy;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 1337. The K Weakest Rows in a Matrix
 * Easy
 *
 * 2441
 *
 * 137
 *
 * Add to List
 *
 * Share
 * You are given an m x n binary matrix mat of 1's (representing soldiers) and 0's (representing civilians). The soldiers are positioned in front of the civilians. That is, all the 1's will appear to the left of all the 0's in each row.
 *
 * A row i is weaker than a row j if one of the following is true:
 *
 * The number of soldiers in row i is less than the number of soldiers in row j.
 * Both rows have the same number of soldiers and i < j.
 * Return the indices of the k weakest rows in the matrix ordered from weakest to strongest.
 *
 *
 *
 * Example 1:
 *
 * Input: mat =
 * [[1,1,0,0,0],
 *  [1,1,1,1,0],
 *  [1,0,0,0,0],
 *  [1,1,0,0,0],
 *  [1,1,1,1,1]],
 * k = 3
 * Output: [2,0,3]
 * Explanation:
 * The number of soldiers in each row is:
 * - Row 0: 2
 * - Row 1: 4
 * - Row 2: 1
 * - Row 3: 2
 * - Row 4: 5
 * The rows ordered from weakest to strongest are [2,0,3,1,4].
 * Example 2:
 *
 * Input: mat =
 * [[1,0,0,0],
 *  [1,1,1,1],
 *  [1,0,0,0],
 *  [1,0,0,0]],
 * k = 2
 * Output: [0,2]
 * Explanation:
 * The number of soldiers in each row is:
 * - Row 0: 1
 * - Row 1: 4
 * - Row 2: 1
 * - Row 3: 1
 * The rows ordered from weakest to strongest are [0,2,3,1].
 *
 *
 * Constraints:
 *
 * m == mat.length
 * n == mat[i].length
 * 2 <= n, m <= 100
 * 1 <= k <= m
 * matrix[i][j] is either 0 or 1.
 * Accepted
 * 168,650
 * Submissions
 * 227,732
 *
 * Success
 * Details
 * Runtime: 85 ms, faster than 5.40% of Java online submissions for The K Weakest Rows in a Matrix.
 * Memory Usage: 54.5 MB, less than 6.30% of Java online submissions for The K Weakest Rows in a Matrix.
 *
 * Next challenges:
 * Group Anagrams
 * Sort Integers by The Power Value
 * Minimum Difference Between Highest and Lowest of K Scores
 */
public class _1337_BS_WeakestRowsInMatrix {

    class Solution {
        public int[] kWeakestRows(int[][] mat, int k) {
            if (mat == null || mat.length < 1 || mat[0] == null) {
                return new int[0];
            }

            Map<Integer, Integer> map1 = new HashMap<>();
            for (int i = 0; i < mat.length; i++) {
                map1.put(i, countOne(mat[i]));
            }

            Map<Integer, List<Integer>> map2 = map1.entrySet()
                .stream()
                .collect(
                    Collectors.groupingBy(
                        Map.Entry::getValue,
                        TreeMap::new,
                        Collectors.mapping(Map.Entry::getKey,Collectors.toList())
                    )
                );

            final List<Integer> list = new ArrayList<>();
            map2.values()
                .stream()
                .forEach(idxList -> {
                    Collections.sort(idxList);
                    idxList.stream().forEach(idx -> {
                        if (list.size() < k) {
                            list.add(idx);
                        }
                    });
                });

            int[] result = new int[list.size()];
            for (int i = 0; i < result.length; i++) {
                result[i] = list.get(i);
            }
            return result;
        }

        private int countOne(int[] binaryArr) {
            for (int i = 0; i < binaryArr.length; i++) {
                binaryArr[i] *= -1;
            }
            int ind = Arrays.binarySearch(binaryArr, 0);
            //System.out.printf("pre idx: %s, arr: %s\n", ind, Arrays.toString(binaryArr));
            if (ind < 0) {
                ind = -ind - 1;
            } else {
                while(ind - 1 >= 0 && binaryArr[ind-1] == 0) {
                    ind -= 1;
                }
            }
            //System.out.printf("idx: %s, arr: %s\n", ind, Arrays.toString(binaryArr));
            return ind;
        }
    }
}
