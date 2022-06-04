package leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * A valid IP address consists of exactly four integers separated by single dots. Each integer is between 0 and 255
 * (inclusive) and cannot have leading zeros.
 *
 * For example, "0.1.2.201" and "192.168.1.1" are valid IP addresses, but "0.011.255.245", "192.168.1.312" and "192.168@1.1"
 * are invalid IP addresses.
 *
 * Given a string s containing only digits, return all possible valid IP addresses that can be formed by inserting dots into s.
 * You are not allowed to reorder or remove any digits in s. You may return the valid IP addresses in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "25525511135"
 * Output: ["255.255.11.135","255.255.111.35"]
 * Example 2:
 *
 * Input: s = "0000"
 * Output: ["0.0.0.0"]
 * Example 3:
 *
 * Input: s = "101023"
 * Output: ["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 20
 * s consists of digits only.
 */
public class _93_RestoreIPAddresses {

    public static class Solution {

        public List<String> restoreIpAddresses(String s) {
            List<String> result = new ArrayList<>();
            if (s == null || s.isBlank()) {
                return result;
            }

            List<String> prefix = new ArrayList<>();
            restoreIpAddresses(result, prefix, 0, s);

            return result;
        }

        private void restoreIpAddresses(List<String> result, List<String> prefix, int index, String suffix) {
            if (index > 3) {
                if (prefix.size() == 4 && suffix.isBlank()) {
                    result.add(String.join(".", prefix));
                }
                return;
            }

            int maxLen = Math.min(suffix.length(), 3);
            for (int i = 1; i <= maxLen; i++) {
                //make a copy
                List<String> nextPrefix = new ArrayList<>(prefix);
                String head = suffix.substring(0, i);
                String tail = suffix.substring(i);
                if (isValidIpAddress(head)) {
                    nextPrefix.add(head);
                    restoreIpAddresses(result, nextPrefix, index+1, tail);
                }
            }
        }

        private boolean isValidIpAddress(String subStr) {
            if (subStr.startsWith("0") && subStr.length()>=2) {
                return false;
            }
            try {
                int parsed = Integer.parseInt(subStr);
                return parsed >= 0 && parsed <= 255;
            } catch(Exception exp) {
                return false;
            }
        }
    }

}
