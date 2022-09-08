package leetcode.medium;

/**
 * Given a string s, return the longest palindromic substring in s.
 *
 * Example 1:
 *
 * Input: s = "babad"
 * Output: "bab"
 * Explanation: "aba" is also a valid answer.
 * Example 2:
 *
 * Input: s = "cbbd"
 * Output: "bb"
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 1000
 * s consist of only digits and English letters.
 */
public class _5_2Pointer_LongestPalindrome {

    public static class Solution {

        public String longestPalindrome(String s) {
            if (s == null || s.isEmpty()) {
                return "";
            }
            int len = s.length();
            if (len == 1) {
                return s;
            }

            if (len == 2) {
                return s.charAt(0) == s.charAt(1) ?  s : s.substring(0, 1);
            }

            int maxM = 0;
            int maxL = 0;

            for (int i = 1; i < len - 1; i++) {
                int l = i;
                int r = i;
                while (isPalindrome(l-1, l, len, s)) {
                    l--;
                }
                while(isPalindrome(r, r+1, len, s)) {
                    r++;
                }
                //System.out.println(String.format("starting l: %s, r: %s", l, r));
                while (isPalindrome(l-1, r+1, len, s)) {
                    l--;
                    r++;
                }

                int pal = r-l+1;
                if (maxL < pal) {
                    maxL = pal;
                    maxM = l;
                }
                //System.out.println(String.format("l: %s, r: %s, max: %s", l, r, pal));
            }
            //System.out.println(String.format("s =%s, starting = %s, length = %s", s, maxM, maxL));
            return s.substring(maxM, maxM + maxL);
        }


    }

    private static boolean isPalindrome(int a, int b, int len, String s) {
        if (isInbound(a, len) && isInbound(b, len)) {
            return s.charAt(a) == s.charAt(b);
        }
        return false;
    }

    private static boolean isInbound(int i, int len) {
        return i >= 0 && i < len;
    }
}
