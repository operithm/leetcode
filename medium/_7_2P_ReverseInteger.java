package leetcode.medium;

/**
 * Success
 * Details
 * Runtime: 4 ms, faster than 17.33% of Java online submissions for Reverse Integer.
 * Memory Usage: 41.3 MB, less than 56.17% of Java online submissions for Reverse Integer.
 * Next challenges:
 * Reverse Bits
 * A Number After a Double Reversal
 *
 * 7. Reverse Integer  Medium
 * ----------------------------------------------
 *
 * Given a signed 32-bit integer x, return x with its digits reversed. If reversing x causes the value to go outside the
 * signed 32-bit integer range [-231, 231 - 1], then return 0.
 *
 * Assume the environment does not allow you to store 64-bit integers (signed or unsigned).
 *
 *
 *
 * Example 1:
 *
 * Input: x = 123
 * Output: 321
 * Example 2:
 *
 * Input: x = -123
 * Output: -321
 * Example 3:
 *
 * Input: x = 120
 * Output: 21
 *
 *
 * Constraints:
 *
 * -231 <= x <= 231 - 1
 *
 */
public class _7_2P_ReverseInteger {

    class Solution {
        public int reverse(int x) {
            String str = String.valueOf(x);

            System.out.printf("x-> %s\n", str);
            char[] arr = str.toCharArray();
            int i = Character.isDigit(arr[0]) ? 0 : 1;
            int j = arr.length - 1;
            boolean isNegative = arr[0] == '-';

            while (i < j) {
                swap(i, j, arr);
                i++;
                j--;
            }

            str = String.valueOf(arr);
            System.out.printf("~x-> %s\n", str);
            if ((arr.length == 10 && str.compareTo(String.valueOf(Integer.MAX_VALUE)) > 0) || (arr.length == 11 && isNegative && str.compareTo(String.valueOf(Integer.MIN_VALUE)) > 0)) {
                System.out.printf("max: %s, min: %s\n", String.valueOf(Integer.MAX_VALUE), String.valueOf(Integer.MIN_VALUE));
                return 0;
            }

            return Integer.parseInt(str);

        }

        private void swap(int i, int j, char[] arr) {
            int len = arr.length;
            if (i >= 0 && i < len && j >= 0 && j < len) {
                char tmp = arr[j];
                arr[j] = arr[i];
                arr[i] = tmp;
            }
        }

    }

}
