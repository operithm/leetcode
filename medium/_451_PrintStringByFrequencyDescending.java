package leetcode.medium;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 451. Sort Characters By Frequency
 * Medium
 *
 * 4170
 *
 * 182
 *
 * Add to List
 *
 * Share
 * Given a string s, sort it in decreasing order based on the frequency of the characters. The frequency of a character is the number of times it appears in the string.
 *
 * Return the sorted string. If there are multiple answers, return any of them.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "tree"
 * Output: "eert"
 * Explanation: 'e' appears twice while 'r' and 't' both appear once.
 * So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
 * Example 2:
 *
 * Input: s = "cccaaa"
 * Output: "aaaccc"
 * Explanation: Both 'c' and 'a' appear three times, so both "cccaaa" and "aaaccc" are valid answers.
 * Note that "cacaca" is incorrect, as the same characters must be together.
 * Example 3:
 *
 * Input: s = "Aabb"
 * Output: "bbAa"
 * Explanation: "bbaA" is also a valid answer, but "Aabb" is incorrect.
 * Note that 'A' and 'a' are treated as two different characters.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 5 * 105
 * s consists of uppercase and lowercase English letters and digits.
 *
 * Success
 * Details
 * Runtime: 51 ms, faster than 17.41% of Java online submissions for Sort Characters By Frequency.
 * Memory Usage: 55.9 MB, less than 11.36% of Java online submissions for Sort Characters By Frequency.
 * Next challenges:
 * Top K Frequent Elements
 * First Unique Character in a String
 * Sort Array by Increasing Frequency
 * Percentage of Letter in String
 */
public class _451_PrintStringByFrequencyDescending {

    class Solution {

        public String frequencySort(String s) {
            if (s == null || s.length() < 1) {
                return s;
            }

            List<Character> list = new ArrayList<>();
            for (int i = 0; i < s.length(); i++) {
                list.add(s.charAt(i));
            }

            Map<Long, List<Character>> map = list.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .collect(
                    Collectors.groupingBy(
                        Map.Entry::getValue, // TreeMap<Long,List<Character>>
                        () -> new TreeMap<>( Collections.reverseOrder()),
                        Collectors.mapping(Map.Entry::getKey,Collectors.toList())
                    )
                );

            final StringBuilder sb = new StringBuilder("");
            map.entrySet().stream().forEach(entry -> {
                List<Character> charList = entry.getValue();
                Collections.sort(charList);
                int key = entry.getKey().intValue();
                charList.stream().forEach( c -> {
                    int count = key;
                    while(count > 0) {
                        sb.append(c);
                        count--;
                    }
                });
            });

            return sb.toString();
        }
    }
}
