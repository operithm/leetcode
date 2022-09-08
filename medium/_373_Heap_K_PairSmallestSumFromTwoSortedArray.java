package leetcode.medium;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 373. Find K Pairs with Smallest Sums
 * Medium
 *
 * 3094
 *
 * 193
 *
 * Add to List
 *
 * Share
 * You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.
 *
 * Define a pair (u, v) which consists of one element from the first array and one element from the second array.
 *
 * Return the k pairs (u1, v1), (u2, v2), ..., (uk, vk) with the smallest sums.
 *
 *
 *
 * Example 1:
 *
 * Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
 * Output: [[1,2],[1,4],[1,6]]
 * Explanation: The first 3 pairs are returned from the sequence: [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
 * Example 2:
 *
 * Input: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
 * Output: [[1,1],[1,1]]
 * Explanation: The first 2 pairs are returned from the sequence: [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
 * Example 3:
 *
 * Input: nums1 = [1,2], nums2 = [3], k = 3
 * Output: [[1,3],[2,3]]
 * Explanation: All possible pairs are returned from the sequence: [1,3],[2,3]
 *
 *
 * Constraints:
 *
 * 1 <= nums1.length, nums2.length <= 105
 * -109 <= nums1[i], nums2[i] <= 109
 * nums1 and nums2 both are sorted in ascending order.
 * 1 <= k <= 104
 *
 * Success
 * Details
 * Runtime: 302 ms, faster than 5.06% of Java online submissions for Find K Pairs with Smallest Sums.
 * Memory Usage: 618.9 MB, less than 5.96% of Java online submissions for Find K Pairs with Smallest Sums.
 * Next challenges:
 * Kth Smallest Element in a Sorted Matrix
 * Find K-th Smallest Pair Distance
 * Kth Smallest Product of Two Sorted Arrays
 */
public class _373_Heap_K_PairSmallestSumFromTwoSortedArray {

    class Solution {
        public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
            List<List<Integer>> result = new ArrayList<>();
            if (nums1 == null || nums2 == null) {
                return result;
            }

            int m =  Math.min(nums1.length, k);
            int n = Math.min(nums2.length, k);

            System.out.printf("m: %s, n: %s\n", m, n);
            boolean[][] V = new boolean[m][n];
            int[] C = new int[] {1, 0};
            int[] R = new int[] {0, 1};

            PriorityQueue<Pair> queue = new PriorityQueue<>(Comparator.comparing(Pair::getVal));
            Pair pair = new Pair(new int[]{0,0}, nums1[0] + nums2[0]);
            queue.add(pair);
            V[0][0] = Boolean.TRUE;

            List<Integer> list = new ArrayList<>();
            while (!queue.isEmpty() && result.size() < k) {
                pair = queue.remove();
                list.clear();
                list.add(nums1[pair.key[0]]);
                list.add(nums2[pair.key[1]]);
                result.add(new ArrayList<>(list));

                for (int i = 0; i < 2; i++) {
                    int[] xy = new int[] {pair.key[0] + C[i], pair.key[1] + R[i]};
                    //System.out.printf("index: %s\n", Arrays.toString(xy));
                    if (inBound(xy, m, n) && !V[xy[0]][xy[1]]) {
                        queue.add(new Pair(xy, nums1[xy[0]] + nums2[xy[1]]));
                        V[xy[0]][xy[1]] = Boolean.TRUE;
                    }
                }
                //System.out.printf("get: %s\n", Arrays.toString(key));
                //System.out.printf("matrix set: %s\n", Arrays.toString(V));
            }
            return result;
        }

        private boolean inBound(int[] xy, int m, int n) {
            return xy[0] >= 0 && xy[0] < m && xy[1] >= 0 && xy[1] < n;
        }

    }

    private static class Pair {
        private final int[] key;
        private int val;

        public Pair(int[] key) {
            this.key = key;
        }

        public Pair(int[] key, int val) {
            this.key = key;
            this.val = val;
        }

        public int getVal() {
            return val;
        }
    }
}

/**
 *
 */
