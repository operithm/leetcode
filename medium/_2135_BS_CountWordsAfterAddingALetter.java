package leetcode.medium;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Success
 * Details
 * Runtime: 1607 ms, faster than 5.04% of Java online submissions for Count Words Obtained After Adding a Letter.
 * Memory Usage: 62.8 MB, less than 97.57% of Java online submissions for Count Words Obtained After Adding a Letter.
 *
 * Next challenges:
 * Strings Differ by One Character
 * Count Substrings That Differ by One Character
 * Maximum Score From Removing Substrings
 *
 * 2135. Count Words Obtained After Adding a Letter
 * Medium
 *
 * 502
 *
 * 111
 *
 * Add to List
 *
 * Share
 * You are given two 0-indexed arrays of strings startWords and targetWords. Each string consists of lowercase English letters only.
 *
 * For each string in targetWords, check if it is possible to choose a string from startWords and perform a conversion operation on it to be equal to that from targetWords.
 *
 * The conversion operation is described in the following two steps:
 *
 * Append any lowercase letter that is not present in the string to its end.
 * For example, if the string is "abc", the letters 'd', 'e', or 'y' can be added to it, but not 'a'. If 'd' is added, the resulting string will be "abcd".
 * Rearrange the letters of the new string in any arbitrary order.
 * For example, "abcd" can be rearranged to "acbd", "bacd", "cbda", and so on. Note that it can also be rearranged to "abcd" itself.
 * Return the number of strings in targetWords that can be obtained by performing the operations on any string of startWords.
 *
 * Note that you will only be verifying if the string in targetWords can be obtained from a string in startWords by performing the operations. The strings in startWords do not actually change during this process.
 *
 *
 *
 * Example 1:
 *
 * Input: startWords = ["ant","act","tack"], targetWords = ["tack","act","acti"]
 * Output: 2
 * Explanation:
 * - In order to form targetWords[0] = "tack", we use startWords[1] = "act", append 'k' to it, and rearrange "actk" to "tack".
 * - There is no string in startWords that can be used to obtain targetWords[1] = "act".
 *   Note that "act" does exist in startWords, but we must append one letter to the string before rearranging it.
 * - In order to form targetWords[2] = "acti", we use startWords[1] = "act", append 'i' to it, and rearrange "acti" to "acti" itself.
 * Example 2:
 *
 * Input: startWords = ["ab","a"], targetWords = ["abc","abcd"]
 * Output: 1
 * Explanation:
 * - In order to form targetWords[0] = "abc", we use startWords[0] = "ab", add 'c' to it, and rearrange it to "abc".
 * - There is no string in startWords that can be used to obtain targetWords[1] = "abcd".
 *
 *
 * Constraints:
 *
 * 1 <= startWords.length, targetWords.length <= 5 * 104
 * 1 <= startWords[i].length, targetWords[j].length <= 26
 * Each string of startWords and targetWords consists of lowercase English letters only.
 * No letter occurs more than once in any string of startWords or targetWords.
 *
 */
public class _2135_BS_CountWordsAfterAddingALetter {

    class Solution {
        public int wordCount(String[] startWords, String[] targetWords) {
            if (startWords == null || startWords.length < 1 || targetWords == null || targetWords.length < 1) {
                return 0;
            }

            var lengths = getLengthList(targetWords);
            var map = getStartWordMap(startWords, lengths);

            final AtomicInteger count = new AtomicInteger(0);
            for (String word : targetWords) {
                int len = word.length() - 1;
                var u = deleteOne(word);
                /**
                 * StartWords list map
                 */
                var v = map.getOrDefault(len, new TreeMap<String, List<String>>());
                for (var x : u) {
                    var prefix = x.substring(0, Math.min(3, len));
                    var w = v.getOrDefault(prefix, new LinkedList<>());
                    int index = Collections.binarySearch(w, x);
                    if (index >= 0) {
                        //System.out.printf("prefix: %s, w: %s, index: %s\n", prefix, w, index);
                        count.addAndGet(1);
                        break;
                    }
                }
            }
            return count.get();
        }

        private List<Integer> getLengthList(String[] targetWords) {
            var set = new HashSet<Integer>();
            for (String word : targetWords) {
                int len = word.length() - 1;
                if (len >= 1) {
                    set.add(len);
                }
            }
            var list = new LinkedList<>(set);
            Collections.sort(list);
            return list;
        }

        private Map<Integer, Map<String, List<String>>> getStartWordMap(String[] startWords, List<Integer> lengths) {
            var result = new TreeMap<Integer, Map<String, Set<String>>>();
            for (String word : startWords) {
                int len = word.length();
                int index = Collections.binarySearch(lengths, len);
                if (index < 0) {
                    continue;
                }

                char[] charArray = word.toCharArray();
                Arrays.sort(charArray);
                int i = Math.min(len, 3);
                StringBuilder sb = new StringBuilder("");
                for (int j = 0; j < i; j++) {
                    sb.append(charArray[j]);
                }
                String prefix = sb.toString();

                //System.out.printf("prefix: %s, array: %s\n", prefix, Arrays.toString(charArray));
                var map = result.getOrDefault(len, new HashMap<String, Set<String>>());
                var set = map.getOrDefault(prefix, new HashSet<String>());
                set.add(new String(charArray));
                map.put(prefix, set);
                result.put(len, map);
            }

            var map = new TreeMap<Integer, Map<String, List<String>>>();
            for (int key : result.keySet()) {
                var charMap = result.get(key);
                var listMap = new TreeMap<String, List<String>>();
                for (String s : charMap.keySet()) {
                    var list = new LinkedList<>(charMap.get(s));
                    Collections.sort(list);
                    listMap.put(s, list);
                }

                map.put(key, listMap);
            }
            //System.out.printf("map: %s\n", String.valueOf(map));
            return map;
        }


        private List<String> deleteOne(String target) {
            var result = new LinkedList<String>();
            var map = new TreeMap<Character, List<Integer>>();
            int len = target.length();
            char[] chars = target.toCharArray();
            Arrays.sort(chars);

            if (len > 1) {
                for (int i = 0; i < chars.length; i++) {
                    char u = chars[i];
                    var indices = map.getOrDefault(u, new LinkedList<Integer>());
                    indices.add(i);
                    map.put(u, indices);
                }
            }

            var listOfIndices = map.values().stream().filter(index -> index.size() == 1)
                    .flatMap(List::stream).collect(Collectors.toList());

            for (int x : listOfIndices) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < len; i++) {
                    if (x != i) {
                        sb.append(chars[i]);
                    }
                }
                result.add(sb.toString());
            }

            Collections.sort(result);
            //System.out.printf("list: %s\n", String.valueOf(result));
            return result;
        }
    }
}
