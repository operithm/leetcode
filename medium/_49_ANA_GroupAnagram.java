package leetcode.medium;

import java.util.*;

/**
 * Success
 * Details
 * Runtime: 189 ms, faster than 5.04% of Java online submissions for Group Anagrams.
 * Memory Usage: 63.2 MB, less than 12.87% of Java online submissions for Group Anagrams.
 * Next challenges:
 * Valid Anagram
 * Group Shifted Strings
 * Find Resultant Array After Removing Anagrams
 *
 * 49. Group Anagrams
 * Medium
 *
 * 11491
 *
 * 363
 *
 * Add to List
 *
 * Share
 * Given an array of strings strs, group the anagrams together. You can return the answer in any order.
 *
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.
 *
 *
 *
 * Example 1:
 *
 * Input: strs = ["eat","tea","tan","ate","nat","bat"]
 * Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * Example 2:
 *
 * Input: strs = [""]
 * Output: [[""]]
 * Example 3:
 *
 * Input: strs = ["a"]
 * Output: [["a"]]
 *
 *
 * Constraints:
 *
 * 1 <= strs.length <= 104
 * 0 <= strs[i].length <= 100
 * strs[i] consists of lowercase English letters.
 */
public class _49_ANA_GroupAnagram {

    class Solution {
        public List<List<String>> groupAnagrams(String[] strs) {
            final List<List<String>> result = new LinkedList<>();
            if (strs == null || strs.length < 1) {
                return result;
            }

            Map<String, List<String>> map = new HashMap<>();
            for (String word : strs) {
                //System.out.printf(" --> %s\n", word);
                if (word != null) {
                    char[] chars = word.toCharArray();
                    Arrays.sort(chars);
                    String key = String.valueOf(chars);

                    List<String> val = map.getOrDefault(key, new LinkedList<>());
                    val.add(word);
                    map.put(key, val);
                }
            }

            map.values().stream().forEach( list -> result.add(list));
            return result;

        }
    }
}
