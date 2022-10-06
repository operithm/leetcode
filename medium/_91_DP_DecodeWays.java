package leetcode.medium;

import java.util.Map;
import java.util.TreeMap;

/**
 * Next question
 * 92. Reverse Linked List II
 * More challenges
 * 639. Decode Ways II
 * 1977. Number of Ways to Separate Numbers
 *
 * 91. Decode Ways
 * Medium
 * 8.9K
 * 4.1K
 * company
 * Facebook
 * company
 * Microsoft
 * company
 * Uber
 * A message containing letters from A-Z can be encoded into numbers using the following mapping:
 *
 * 'A' -> "1"
 * 'B' -> "2"
 * ...
 * 'Z' -> "26"
 * To decode an encoded message, all the digits must be grouped then mapped back into letters using the reverse of the mapping
 * above (there may be multiple ways). For example, "11106" can be mapped into:
 *
 * "AAJF" with the grouping (1 1 10 6)
 * "KJF" with the grouping (11 10 6)
 * Note that the grouping (1 11 06) is invalid because "06" cannot be mapped into 'F' since "6" is different from "06".
 *
 * Given a string s containing only digits, return the number of ways to decode it.
 *
 * The test cases are generated so that the answer fits in a 32-bit integer.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "12"
 * Output: 2
 * Explanation: "12" could be decoded as "AB" (1 2) or "L" (12).
 * Example 2:
 *
 * Input: s = "226"
 * Output: 3
 * Explanation: "226" could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 * Example 3:
 *
 * Input: s = "06"
 * Output: 0
 * Explanation: "06" cannot be mapped to "F" because of the leading zero ("6" is different from "06").
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 100
 * s contains only digits and may contain leading zero(s).
 *
 */
public class _91_DP_DecodeWays {

    class Solution {
        public int numDecodings(String s) {
            if (s == null || s.length() < 1) {
                return 0;
            }
            var mapping = getMapping();
            var memo = new TreeMap<String, Integer>();
            return numDecodings(s, mapping, memo);
        }

        private int numDecodings(String s, Map<String, Character> mapping, Map<String, Integer> memo) {
            if (s == "") {
                return 1;
            }
            if (!mapping.containsKey(s.substring(0, 1))) {
                return 0;
            }
            if (memo.get(s) != null) {
                return memo.get(s);
            }

            int n = s.length();
            int result = 0;
            if (mapping.containsKey(s.substring(0, 1))) {
                result += 1 * numDecodings(s.substring(1), mapping, memo);
            }
            if (n >= 2 && mapping.containsKey(s.substring(0, 2))) {
                result += 1 * numDecodings(s.substring(2), mapping, memo);
            }
            memo.put(s, result);
            return result;
        }
    }

    private static Map<String, Character> getMapping() {
        var map = new TreeMap<String, Character>();
        for (int i = 0; i < 26; i++) {
            map.put(String.valueOf(i+1), (char) (i + 'A'));
        }
        return map;
    }
}
