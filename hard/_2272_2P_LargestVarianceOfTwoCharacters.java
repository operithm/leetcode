package leetcode.hard;

import java.util.*;

/**
 * Runtime 498 ms Beats 16.94% Memory 43.4 MB Beats 45.91%
 *
 * 2272. Substring With Largest Variance
 * Hard
 * 535
 * 62
 * company
 * Amazon
 * The variance of a string is defined as the largest difference between the number of occurrences of any 2 characters present in the string. Note the two characters may or may not be the same.
 *
 * Given a string s consisting of lowercase English letters only, return the largest variance possible among all substrings of s.
 *
 * A substring is a contiguous sequence of characters within a string.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aababbb"
 * Output: 3
 * Explanation:
 * All possible variances along with their respective substrings are listed below:
 * - Variance 0 for substrings "a", "aa", "ab", "abab", "aababb", "ba", "b", "bb", and "bbb".
 * - Variance 1 for substrings "aab", "aba", "abb", "aabab", "ababb", "aababbb", and "bab".
 * - Variance 2 for substrings "aaba", "ababbb", "abbb", and "babb".
 * - Variance 3 for substring "babbb".
 * Since the largest possible variance is 3, we return it.
 * Example 2:
 *
 * Input: s = "abcde"
 * Output: 0
 * Explanation:
 * No letter occurs more than once in s, so the variance of every substring is 0.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 104
 * s consists of lowercase English letters.
 */
public class _2272_2P_LargestVarianceOfTwoCharacters {

    static class Count {
        private int count;
        private final char id;

        public Count(char id) {
            this.count = 1;
            this.id = id;
        }

        public int getCount() { return count; }
        public void addOne() { count++; }

        public void removeOne() {
            if (count > 0) {
                count--;
            } else {
                throw new IllegalArgumentException("empty count");
            }
        }

        @Override
        public String toString() {
            return String.format("{%s->%s}", id, count);
        }
    }

    static class CharMap {
        private final Map<Character, Count> map;
        private final LinkedList<Count> codes;

        public CharMap() {
            this.map = new TreeMap<>();
            this.codes = new LinkedList<>();
        }

        public CharMap(String str) {
            this.map = new TreeMap<>();
            for (char a : str.toCharArray()) {
                var count = map.get(a);
                if (count == null) {
                    count = new Count(a);
                } else {
                    count.addOne();
                }
                map.put(a, count);
            }
            this.codes = new LinkedList<>(map.values());
            Collections.sort(codes, Comparator.comparing(Count::getCount));
        }

        private CharMap(Map<Character, Count> map, LinkedList<Count> codes) {
            this.map = new TreeMap<>(map);
            this.codes = new LinkedList<>(codes);
        }

        public CharMap addMap(CharMap another) {
            for (char c : another.map.keySet()) {
                var count = this.map.get(c);
                var anCount = another.map.get(c);
                if (count == null) {
                    this.map.put(c, anCount);
                } else {
                    count.count += anCount.count;
                    this.map.put(c, count);
                }
            }
            this.codes.clear();
            this.codes.addAll(this.map.values());
            Collections.sort(codes, Comparator.comparing(Count::getCount));
            return this;
        }

        public void addChar(char id) {
            var count = map.get(id);
            if (count == null) {
                count = new Count(id);
                map.put(id, count);
                codes.addFirst(count);
            } else {
                codes.remove(count);
                count.addOne();
                update(count);
            }
        }

        public void removeChar(char id) {
            //System.out.printf("\tmap: %s to remove: %s\n", String.valueOf(map), id);
            var count = map.get(id);
            if (count != null) {
                codes.remove(count);
                count.removeOne();
                update(count);
            }
        }

        public int getVariance() {
            if (codes.size() <= 1) {
                return 0;
            }
            return codes.peekLast().count - codes.peekFirst().count;
        }

        public CharMap clone() {
            return new CharMap(this.map, this.codes);
        }

        private void update(Count count) {
            if (count.count > 0) {
                map.put(count.id, count);
                int index = Collections.binarySearch(codes, count, Comparator.comparing(Count::getCount));
                int len = codes.size();
                if (index >= 0) {
                    addAt(len, index, count);
                } else {
                    index = -index - 1;
                    addAt(len, index, count);
                }
            } else {
                map.remove(count.id);
            }
        }

        private void addAt(int len, int index, Count count) {
            if (len <= index) {
                codes.add(count);
            } else {
                codes.add(index, count);
            }
        }
    }

    class Solution {

