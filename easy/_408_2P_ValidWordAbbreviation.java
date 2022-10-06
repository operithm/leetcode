package leetcode.easy;

/**
 * 408. (Easy) Valid Word Abbreviation
 *
 *
 * Share
 * A string can be abbreviated by replacing any number of non-adjacent, non-empty substrings with their lengths. The lengths should not have leading zeros.
 *
 * For example, a string such as "substitution" could be abbreviated as (but not limited to):
 *
 * "s10n" ("s ubstitutio n")
 * "sub4u4" ("sub stit u tion")
 * "12" ("substitution")
 * "su3i1u2on" ("su bst i t u ti on")
 * "substitution" (no substrings replaced)
 * The following are not valid abbreviations:
 *
 * "s55n" ("s ubsti tutio n", the replaced substrings are adjacent)
 * "s010n" (has leading zeros)
 * "s0ubstitution" (replaces an empty substring)
 * Given a string word and an abbreviation abbr, return whether the string matches the given abbreviation.
 *
 * A substring is a contiguous non-empty sequence of characters within a string.
 *
 *
 *
 * Example 1:
 *
 * Input: word = "internationalization", abbr = "i12iz4n"
 * Output: true
 * Explanation: The word "internationalization" can be abbreviated as "i12iz4n" ("i nternational iz atio n").
 * Example 2:
 *
 * Input: word = "apple", abbr = "a2e"
 * Output: false
 * Explanation: The word "apple" cannot be abbreviated as "a2e".
 *
 *
 * Constraints:
 *
 * 1 <= word.length <= 20
 * word consists of only lowercase English letters.
 * 1 <= abbr.length <= 10
 * abbr consists of lowercase English letters and digits.
 * All the integers in abbr will fit in a 32-bit integer.
 *
 * Success
 * Details
 * Runtime: 9 ms, faster than 8.15% of Java online submissions for Valid Word Abbreviation.
 * Memory Usage: 42.7 MB, less than 26.85% of Java online submissions for Valid Word Abbreviation.
 * Next challenges:
 * Minimum Unique Word Abbreviation
 * Word Abbreviation
 * Check if an Original String Exists Given Two Encoded Strings
 */
public class _408_2P_ValidWordAbbreviation {

    class Solution {
        public boolean validWordAbbreviation(String word, String abbr) {
            if (word == null || abbr == null ||word.length() < 1 || word.length() > 20 || abbr.length() < 1 || abbr.length() > 10) {
                return false;
            }

            if (word.equals(abbr)) {
                return true;
            }

            int i = 0;
            int j = 0;
            int m = word.length();
            int n = abbr.length();

            String number = "";
            /**
             * Whie TRUE: within bound
             */
            while(i < m &&  j < n) {
                char a =  abbr.charAt(j);
                char w =  word.charAt(i);
                /**
                 * Short circuited
                 */
                if (!Character.isDigit(a)) {
                    if (a == w) {
                        i++;
                        j++;
                    } else {
                        return false;
                    }
                }

                while (j < n && Character.isDigit(abbr.charAt(j))) {
                    number += abbr.charAt(j);
                    j++;
                }

                if (number.startsWith("0")) {
                    return false;
                }

                if (!number.equals("")) {
                    int count = Integer.parseInt(number);
                    number = "";
                    i+= count;
                }
            }

            return i == m && j == n;

        }
    }
}
