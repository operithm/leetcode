package leetcode.easy;

import java.util.Set;
import java.util.TreeSet;

/**
 * 1684. Count the Number of Consistent Strings
 * Easy
 *
 * 909
 *
 * 41
 *
 * Add to List
 *
 * Share
 * You are given a string allowed consisting of distinct characters and an array of strings words. A string is consistent if all characters in the string appear in the string allowed.
 *
 * Return the number of consistent strings in the array words.
 *
 *
 *
 * Example 1:
 *
 * Input: allowed = "ab", words = ["ad","bd","aaab","baa","badab"]
 * Output: 2
 * Explanation: Strings "aaab" and "baa" are consistent since they only contain characters 'a' and 'b'.
 * Example 2:
 *
 * Input: allowed = "abc", words = ["a","b","c","ab","ac","bc","abc"]
 * Output: 7
 * Explanation: All strings are consistent.
 * Example 3:
 *
 * Input: allowed = "cad", words = ["cc","acd","b","ba","bac","bad","ac","d"]
 * Output: 4
 * Explanation: Strings "cc", "acd", "ac", and "d" are consistent.
 *
 *
 * Constraints:
 *
 * 1 <= words.length <= 104
 * 1 <= allowed.length <= 26
 * 1 <= words[i].length <= 10
 * The characters in allowed are distinct.
 * words[i] and allowed contain only lowercase English letters.
 * Accepted
 * 90,363
 * Submissions
 * 110,350
 *
 *
 */
public class _1684_CountNumbersOfConsistentString {

    class Solution {
        public int countConsistentStrings(String allowed, String[] words) {
            if (allowed == null || allowed.length() < 1) {
                return 0;
            }

            /**
             Distinct chararcters
             */
            Set<Character> set = new TreeSet<>();
            for (char c : allowed.toCharArray()) {
                set.add(c);
            }

            int count = 0;
            for (String word : words) {
                if (word != null && consistent(word, set)) {
                    count++;
                }
            }

            return count;
        }

        /**
         *
         * @param word
         * @param set
         * @return
         */
        private boolean consistent(String word, Set<Character> set) {
            if (word == null || word.length() < 1) {
                return false;
            }
            Set<Character> charSet = new TreeSet<>();
            for (char c : word.toCharArray()) {
                charSet.add(c);
            }
            //remove all -> A - B
            charSet.removeAll(set);
            return charSet.size() == 0;
        }
    }
}
