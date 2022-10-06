package leetcode.medium;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Success
 * Details
 * Runtime: 133 ms, faster than 11.33% of Java online submissions for Permutations II.
 * Memory Usage: 43 MB, less than 94.22% of Java online submissions for Permutations II.
 * Next challenges:
 * Palindrome Permutation II
 * Number of Squareful Arrays
 *
 * 47. Permutations II
 * Medium
 *
 * 6373
 *
 * 112
 *
 * Add to List
 *
 * Share
 * Given a collection of numbers, nums, that might contain duplicates, return all possible unique permutations in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,1,2]
 * Output:
 * [[1,1,2],
 *  [1,2,1],
 *  [2,1,1]]
 * Example 2:
 *
 * Input: nums = [1,2,3]
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 *
 */
public class _47_PC_Permutations_II {

    class Solution {
        public List<List<Integer>> permuteUnique(int[] nums) {
            if (nums == null || nums.length < 1) {
                return new LinkedList<>();
            }

            int len = nums.length;
            final Map<String, List<Integer>> output = new HashMap<>();

            LinkedList<Integer> prefix = new LinkedList<>();
            permute(output, prefix, nums, 0, len);

            return new LinkedList<>(output.values());
        }

        /**
         * Inplace swap and depupe at output time
         */
        private void permute(final Map<String, List<Integer>> output, LinkedList<Integer> prefix, int[] suffix, int start, int n) {

            if (prefix.size() >= n) {
                List<Integer> copy = new LinkedList<>(prefix);
                output.put(String.valueOf(copy), copy);
                //System.out.printf("prefix: %s \touput: %s\n", String.valueOf(copy), String.valueOf(output));
                return;
            }

            for (int i = start; i < n; i++ ) {
                int j = swapAndGet(start, i, suffix);
                prefix.addLast(j);
                //System.out.printf("prefix: %s \tsuffix: %s\n", String.valueOf(prefix), Arrays.toString(suffix));
                permute(output, prefix, suffix, start + 1, n);
                prefix.removeLast();
                swapAndGet(i, start, suffix);
            }

        }

        /**
         *
         */
        private int swapAndGet(int i, int j, int[] nums) {
            int temp = nums[j];
            nums[j] = nums[i];
            nums[i] = temp;
            return temp;
        }
    }
}
