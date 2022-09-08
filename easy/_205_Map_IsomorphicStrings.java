package leetcode.easy;

import java.util.HashMap;

/**
 * Success
 * Details
 * Runtime: 36 ms, faster than 11.59% of Java online submissions for Isomorphic Strings.
 * Memory Usage: 42.9 MB, less than 68.56% of Java online submissions for Isomorphic Strings.
 * Next challenges:
 * Word Pattern
 * Show off your acceptance:
 *
 * 205. Isomorphic Strings
 * Easy
 *
 * 4696
 *
 * 852
 *
 * Add to List
 *
 * Share
 * Given two strings s and t, determine if they are isomorphic.
 *
 * Two strings s and t are isomorphic if the characters in s can be replaced to get t.
 *
 * All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character, but a character may map to itself.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "egg", t = "add"
 * Output: true
 * Example 2:
 *
 * Input: s = "foo", t = "bar"
 * Output: false
 * Example 3:
 *
 * Input: s = "paper", t = "title"
 * Output: true
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 5 * 104
 * t.length == s.length
 * s and t consist of any valid ascii character.
 * Accepted
 * 621,929
 * Submissions
 * 1,465,187
 */
public class _205_Map_IsomorphicStrings {
    class Solution {
        public boolean isIsomorphic(String s, String t) {
            if (s == null) {
                return t == null;
            }
            if (t == null || s.length() != t.length()) {
                return false;
            }

            var map = new HashMap<Character, Character>();
            int len = s.length();
            for (int i = 0; i < len; i++) {
                char k = s.charAt(i);
                char v = t.charAt(i);
                if (!map.keySet().contains(k)) {
                    if (!map.values().contains(v)) {
                        map.put(k, v);
                    } else {
                        return false;
                    }
                } else {
                    if (v != map.get(k)) {
                        return false;
                    }
                }
            }
            return true;
        }
    }
}
