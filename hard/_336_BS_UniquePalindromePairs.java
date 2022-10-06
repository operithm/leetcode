package leetcode.hard;

import java.util.*;

/**
 * Success
 * Details
 * Runtime: 2664 ms, faster than 5.13% of Java online submissions for Palindrome Pairs.
 * Memory Usage: 74.7 MB, less than 80.82% of Java online submissions for Palindrome Pairs.
 * Next challenges:
 * Shortest Palindrome
 * Longest Palindrome by Concatenating Two Letter Words
 *
 * 336. Palindrome Pairs
 * Hard
 *
 * 3878
 *
 * 405
 *
 * Add to List
 *
 * Share
 * Given a list of unique words, return all the pairs of the distinct indices (i, j) in the given list, so that the concatenation of the two words words[i] + words[j] is a palindrome.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["abcd","dcba","lls","s","sssll"]
 * Output: [[0,1],[1,0],[3,2],[2,4]]
 * Explanation: The palindromes are ["dcbaabcd","abcddcba","slls","llssssll"]
 * Example 2:
 *
 * Input: words = ["bat","tab","cat"]
 * Output: [[0,1],[1,0]]
 * Explanation: The palindromes are ["battab","tabbat"]
 * Example 3:
 *
 * Input: words = ["a",""]
 * Output: [[0,1],[1,0]]
 *
 * Constraints:
 *
 * 1 <= words.length <= 5000
 * 0 <= words[i].length <= 300
 * words[i] consists of lower-case English letters.
 *
 */
public class _336_BS_UniquePalindromePairs {
    static class CodeMap {
        private final Map<Character, Integer> map;
        private int odd;

        public CodeMap(String word) {
            this.map = buildMap(word);
            this.odd = odd();
        }

        private CodeMap(Map<Character, Integer> map) {
            this.map = map;
            this.odd = odd();
        }

        public int variance() {
            return map.size();
        }

        private int odd() {
            int count = 0;
            for (int val : map.values()) {
                count += (val % 2 == 0) ? 0 : 1;
            }
            return count;
        }

        private Map<Character, Integer> buildMap(String word) {
            Map<Character, Integer> map = new TreeMap<>();
            if (word != null) {
                char[] charArray = word.toCharArray();
                for (char c : charArray) {
                    Integer count = map.getOrDefault(c, 0);
                    count++;
                    map.put(c, count);
                }
            }
            return map;
        }

        public static CodeMap merge(CodeMap map1, CodeMap map2) {
            Map<Character, Integer> map = new TreeMap<>(map1.map);
            for (char c : map2.map.keySet()) {
                Integer val = map1.map.getOrDefault(c, 0);
                map.put(c, val + map2.map.get(c));
            }
            return new CodeMap(map);
        }
    }


    static class IndexedStr {
        private final int index;
        private final String val;
        private final boolean reverse;
        private final CodeMap codeMap;

        public IndexedStr(int index, String val) {
            this.index = index;
            this.val = val;
            this.reverse = false;
            this.codeMap = new CodeMap(val);
        }

        public IndexedStr(int index, String val, boolean reverse) {
            this.index = index;
            this.val = val;
            this.reverse = reverse;
            this.codeMap = new CodeMap(val);
        }

        public String getVal() {
            return this.val;
        }

        @Override
        public String toString() {
            return String.format("%s:%s - %s", val, index, reverse);
        }
    }

    class Solution {

        public List<List<Integer>> palindromePairs(String[] words) {
            if (words == null || words.length < 1) {
                return new LinkedList<>();
            }

            int n = words.length;
            var wordList = new IndexedStr[n*2];
            for (int i = 0; i < words.length; i++) {
                wordList[i*2] = new IndexedStr(i, words[i]);
                wordList[i*2+1] = new IndexedStr(i, getReverse(words[i]), true);
            }
            Arrays.sort(wordList, Comparator.comparing(IndexedStr::getVal));

            var result = new LinkedList<List<Integer>>();
            compare(result, wordList);

            return result;
        }

        private void compareEmpty(List<List<Integer>> output, IndexedStr empty, IndexedStr[] wordList, Map<String, Boolean> memo) {
            if (empty.val.length() == 0) {
                int index = empty.index;
                for (IndexedStr word : wordList) {
                    if (!word.reverse && word.index != index && isPalindrome(word.val, memo)) {
                        output.add(List.of(index, word.index));
                        output.add(List.of(word.index, index));
                    }
                }
            }

        }

        private void compare(List<List<Integer>> output, IndexedStr[] wordList) {
            final Map<String, Boolean> memo = new TreeMap<>();
            int n = wordList.length;

            for (int i = 0; i < n; i++) {
                IndexedStr a = wordList[i];
                if (a.val.length() == 0) {
                    if (!a.reverse) {
                        compareEmpty(output, a, wordList, memo);
                    } else {
                        continue;
                    }
                }

                int u = 0;
                int len = a.val.length();
                if (len >= 1) {
                    char suffix = (char) (a.val.charAt(len - 1) + 1);
                    IndexedStr upper = new IndexedStr(-1, a.val.substring(0, len-1) + suffix);
                    u = getIndex(wordList, upper);
                }

                int j = i + 1;
                while (j < n && j <= u) {
                    IndexedStr b = wordList[j];
                    if (b.val.length() != 0 && a.val.length() != 0) {
                        var list = isPalindrome(a, b, memo);
                        if (!list.isEmpty()) {
                            output.add(list);
                        }
                    }
                    j++;
                }
            }
        }

        private int getIndex(IndexedStr[] source, IndexedStr target) {
            int index = Arrays.binarySearch(source, target, Comparator.comparing(IndexedStr::getVal));
            if (index < 0) {
                index = -index -1;
            }
            return index;
        }

        private String getReverse(String val) {
            char[] array = val.toCharArray();
            int i = 0, j = array.length - 1;
            while (i <= j) {
                char tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;
                i++;
                j--;
            }
            return String.valueOf(array);
        }

        private List<Integer> isPalindrome(IndexedStr a, IndexedStr b, Map<String, Boolean> memo) {
            if (a.index == b.index || a.reverse == b.reverse) {
                return List.of();
            }

            if (a.reverse) {
                IndexedStr tmp = a;
                a = b;
                b = tmp;
            }

            boolean isPalindrome = true;
            CodeMap codeMap = CodeMap.merge(a.codeMap, b.codeMap);
            if (codeMap.variance() > 1 && codeMap.odd > 1) {
                memo.put(a.val + getReverse(b.val), false);
                return List.of();
            }

            if (codeMap.variance() == 1) {
                memo.put(a.val + b.val, true);
                return List.of(a.index, b.index);
            }

            if (a.val.startsWith(b.val)) {
                isPalindrome = isPalindrome(a.val.substring(b.val.length()), memo);
            } else if (b.val.startsWith(a.val)) {
                isPalindrome = isPalindrome(b.val.substring(a.val.length()), memo);
            } else {
                isPalindrome = isPalindrome(a.val + getReverse(b.val), memo);
            }

            return isPalindrome ? List.of(a.index, b.index) : List.of();
        }

        private boolean isPalindrome(String str, Map<String, Boolean> memo) {
            if (str == "" || str.length() == 1) {
                return true;
            }
            Boolean result = memo.get(str);
            if (result == null) {
                result = true;
                int i = 0, j = str.length() - 1;
                while (i <= j) {
                    if (str.charAt(i) != str.charAt(j)) {
                        result = false;
                        break;
                    }
                    i++;
                    j--;
                }
                memo.put(str, result);
            }
            return result;
        }
    }
}
