package leetcode.easy;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

/**
 * 506. Relative Ranks
 * Easy
 *
 * You are given an integer array score of size n, where score[i] is the score of the ith athlete in a competition. All the scores are guaranteed to be unique.
 *
 * The athletes are placed based on their scores, where the 1st place athlete has the highest score, the 2nd place athlete has the 2nd highest score, and so on. The placement of each athlete determines their rank:
 *
 * The 1st place athlete's rank is "Gold Medal".
 * The 2nd place athlete's rank is "Silver Medal".
 * The 3rd place athlete's rank is "Bronze Medal".
 * For the 4th place to the nth place athlete, their rank is their placement number (i.e., the xth place athlete's rank is "x").
 * Return an array answer of size n where answer[i] is the rank of the ith athlete.
 *
 *
 *
 * Example 1:
 *
 * Input: score = [5,4,3,2,1]
 * Output: ["Gold Medal","Silver Medal","Bronze Medal","4","5"]
 * Explanation: The placements are [1st, 2nd, 3rd, 4th, 5th].
 * Example 2:
 *
 * Input: score = [10,3,8,9,4]
 * Output: ["Gold Medal","5","Bronze Medal","Silver Medal","4"]
 * Explanation: The placements are [1st, 5th, 3rd, 2nd, 4th].
 *
 *
 *
 * Constraints:
 *
 * n == score.length
 * 1 <= n <= 104
 * 0 <= score[i] <= 106
 * All the values in score are unique.
 *
 * Accepted
 * 91,850
 * Submissions
 * 160,925
 *
 * Success
 * Details
 * Runtime: 7 ms, faster than 89.84% of Java online submissions for Relative Ranks.
 * Memory Usage: 43.6 MB, less than 79.30% of Java online submissions for Relative Ranks.
 *
 * Next challenges:
 *
 * Maximum Candies You Can Get from Boxes
 * Count Equal and Divisible Pairs in an Array
 * Sort Even and Odd Indices Independently
 */
public class _506_RelativeRanks {

    class Solution {
        public String[] findRelativeRanks(int[] score) {
            if (score == null || score.length < 1) {
                return new String[0];
            }
            Map<Integer, Integer> map1 = new TreeMap<>(Collections.reverseOrder());
            int i = 0;
            for (int a : score) {
                map1.put(a, i++);
            }

            String[] ranks = new String[map1.size()];
            i = 0;
            for (Map.Entry entry : map1.entrySet()) {
                int value = ((Integer) entry.getValue()).intValue();
                ranks[value] = rankText(i);
                i++;
            }

            return ranks;

        }

        private String rankText (int i) {
            switch(i) {
                case 0:
                    return "Gold Medal";
                case 1:
                    return "Silver Medal";
                case 2:
                    return "Bronze Medal";
                default:
                    return String.valueOf(i+1);
            }
        }
    }
}
