package leetcode.medium;

import java.util.LinkedList;
import java.util.List;

/**
 * Success
 * Details
 * Runtime: 63 ms, faster than 10.35% of Java online submissions for Maximum Split of Positive Even Integers.
 * Memory Usage: 160.5 MB, less than 9.50% of Java online submissions for Maximum Split of Positive Even Integers.
 * Next challenges:
 * Sum of Digits in the Minimum Number
 * Ugly Number III
 * Minimum Subsequence in Non-Increasing Order
 *
 * 2178. Maximum Split of Positive Even Integers
 * Medium
 *
 * 541
 *
 * 54
 *
 * Add to List
 *
 * Share
 * You are given an integer finalSum. Split it into a sum of a maximum number of unique positive even integers.
 *
 * For example, given finalSum = 12, the following splits are valid (unique positive even integers summing up to finalSum): (12), (2 + 10), (2 + 4 + 6), and (4 + 8). Among them, (2 + 4 + 6) contains the maximum number of integers. Note that finalSum cannot be split into (2 + 2 + 4 + 4) as all the numbers should be unique.
 * Return a list of integers that represent a valid split containing a maximum number of integers. If no valid split exists for finalSum, return an empty list. You may return the integers in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: finalSum = 12
 * Output: [2,4,6]
 * Explanation: The following are valid splits: (12), (2 + 10), (2 + 4 + 6), and (4 + 8).
 * (2 + 4 + 6) has the maximum number of integers, which is 3. Thus, we return [2,4,6].
 * Note that [2,6,4], [6,2,4], etc. are also accepted.
 * Example 2:
 *
 * Input: finalSum = 7
 * Output: []
 * Explanation: There are no valid splits for the given finalSum.
 * Thus, we return an empty array.
 * Example 3:
 *
 * Input: finalSum = 28
 * Output: [6,8,2,12]
 * Explanation: The following are valid splits: (2 + 26), (6 + 8 + 2 + 12), and (4 + 24).
 * (6 + 8 + 2 + 12) has the maximum number of integers, which is 4. Thus, we return [6,8,2,12].
 * Note that [10,2,4,12], [6,2,4,16], etc. are also accepted.
 *
 *
 * Constraints:
 *
 * 1 <= finalSum <= 1010
 */
public class _2178_REC_MaximumSplitOfPositiveEvenIntegers {

    class Solution {
        public List<Long> maximumEvenSplit(long finalSum) {
            if (finalSum % 2 == 1) {
                return new LinkedList<>();
            }
            var result = new LinkedList<Long>();
            long p = finalSum;

            split(result, 2, p);

            return result;
        }

        private void split(List<Long> prefix, long start, long p) {
            if (start * 2 + 2 > p) {
                if (p > 0) {
                    prefix.add(p);
                }
                return;
            }
            prefix.add(start);
            split(prefix, start + 2, p - start);
        }
    }
}
