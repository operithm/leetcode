package leetcode.easy;

/**
 * 2231. Largest Number After Digit Swaps by Parity
 * Easy

 * You are given a positive integer num. You may swap any two digits of num that have the same parity
 * (i.e. both odd digits or both even digits).
 *
 * Return the largest possible value of num after any number of swaps.
 *
 * Example 1:
 *
 * Input: num = 1234
 * Output: 3412
 *
 * Explanation: Swap the digit 3 with the digit 1, this results in the number 3214.
 * Swap the digit 2 with the digit 4, this results in the number 3412.
 * Note that there may be other sequences of swaps but it can be shown that 3412 is the largest possible number.
 * Also note that we may not swap the digit 4 with the digit 1 since they are of different parities.
 * Example 2:
 *
 * Input: num = 65875
 * Output: 87655
 * Explanation: Swap the digit 8 with the digit 6, this results in the number 85675.
 * Swap the first digit 5 with the digit 7, this results in the number 87655.
 * Note that there may be other sequences of swaps but it can be shown that 87655 is the largest possible number.
 *
 *
 * Constraints:
 *
 * 1 <= num <= 109
 *
 * Success
 * Details
 * Runtime: 2 ms, faster than 79.08% of Java online submissions for Largest Number After Digit Swaps by Parity.
 * Memory Usage: 41 MB, less than 62.88% of Java online submissions for Largest Number After Digit Swaps by Parity.
 * Next challenges:
 * Largest Number At Least Twice of Others
 * Sort Array By Parity
 * Sort Array By Parity II
 * Smallest String With Swaps
 * Rearrange Array Elements by Sign
 */
public class _2231_2P_MaxNumberFromParityDigits {

    class Solution {
        public int largestInteger(int num) {
            if (num <= 0) {
                throw new IllegalArgumentException("input is not a positive number.");
            }

            String str = String.valueOf(num);
            //System.out.printf("str: %s\n", str);

            char[] chars = str.toCharArray();
            int len = chars.length;

            boolean[] parity = new boolean[len];
            for (int i = 0; i < len; i++) {
                parity[i] = (chars[i] - '0') % 2 == 0;
            }

            StringBuilder sb = new StringBuilder("");
            for (int i = 0; i < len; i++) {
                char c = chars[i];
                boolean evenOrOdd = parity[i];
                for (int j = i; j < len; j++) {
                    if (evenOrOdd == parity[j]  && c < chars[j]) {
                        char t = chars[j];
                        chars[j] = c;
                        c = t;
                    }
                }
                chars[i] = c;
                sb.append(c);
            }

            return Integer.parseInt(sb.toString());
        }
    }
}
