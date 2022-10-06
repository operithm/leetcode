package leetcode.medium;

import java.util.*;

/**
 * Success
 * Details
 * Runtime: 494 ms, faster than 6.29% of Java online submissions for Random Pick Index.
 * Memory Usage: 87.9 MB, less than 30.82% of Java online submissions for Random Pick Index.
 * Next challenges:
 * Linked List Random Node
 * Random Pick with Blacklist
 * Random Pick with Weight
 *
 * 398. Random Pick Index
 * Medium
 *
 * 1024
 *
 * 1124
 *
 * Add to List
 *
 * Share
 * Given an integer array nums with possible duplicates, randomly output the index of a given target number. You can assume that the given target number must exist in the array.
 *
 * Implement the Solution class:
 *
 * Solution(int[] nums) Initializes the object with the array nums.
 * int pick(int target) Picks a random index i from nums where nums[i] == target. If there are multiple valid i's, then each index should have an equal probability of returning.
 *
 *
 * Example 1:
 *
 * Input
 * ["Solution", "pick", "pick", "pick"]
 * [[[1, 2, 3, 3, 3]], [3], [1], [3]]
 * Output
 * [null, 4, 0, 2]
 *
 * Explanation
 * Solution solution = new Solution([1, 2, 3, 3, 3]);
 * solution.pick(3); // It should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
 * solution.pick(1); // It should return 0. Since in the array only nums[0] is equal to 1.
 * solution.pick(3); // It should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 2 * 104
 * -231 <= nums[i] <= 231 - 1
 * target is an integer from nums.
 * At most 104 calls will be made to pick.
 *
 *
 */
public class _389_RD_RandomIndexPick {

    class Solution {
        private final Map<Integer, List<Integer>> map;

        public Solution(int[] nums) {
            this.map = new TreeMap<>();
            init(nums);
        }

        public int pick(int target) {
            var list = map.get(target);
            int n = list.size();
            if (n == 1) {
                return list.get(0);
            }

            int div = new Random().nextInt((int) System.currentTimeMillis() / 1000);
            return list.get(div % n);

        }

        private void init(int[] nums) {
            if (nums != null) {
                for (int i = 0; i < nums.length; i++) {
                    var list = map.getOrDefault(nums[i], new LinkedList<Integer>());
                    list.add(i);
                    map.put(nums[i], list);
                }
            }
        }
    }

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int param_1 = obj.pick(target);
 */

}
