package leetcode.medium;

import java.util.Arrays;

/**
 * Success
 * Details
 * Runtime: 245 ms, faster than 11.87% of Java online submissions for Jump Game II.
 * Memory Usage: 54.7 MB, less than 5.09% of Java online submissions for Jump Game II.
 * Next challenges:
 * Jump Game
 * Jump Game III
 * Jump Game VII
 * Jump Game VIII
 *
 * 45. Jump Game II
 * Medium
 *
 * 9391
 *
 * 331
 *
 * Add to List
 *
 * Share
 * Given an array of non-negative integers nums, you are initially positioned at the first index of the array.
 *
 * Each element in the array represents your maximum jump length at that position.
 *
 * Your goal is to reach the last index in the minimum number of jumps.
 *
 * You can assume that you can always reach the last index.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,3,1,1,4]
 * Output: 2
 * Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * Example 2:
 *
 * Input: nums = [2,3,0,1,4]
 * Output: 2
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 104
 * 0 <= nums[i] <= 1000
 *
 *
 */
public class _45_DFS_JumpGame_II {

    class Solution {
        public int jump(int[] nums) {
            if (nums == null || nums.length < 1) {
                return 0;
            }

            int len = nums.length;
            int dist[] = new int[len];
            Arrays.fill(dist, Integer.MAX_VALUE);

            int v = len - 1;
            int w = 0;
            dist[v] = 0;

            DFS(nums, v, w, dist);

            return dist[w];
        }

        /**
         * G = nums
         w = 0 , v = nums.len - 1
         */
        private void DFS(int[]nums, int v, int w, int[] dist) {
            int u = farestAdjacentEdgeOrStart(nums, v);
            //System.out.printf(">> v: %s, w: %s\n", v, w);
            if (dist[u] > dist[v] + 1) {
                dist[u] = dist[v] + 1;
                if (u == w) {
                    return;
                }
                DFS(nums, u, w, dist);
            }
        }

        /**
         * The last return min must be the start node, i.e. 0
         * @param nums
         * @param v
         * @return
         */
        private int farestAdjacentEdgeOrStart(int[] nums, int v) {
            int min = v;
            for (int i = v - 1; i >= 0; i--) {
                int u = nums[i] + i;
                if (u >= v) {
                    min = i;
                }
            }
            //System.out.printf("v: %s, min: %s\n", v, min);
            return min;
        }
    }


}
