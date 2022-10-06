package leetcode.easy;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * 1464. Maximum Product of Two Elements in an Array
 * Easy
 *
 * 979
 *
 * 159
 *
 * Add to List
 *
 * Share
 * Given the array of integers nums, you will choose two different indices i and j of that array. Return the maximum value of (nums[i]-1)*(nums[j]-1).
 *
 *
 * Example 1:
 *
 * Input: nums = [3,4,5,2]
 * Output: 12
 * Explanation: If you choose the indices i=1 and j=2 (indexed from 0), you will get the maximum value, that is, (nums[1]-1)*(nums[2]-1) = (4-1)*(5-1) = 3*4 = 12.
 * Example 2:
 *
 * Input: nums = [1,5,4,5]
 * Output: 16
 * Explanation: Choosing the indices i=1 and j=3 (indexed from 0), you will get the maximum value of (5-1)*(5-1) = 16.
 * Example 3:
 *
 * Input: nums = [3,7]
 * Output: 12
 *
 *
 * Constraints:
 *
 * 2 <= nums.length <= 500
 * 1 <= nums[i] <= 10^3
 *
 * Success
 * Details
 * Runtime: 7 ms, faster than 12.77% of Java online submissions for Maximum Product of Two Elements in an Array.
 * Memory Usage: 44.2 MB, less than 6.91% of Java online submissions for Maximum Product of Two Elements in an Array.
 * Next challenges:
 * Search in a Sorted Array of Unknown Size
 * K Closest Points to Origin
 * Minimum Average Difference
 */
public class _1464_2P_MaxProductOfTwoElementFromArray {
    class Solution {
        public int maxProduct(int[] nums) {
            if (nums == null || nums.length < 2 || nums.length > 500) {
                throw new IllegalArgumentException("not a valid array.");
            }

            PriorityQueue<Integer> max = new PriorityQueue<>(2, Collections.reverseOrder());
            PriorityQueue<Integer> min = new PriorityQueue<>(2);

            for (int a : nums) {
                int b = a - 1;
                max.add(b);
                min.add(b);

                if (max.size() > 2) {
                    max.remove();
                }

                if (min.size() > 2) {
                    min.remove();
                }
            }

            int minProd = 1;
            while(!max.isEmpty()) {
                minProd *= max.remove();
            }
            int maxProd = 1;
            while(!min.isEmpty()) {
                maxProd *= min.remove();
            }

            return Math.max(minProd, maxProd);

        }
    }
}
