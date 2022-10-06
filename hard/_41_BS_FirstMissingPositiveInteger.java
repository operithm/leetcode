package leetcode.hard;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Success
 * Details
 * Runtime: 46 ms, faster than 13.20% of Java online submissions for First Missing Positive.
 * Memory Usage: 51.4 MB, less than 93.02% of Java online submissions for First Missing Positive.
 * Next challenges:
 * Missing Number
 * Find the Duplicate Number
 * Find All Numbers Disappeared in an Array
 * Couples Holding Hands
 * Smallest Number in Infinite Set
 *
 * 41. First Missing Positive
 * Hard
 *
 * 11653
 *
 * 1449
 *
 * Add to List
 *
 * Share
 * Given an unsorted integer array nums, return the smallest missing positive integer.
 *
 * You must implement an algorithm that runs in O(n) time and uses constant extra space.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,0]
 * Output: 3
 * Explanation: The numbers in the range [1,2] are all in the array.
 * Example 2:
 *
 * Input: nums = [3,4,-1,1]
 * Output: 2
 * Explanation: 1 is in the array but 2 is missing.
 * Example 3:
 *
 * Input: nums = [7,8,9,11,12]
 * Output: 1
 * Explanation: The smallest positive integer 1 is missing.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * -231 <= nums[i] <= 231 - 1
 */
public class _41_BS_FirstMissingPositiveInteger {

    class Solution {
        public int firstMissingPositive(int[] nums) {
            List<int[]> ranges = new LinkedList<>();
            ranges.add(new int[]{1, Integer.MAX_VALUE});
            for (int num : nums) {
                insert(ranges, num);
            }
            return ranges.get(0)[0];
        }

        private void insert(List<int[]> ranges, int num) {
            if (num <= 0) {
                return;
            }
            int index = Collections.binarySearch(ranges, new int[]{num, num}, Comparator.comparing(a -> a[0]));
            //System.out.printf("index: %s, num: %s\n", index, num);
            if (index >= 0) {
            } else {
                index = -index - 1;
                /**
                 not found, so go previous index - 1 or do nothing;
                 */
                if (index == 0) {
                    //not found, doing nothing
                    return;
                } else {
                    index -= 1;
                }
            }
            var range = ranges.get(index);
            //System.out.printf("index: %s, list: %s\n", index, printRanges(ranges));
            if (range[1] < num) {
                return;
            }
            if (range[0] == range[1]) {
                ranges.remove(index);
            } else {
                if (range[0] == num) {
                    range[0] = num + 1;
                } else if (range[1] == num) {
                    range[1] = num - 1;
                } else {
                    var append = new int[] {num + 1, range[1]};
                    range[1] = num - 1;
                    if (index + 1 == ranges.size()) {
                        ranges.add(append);
                    } else {
                        ranges.add(index + 1, append);
                    }
                }
            }
            //System.out.printf("index: %s, list: %s\n", index, printRanges(ranges));
        }

        private String printRanges(List<int[]> ranges) {
            return String.join(",", ranges.stream().map(a -> Arrays.toString(a)).collect(Collectors.toList()));
        }
    }
}
