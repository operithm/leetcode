package leetcode.easy;

/**
 * Success
 * Details
 * Runtime: 2 ms, faster than 46.92% of Java online submissions for Is Subsequence.
 * Memory Usage: 42.3 MB, less than 32.76% of Java online submissions for Is Subsequence.
 * Next challenges:
 * Number of Matching Subsequences
 * Shortest Way to Form String: 1055
 */
public class _392_2P_IsSubsequence {

    class Solution {
        public boolean isSubsequence(String s, String t) {
            if (s == null || s.isEmpty()) {
                return true;
            }

            int i = 0;
            int j = 0;
            while (i < t.length() && j < s.length() ) {
                if (t.charAt(i) == s.charAt(j)) {
                    j++;
                }
                i++;
            }
            return j == s.length();

        }
    }
}
