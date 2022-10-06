package leetcode.easy;

/**
 * Success
 * Details
 * Runtime: 18 ms, faster than 35.74% of Java online submissions for Count Binary Substrings.
 * Memory Usage: 52.4 MB, less than 13.18% of Java online submissions for Count Binary Substrings.
 * Next challenges:
 * Encode and Decode Strings
 *
 * 696. Count Binary Substrings
 * Easy
 *
 * 3250
 *
 * 706
 *
 * Add to List
 *
 * Share
 * Given a binary string s, return the number of non-empty substrings that have the same number of 0's and 1's, and all the 0's and all the 1's in these substrings are grouped consecutively.
 *
 * Substrings that occur multiple times are counted the number of times they occur.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "00110011"
 * Output: 6
 * Explanation: There are 6 substrings that have equal number of consecutive 1's and 0's: "0011", "01", "1100", "10", "0011", and "01".
 * Notice that some of these substrings repeat and are counted the number of times they occur.
 * Also, "00110011" is not a valid substring because all the 0's (and 1's) are not grouped together.
 * Example 2:
 *
 * Input: s = "10101"
 * Output: 4
 * Explanation: There are 4 substrings: "10", "01", "10", "01" that have equal number of consecutive 1's and 0's.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 105
 * s[i] is either '0' or '1'.
 * Accepted
 * 168,515
 * Submissions
 * 257,732
 */
public class _696_2Pointer_CountBinaryStrings {

    class Solution {
        public int countBinarySubstrings(String s) {
            if (s == null || s.length() < 1) {
                return 0;
            }

            int n = s.length();
            int[] sub = new int[n];
            sub[0] = s.charAt(0) == '0' ? -1 : 1;
            int nc = -1;
            int pc = -1;

            int count = 0;
            for (int i = 1; i < n; i++) {
                char c = s.charAt(i);
                if (c == '0') {
                    if (sub[i-1] > 0) {
                        sub[i] = -1;
                        pc = (i-1);
                        count ++;
                    } else {
                        sub[i] = sub[i-1] - 1;
                        if (pc != -1 && sub[pc] >= Math.abs(sub[i])) {
                            count++;
                        }
                    }
                }

                if (c == '1') {
                    if (sub[i-1] < 0) {
                        sub[i] = 1;
                        nc = (i-1);
                        count ++;
                    } else {
                        sub[i] = sub[i-1] + 1;
                        if (nc != -1 && sub[i] <= Math.abs(sub[nc])) {
                            count++;
                        }
                    }
                }
            }
            return count;
        }
    }

}
