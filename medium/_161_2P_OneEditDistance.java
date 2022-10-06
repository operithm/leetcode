package leetcode.medium;

/**
 * Success
 * Details
 * Runtime: 2 ms, faster than 39.92% of Java online submissions for One Edit Distance.
 * Memory Usage: 42.1 MB, less than 81.08% of Java online submissions for One Edit Distance.
 * Next challenges:
 * Edit Distance
 *
 * 161. One Edit Distance
 * Medium
 *
 * 1204
 *
 * 168
 *
 * Add to List
 *
 * Share
 * Given two strings s and t, return true if they are both one edit distance apart, otherwise return false.
 *
 * A string s is said to be one distance apart from a string t if you can:
 *
 * Insert exactly one character into s to get t.
 * Delete exactly one character from s to get t.
 * Replace exactly one character of s with a different character to get t.
 *
 *
 * Example 1:
 *
 * Input: s = "ab", t = "acb"
 * Output: true
 * Explanation: We can insert 'c' into s to get t.
 * Example 2:
 *
 * Input: s = "", t = ""
 * Output: false
 * Explanation: We cannot get t from s by only one step.
 *
 *
 * Constraints:
 *
 * 0 <= s.length, t.length <= 104
 * s and t consist of lowercase letters, uppercase letters, and digits.
 */
public class _161_2P_OneEditDistance {

    class Solution {
        public boolean isOneEditDistance(String s, String t) {
            if (s == null || s.length() == 0) {
                return t != null && t.length() == 1;
            }
            if (t == null || t.length() == 0) {
                return s.length() == 1;
            }

            if (Math.abs(s.length()-t.length()) > 1) {
                return false;
            }

            String first = s;
            String second = t;
            int n = Math.max(s.length(), t.length());
            if (n != s.length()) {
                first = t;
                second = s;
            }

            int i = 0;
            int j = 0;
            int edit = 0;
            while (i < first.length() && j < second.length()) {
                //System.out.printf("1: %s, 2: %s\n", first, second);
                if (first.charAt(i) != second.charAt(j)) {
                    edit++;
                    if (edit > 1) {
                        return false;
                    }
                    if (first.length() > second.length()) {
                        i++;
                        return compare(first.substring(i), second.substring(j));
                    }
                }
                i++;
                j++;
            }
            return edit == 1 || (j == i && j != first.length());
        }

        private boolean compare (String a, String b) {
            if (a.length() != b.length()) {
                return false;
            }
            for (int i = 0; i < a.length(); i++) {
                if (a.charAt(i) != b.charAt(i)) {
                    return false;
                }
            }
            return true;
        }
    }
}
