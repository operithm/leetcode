package leetcode.medium;

import java.util.Stack;

/**
 * 1249. Minimum Remove to Make Valid Parentheses
 * Medium
 *
 * 4991
 *
 * 90
 *
 * Add to List
 *
 * Share
 * Given a string s of '(' , ')' and lowercase English characters.
 *
 * Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting parentheses string is valid and return any valid string.
 *
 * Formally, a parentheses string is valid if and only if:
 *
 * It is the empty string, contains only lowercase characters, or
 * It can be written as AB (A concatenated with B), where A and B are valid strings, or
 * It can be written as (A), where A is a valid string.
 *
 *
 * Example 1:
 *
 * Input: s = "lee(t(c)o)de)"
 * Output: "lee(t(c)o)de"
 * Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.
 * Example 2:
 *
 * Input: s = "a)b(c)d"
 * Output: "ab(c)d"
 * Example 3:
 *
 * Input: s = "))(("
 * Output: ""
 * Explanation: An empty string is also valid.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 105
 * s[i] is either'(' , ')', or lowercase English letter.
 *
 * Success
 * Details
 * Runtime: 77 ms, faster than 13.13% of Java online submissions for Minimum Remove to Make Valid Parentheses.
 * Memory Usage: 54.5 MB, less than 25.93% of Java online submissions for Minimum Remove to Make Valid Parentheses.
 * Next challenges:
 * Minimum Number of Swaps to Make the String Balanced
 * Check if a Parentheses String Can Be Valid
 */
public class _1249_Stack_MinimumRemoveToValidParenthesis {

    class Solution {
        public String minRemoveToMakeValid(String s) {
            if (s == null || s.length() < 1) {
                return s;
            }

            char[] arr = s.toCharArray();
            String first = parse(arr, '(', ')');
            arr = first.toCharArray();

            reverse(arr);
            String second = parse(arr, ')', '(');

            arr = second.toCharArray();
            reverse(arr);

            return String.valueOf(arr);

        }

        private void reverse(char[] arr) {
            int high = arr.length - 1;
            int low  = 0;
            while (low < high) {
                char tmp = arr[low];
                arr[low] = arr[high];
                arr[high] = tmp;
                low++;
                high--;
            }
        }

        private String parse(char[] arr, char start, char end) {
            Stack<Character> stack = new Stack<>();
            StringBuilder sb = new StringBuilder("");

            for (char c : arr) {
                if (c == start) {
                    sb.append(c);
                    stack.push(c);
                    continue;
                }
                if (c == end) {
                    if (!stack.isEmpty()) {
                        stack.pop();
                        sb.append(c);
                    }
                    continue;
                }
                sb.append(c);
            }

            return sb.toString();
        }
    }
}
