package leetcode.easy;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Success
 * Details
 * Runtime: 8 ms, faster than 67.71% of Java online submissions for Missing Ranges.
 * Memory Usage: 42.4 MB, less than 45.76% of Java online submissions for Missing Ranges.
 * Next challenges:
 * Summary Ranges
 *
 * 163. Missing Ranges
 * Easy
 *
 * 835
 *
 * 2634
 *
 * Add to List
 *
 * Share
 * You are given an inclusive range [lower, upper] and a sorted unique integer array nums, where all elements are in the inclusive range.
 *
 * A number x is considered missing if x is in the range [lower, upper] and x is not in nums.
 *
 * Return the smallest sorted list of ranges that cover every missing number exactly. That is, no element of nums is in any of the ranges, and each missing number is in one of the ranges.
 *
 * Each range [a,b] in the list should be output as:
 *
 * "a->b" if a != b
 * "a" if a == b
 *
 *
 * Example 1:
 *
 * Input: nums = [0,1,3,50,75], lower = 0, upper = 99
 * Output: ["2","4->49","51->74","76->99"]
 * Explanation: The ranges are:
 * [2,2] --> "2"
 * [4,49] --> "4->49"
 * [51,74] --> "51->74"
 * [76,99] --> "76->99"
 * Example 2:
 *
 * Input: nums = [-1], lower = -1, upper = -1
 * Output: []
 * Explanation: There are no missing ranges since there are no missing numbers.
 *
 *
 * Constraints:
 *
 * -109 <= lower <= upper <= 109
 * 0 <= nums.length <= 100
 * lower <= nums[i] <= upper
 * All the values of nums are unique.
 */
public class _163_BS_MissingRanges {

    class Solution {
        public List<String> findMissingRanges(int[] nums, int lower, int upper) {
            var result = new LinkedList<String>();
            var range = new LinkedList<int[]>();

            range.add(new int[]{lower, upper});
            //System.out.printf("range: %s", String.valueOf(range));
            for (int num : nums) {
                delete(range, num);
            }

            while(!range.isEmpty()) {
                int[] v = range.remove(0);
                output(result, v);
            }

            return result;
        }

        private void delete(List<int[]> range, int num) {
            int len = range.size();
            int index = Collections.binarySearch(range, new int[]{num, num},
                    Comparator.comparingInt(a -> a[0]));

            /**
             *
             */
            int[] u = null;
            if (index >= 0) {
                u = range.get(index);
                if(u[0] == u[1]) {
                    range.remove(index);
                } else {
                    u[0] = num + 1;
                }
            } else {
                int pos = -index - 1;
                if (pos == 0) {
                    //do nothing
                    return;
                }
                u = range.get(pos-1);
                int upper = u[1];
                u[1] = num - 1;
                if (upper >= num + 1) {
                    int[] w = new int[] {num + 1, upper};
                    if (pos == len) {
                        range.add(w);
                    } else {
                        range.add(pos, w);
                    }
                }
            }
        }

        /**
         *
         */
        private void output(List<String> result, int[] interval) {
            if (interval[0] == interval[1]) {
                result.add("" + interval[0]);
            } else {
                result.add(String.format("%s->%s", interval[0], interval[1]));
            }
        }
    }
}
