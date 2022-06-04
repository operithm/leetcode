package leetcode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * You are given a string s and an array of strings words of the same length. Return all starting indices of substring(s) in s
 * that is a concatenation of each word in words exactly once, in any order, and without any intervening characters.
 *
 * You can return the answer in any order.
 *
 * Example 1:
 *
 * Input: s = "barfoothefoobarman", words = ["foo","bar"]
 * Output: [0,9]
 * Explanation: Substrings starting at index 0 and 9 are "barfoo" and "foobar" respectively.
 * The output order does not matter, returning [9,0] is fine too.
 * Example 2:
 *
 * Input: s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
 * Output: []
 * Example 3:
 *
 * Input: s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
 * Output: [6,9,12]
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 104
 * s consists of lower-case English letters.
 * 1 <= words.length <= 5000
 * 1 <= words[i].length <= 30
 * words[i] consists of lower-case English letters.
 */
public class SubstringWithConcatAllWords {

    public static final class Solution {

        public List<Integer> findSubstring(String s, String[] words) {

            int width = words[0].length();
            Map<Integer, String> found = new HashMap<>();

            Arrays.stream(words).forEach(w -> kmp(s, w).stream().forEach(n -> found.put(n, w)));

            return found.keySet().stream().filter( f ->
                    found.keySet().contains(f + width) && found.get(f) != found.get(f + width)).collect(Collectors.toList());

        }

        private Set<Integer> kmp (String s, String w) {
            final Set<Integer> result = new HashSet<>();
            int[] T = kmpLookup(w);

            int j = 0;
            int k = 0;

            int i = 0;
            while (j < s.length()) {
                if (s.charAt(j) == w.charAt(k)) {
                    j++;
                    k++;
                    if(k == w.length()) {
                        result.add(j - k);
                        k = 0;          // k = T[w.length] ?
                    }
                } else {
                    k = T[k];
                    if (k < 0) {
                        j ++;
                        k ++;
                    }
                }
            }
            return result;
        }

        //w at least of length of 1
        private int[] kmpLookup(String w) {
            int[] T = new int[w.length()];
            T[0] = -1;
            int p = 1;          //starting second character
            int count = 0;      //starting from index 0 count

            while(p < w.length()) {

                if (w.charAt(p) == w.charAt(count)) {
                    T[p] = T[count];    //able to keep counting
                } else {
                    T[p] = count;       //up to this count
                    while (count >= 0 && w.charAt(p) != w.charAt(count)) {
                        count = T[count];
                    }
                }

                p++;
                count ++;
            }
            return T;
        }
    }

}