        public int largestVariance(String s) {
            int[] freq = new int[26];
            for (int i = 0; i < s.length(); i++) {
                freq[s.charAt(i) - 'a']++;
            }

            int largest = 0;
            for (char ch1 = 'a'; ch1 <= 'z'; ch1++) {
                for (char ch2 = 'a'; ch2 <= 'z'; ch2++) {
                    int ch1Count = freq[ch1 - 'a'];
                    int ch2Count = freq[ch2 - 'a'];
                    if (ch1 == ch2 || ch1Count == 0 || ch2Count == 0) {
                        continue;
                    }

                    int f1 = 0, f2 = 0;
                    for (int i = 0; i < s.length(); i++) {
                        if (s.charAt(i) == ch1) {
                            f1++;
                        }
                        if (s.charAt(i) == ch2) {
                            f2++;
                            ch2Count--;
                        }

                        if (f1 > 0 && f2 > 0 && f1 > f2) {
                            largest = Math.max(largest, f1 - f2);
                        }

                        if (ch2Count > 0 && f1 < f2) {
                            f1 = f2 = 0;
                        }
                    }
                }
            }

            return largest;
        }

        public int largestVariance4(String s) {
            if (s == null || s.length() <= 2) {
                return 0;
            }

            int n = s.length();
            int max = Integer.MIN_VALUE;
            int k = 2;

            for (int i = 0; i < n - k; i++) {
                var map = new CharMap(s.substring(i, i+k));
                for (int j = i + k; j < n; j++) {
                    map.addChar(s.charAt(j));
                    int variance = map.getVariance();
                    if (variance > max) {
                        max = variance;
                    }
                }
            }
            return max;
        }

        public int largestVariance2(String s) {
            if (s == null || s.length() <= 2) {
                return 0;
            }

            int n = s.length();
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < n - 2; i++) {
                int j = i + 2;
                var map = new CharMap(s.substring(i, j));
                while (j < n) {
                    map.addChar(s.charAt(j++));
                    if (max < map.getVariance()) {
                        max = map.getVariance();
                    }
                }
            }
            return max;
        }

        public int largestVariance3(String s) {
            if (s == null || s.length() <= 2) {
                return 0;
            }

            int n = s.length();
            int max = Integer.MIN_VALUE;

            Map<Character, LinkedList<Integer>> map = new TreeMap<>();
            for (int i = 0; i < n; i++) {
                char c = s.charAt(i);
                var list = map.getOrDefault(c, new LinkedList<>());
                list.add(i);
                map.put(c, list);
            }

            var indices = new LinkedList<LinkedList<Integer>>(map.values());
            System.out.printf("indices: %s\n", String.valueOf(indices));
            Collections.sort(indices, Comparator.comparing(List::size));
            if (indices.size() <= 1) {
                return 0;
            }
            int m = indices.size() / 2;
            for (int i = indices.size() - 1; i >= m; i-- ) {
                for (int j = 0; j < m; j++ ) {
                    int variance = variance(indices.get(j), indices.get(i));
                    if (variance > max) {
                        max = variance;
                    }
                }
            }

            return max;
        }

        /**
         *  |-----------|
         * |-----------------|
         */
        private int variance(LinkedList<Integer> small, LinkedList<Integer> large) {
            int m = small.size();
            int n = large.size();
            int right = Collections.binarySearch(small, large.peekLast());
            if (right < 0) {
                right = -right - 1;
            }
            int left = Collections.binarySearch(small, large.peekFirst());
            if (left < 0) {
                left = -left - 1;
            }

            System.out.printf("left %s, right: %s, n: %s\n", left, right, n);
            if (left == 0) {
                if (right == 0) {
                    return n - 1;
                } else {
                    return n - right;
                }
            } else if (left == m) {
                return n - 1;
            } else {
                if (right == m) {
                    return n - (m - left);
                } else {
                    return n - (right - left + 1);
                }

            }
        }

        public int dp(CharMap map, String str) {
            //System.out.printf("dp map: %s to on: %s\n", String.valueOf(map.map), str);
            int len = str.length();
            if (len <= 2) {
                return 0;
            } else {
                int self = map.getVariance();
                char first = str.charAt(0);
                char last = str.charAt(len - 1);
                map.removeChar(first);
                int variance1 = dp(map, str.substring(1));
                map.addChar(first);
                map.removeChar(last);
                int variance2 = dp(map, str.substring(0, len - 1));
                map.addChar(last);
                return Math.max(self, Math.max(variance1, variance2));
            }

        }

    }
}
