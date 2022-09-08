package leetcode.hard;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Success
 * Details
 * Runtime: 393 ms, faster than 16.03% of Java online submissions for Substring with Concatenation of All Words.
 * Memory Usage: 42.8 MB, less than 97.16% of Java online submissions for Substring with Concatenation of All Words.
 * Next challenges:
 * Minimum Window Substring
 *
 *
 * You are given a string s and an array of strings words of the same length. Return all starting indices of substring(s)
 * in s that is a concatenation of each word in words exactly once, in any order, and without any intervening characters.
 *
 * You can return the answer in any order.
 *
 *
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
public class _30_SubstringWithConcatAllWords {

    class Solution {
        public List<Integer> findSubstring(String s, String[] words) {
            final List<Integer> result = new ArrayList<>();
            if (words == null || words.length < 1 || s == null || s.isEmpty()) {
                return result;
            }

            int len = words.length * words[0].length();
            int xlen = words[0].length();
            int i = 0;
            Map<Character, List<String>> map = prefixMap(words);

            while (i <= s.length() - len) {
                while (i < s.length() - len && !map.keySet().contains(s.charAt(i))) {
                    i++;
                }
                //System.out.println(String.format("i: %s", i));
                if (i <= s.length() - xlen && contains(i, s, xlen, prefixMap(words))) {
                    result.add(i);
                }
                i++;
            }

            return result;
        }
    }

    private static boolean contains(int i, String s, int xlen, Map<Character, List<String>> template) {
        int start = i;
        System.out.println(String.format("last: %s", s.length() - xlen));
        while (start <= s.length() - xlen && !template.isEmpty()) {
            int end = start + xlen;
            String word = s.substring(start, end);
            //System.out.println(String.format("word: %s at start: %s, end: %s", word, start, end));
            char c = word.charAt(0);

            if (template.keySet().contains(c) && template.get(c).contains(word)) {
                template.get(c).remove(word);
                if (template.get(c).isEmpty()) {
                    template.remove(c);
                }
                start+= xlen;
                //System.out.println(String.format("template size: %s at start: %s", template.size(), start));
            } else {
                break;
            }

        }
        return template.isEmpty();
    }

    private static Map<Character, List<String>> prefixMap (String[] words) {
        Map<Character, List<String>> map = new TreeMap<>();
        for (String word : words) {
            char prefix = word.charAt(0);
            List<String> list = map.getOrDefault(prefix, new ArrayList<>());
            list.add(word);
            map.put(prefix, list);
        }
        return map;
    }
}
