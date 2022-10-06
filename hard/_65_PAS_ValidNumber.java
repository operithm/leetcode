package leetcode.hard;

/**
 * 65. Valid Number
 * Hard
 *
 * 652
 *
 * 1166
 *
 * Add to List
 *
 * Share
 * A valid number can be split up into these components (in order):
 *
 * A decimal number or an integer.
 * (Optional) An 'e' or 'E', followed by an integer.
 * A decimal number can be split up into these components (in order):
 *
 * (Optional) A sign character (either '+' or '-').
 * One of the following formats:
 * One or more digits, followed by a dot '.'.
 * One or more digits, followed by a dot '.', followed by one or more digits.
 * A dot '.', followed by one or more digits.
 * An integer can be split up into these components (in order):
 *
 * (Optional) A sign character (either '+' or '-').
 * One or more digits.
 * For example, all the following are valid numbers: ["2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789"], while the following are not valid numbers: ["abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53"].
 *
 * Given a string s, return true if s is a valid number.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "0"
 * Output: true
 * Example 2:
 *
 * Input: s = "e"
 * Output: false
 * Example 3:
 *
 * Input: s = "."
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 20
 * s consists of only English letters (both uppercase and lowercase), digits (0-9), plus '+', minus '-', or dot '.'.
 *
 * Success
 * Details
 * Runtime: 9 ms, faster than 19.88% of Java online submissions for Valid Number.
 * Memory Usage: 43.8 MB, less than 21.26% of Java online submissions for Valid Number.
 * Next challenges:
 * Zigzag Conversion
 * Making File Names Unique
 * Longest Path With Different Adjacent Characters
 */
public class _65_PAS_ValidNumber {

    class Solution {
        public boolean isNumber(String s) {
            if (s == null || s.length() < 1)  {
                return false;
            }
            String trans = s + "$";
            return parseSignedNumber(trans);
        }

        /**
         * A number is a SignedNumber
         *      Sign:   -> DecimalNumber
         *      Unsign: -> DecimalNumber
         *
         * @param trans
         * @return
         */
        private boolean parseSignedNumber(String trans) {
            if ( atEnd(trans) ) { return false; }
            char c = trans.charAt(0);
            if ( c == '+' || c == '-') {
                return parseDecimalNumber(trans.substring(1));
            }
            return parseDecimalNumber(trans);
        }

        /**
         * A decimal number is a ScientificNumber
         * @param trans
         * @return
         */
        private boolean parseDecimalNumber(String trans) {
            if ( atEnd(trans) ) { return false; }
            char c = trans.charAt(0);
            if (Character.isDigit(c)) {
                return parseDecimalNumberOrEnd(trans.substring(1));
            }
            if (c == '.') {
                return parseScientificNumber(trans.substring(1));
            }
            return false;
        }

        /**
         * A decimal number or the end
         * @param trans
         * @return
         */
        private boolean parseDecimalNumberOrEnd(String trans) {
            if (atEnd(trans)) {  return true; }
            char c = trans.charAt(0);
            if (Character.isDigit(c)) {
                return parseDecimalNumberOrEnd(trans.substring(1));
            }
            if (c == 'e' || c == 'E') {
                return parseScientificNumberSuffix(trans.substring(1));
            }
            /**
             * A dot can have no suffix
             */
            if (c == '.') {
                return parseScientificNumberOrEnd(trans.substring(1));
            }
            return false;
        }

        /**
         * Scientific Number must start with a number
         */
        private boolean parseScientificNumber(String trans) {
            if (atEnd(trans)) {  return false; }
            char c = trans.charAt(0);
            if (Character.isDigit(c)) {
                return parseScientificNumberOrEnd(trans.substring(1));
            }
            return false;
        }

        /**
         * Can be the end
         */
        private boolean parseScientificNumberOrEnd(String trans) {
            if (atEnd(trans)) { return true; }
            char c = trans.charAt(0);
            if (Character.isDigit(c)) {
                return parseScientificNumberOrEnd(trans.substring(1));
            }
            if (c == 'e' || c == 'E') {
                return parseScientificNumberSuffix(trans.substring(1));
            }
            return false;
        }

        /**
         * Can be the end
         */
        private boolean parseScientificNumberSuffix(String trans) {
            if (atEnd(trans)) { return false; }
            char c = trans.charAt(0);
            if (Character.isDigit(c)) {
                return parseNumbersOrEnd(trans.substring(1));
            }
            if (c == '+' || c == '-') {
                System.out.printf(" after sci-sign.");
                return parseNumbersAndEnd(trans.substring(1));
            }
            return false;
        }

        /**
         * only numbers or end
         */
        private boolean parseNumbersOrEnd(String trans) {
            if (atEnd(trans)) { return true; }
            char c = trans.charAt(0);
            if (Character.isDigit(c)) {
                return parseNumbersOrEnd(trans.substring(1));
            }
            return false;
        }

        /**
         * only numbers or end
         */
        private boolean parseNumbersAndEnd(String trans) {
            if (atEnd(trans)) { return false; }
            char c = trans.charAt(0);
            if (Character.isDigit(c)) {
                return parseNumbersOrEnd(trans.substring(1));
            }
            return false;
        }

        private boolean atEnd(String trans) {
            return (trans.length() == 0 || trans.charAt(0) == '$') ;
        }

    }
}
