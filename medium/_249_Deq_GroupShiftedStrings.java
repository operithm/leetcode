package leetcode.medium;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Success
 * Details
 * Runtime: 347 ms, faster than 5.33% of Java online submissions for Group Shifted Strings.
 * Memory Usage: 55.3 MB, less than 5.26% of Java online submissions for Group Shifted Strings.
 * Next challenges:
 *
 * Lonely Pixel I
 * Minimum Area Rectangle
 * Number of Sub-arrays of Size K and Average Greater than or Equal to Thresh
 *
 * 249. Group Shifted Strings
 * Medium
 *
 * 1403
 *
 * 266
 *
 * Add to List
 *
 * Share
 * We can shift a string by shifting each of its letters to its successive letter.
 * For example, "abc" can be shifted to be "bcd".
 * We can keep shifting the string to form a sequence.
 *
 * For example, we can keep shifting "abc" to form the sequence: "abc" -> "bcd" -> ... -> "xyz".
 * Given an array of strings strings, group all strings[i] that belong to the same shifting sequence. You may return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: strings = ["abc","bcd","acef","xyz","az","ba","a","z"]
 * Output: [["acef"],["a","z"],["abc","bcd","xyz"],["az","ba"]]
 * Example 2:
 *
 * Input: strings = ["a"]
 * Output: [["a"]]
 *
 *
 * Constraints:
 *
 * 1 <= strings.length <= 200
 * 1 <= strings[i].length <= 50
 * strings[i] consists of lowercase English letters.
 *
 */
public class _249_Deq_GroupShiftedStrings {

    class Solution {
        public List<List<String>> groupStrings(String[] strings) {
            if (strings == null || strings.length == 0) {
                return new LinkedList<>();
            }

            var map = new HashMap<String, List<String>>();
            for (String str : strings) {
                var key = isomophic(str);
                var list = map.getOrDefault(key, new LinkedList<String>());
                list.add(str);
                map.put(key, list);
            }
            return new LinkedList<>(map.values());
        }

        private String isomophic(String str) {
            char v = str.charAt(0);
            StringBuilder sb = new StringBuilder("");
            for (char c : str.toCharArray()) {
                int diff = c - v;
                if ((diff < 0 && Math.abs(diff) >= 13) || diff > 13 ) {
                    diff = diff > 0 ? diff - 26 : diff + 26;
                }
                System.out.printf("c: %s, v: %s, diff: %s\n", c, v, diff);
                if (diff >= 0) {
                    sb.append("+");
                }
                sb.append(diff);
                v = c;
            }
            return sb.toString();
        }
    }
}
