package leetcode.medium;

import java.util.*;

/**
 * Next question
 * 160. Intersection of Two Linked Lists
 * More challenges
 * 239. Sliding Window Maximum
 * 340. Longest Substring with At Most K Distinct Characters
 * 992. Subarrays with K Different Integers
 *
 * 159. Longest Substring with At Most Two Distinct Characters
 * Medium
 * 1.9K
 * 29
 * company
 * Google
 * Given a string s, return the length of the longest substring that contains at most two distinct characters.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "eceba"
 * Output: 3
 * Explanation: The substring is "ece" which its length is 3.
 * Example 2:
 *
 * Input: s = "ccaabbb"
 * Output: 5
 * Explanation: The substring is "aabbb" which its length is 5.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 105
 * s consists of English letters.
 */
public class _159_2P_LongestSubstringWithAtmostTwoDistinctCharacters {

    class Solution {
        public int lengthOfLongestSubstringTwoDistinct(String s) {
            if (s == null) {
                return 0;
            }
            if (s.length() <= 2) {
                return s.length();
            }
            int n = s.length();
            Set<Character> set = new HashSet<>();
            int max = 0;
            for (int i = 0; i < n; i ++) {
                set.clear();
                set.add(s.charAt(i));
                for (int j = i; j < n && set.size() <= 2; j++) {
                    set.add(s.charAt(j));
                    if (set.size() <= 2) {
                        max = Math.max(max, j-i + 1);
                    }
                }
            }
            return max;
        }


        public int lengthOfLongestSubstringTwoDistinct1(String s) {
            if (s == null) {
                return 0;
            }
            if (s.length() <= 2) {
                return s.length();
            }
            var map = new TreeMap<Character, LinkedList<int[]>>();
            var queue = new LinkedList<Integer>();
            HashSet<Character>[] edges = new HashSet[52];
            for (int i = 0; i < 52; i++) {
                edges[i] = new HashSet<Character>();
            }

            char current = s.charAt(0);
            for (int i = 0; i < s.length(); i++) {
                char next = s.charAt(i);
                if (next == current) {
                    queue.offer(i);
                    continue;
                } else {
                    int[] index = new int[] {queue.peekFirst(), queue.peekLast()};
                    update(current, index, map);
                    edges[charToIndex(current)].add(next);
                    edges[charToIndex(next)].add(current);
                    current = next;
                    queue.clear();
                    queue.offer(i);
                }
            }
            int[] index = new int[] {queue.peekFirst(), queue.peekLast()};
            update(current, index, map);
            boolean[] visited = new boolean[52];
            //print(map.get(current));
            //System.out.printf("edges: %s, map: %s\n", Arrays.toString(edges), String.valueOf(map));
            if (getNext(edges, visited) == '\0') {
                return getLongest(map);
            }

            return BFS(edges, map, visited);
        }

        private int BFS(HashSet<Character>[] edges, TreeMap<Character, LinkedList<int[]>> graph, boolean[] visited) {
            Queue<Character> queue = new LinkedList<Character>();
            char c = getNext(edges, visited);
            if (c != '\0') {
                queue.offer(c);
            }

            int max = 0;
            while(!queue.isEmpty()) {
                char v = queue.remove();
                visited[charToIndex(v)] = true;
                var u = edges[charToIndex(v)];

                for (char w : u) {
                    if (!visited[charToIndex(w)]) {
                        max = Math.max(max, getLongest(graph.get(v), graph.get(w)));
                    }
                }

                if (queue.isEmpty()) {
                    char x = getNext(edges, visited);
                    if (x != '\0') {
                        queue.offer(x);
                    }
                }
            }
            return max;
        }

        private char getNext(HashSet<Character>[] edges, boolean[] visited) {
            for (int i = 0; i < 52; i++) {
                if (!visited[i] && !edges[i].isEmpty()) {
                    if (i < 26) {
                        return (char) (i + 'a');
                    } else {
                        return (char) (i - 26 + 'A');
                    }
                }
            }
            return '\0';
        }

        private int charToIndex(char c) {
            if (Character.toLowerCase(c) == c) {
                return c - 'a';
            } else {
                return c - 'A' + 26;
            }
        }

        private void print(LinkedList<int[]> list) {
            for (int[] r : list) {
                System.out.printf("%s\t", Arrays.toString(r));
            }
            System.out.printf("\n");
        }

        private void update(char c, int[] index, Map<Character, LinkedList<int[]>> map) {
            var list = map.getOrDefault(c, new LinkedList<>());
            list.add(index);
            map.put(c, list);
        }

        private int getLongest (LinkedList<int[]> v1, LinkedList<int[]> v2) {
            var merged = new LinkedList<int[]>();
            v1.removeIf( x -> {
                int index = Collections.binarySearch(v2, x, (a, b) -> (a[0] - b[0]));
                int n = v2.size();
                int[] y = new int[]{x[0], x[1]};
                boolean result = false;
                if (index < 0) {
                    index = -index - 1;
                }

                if (index == 0) {
                    if (v2.get(0)[0] == x[1] + 1) {
                        y[1] = v2.get(0)[1];
                        v2.removeFirst();
                        result =  true;
                    }
                }
                if (index == n) {
                    if (v2.peekLast()[1] + 1 == y[0]) {
                        y[0] = v2.peekLast()[0];
                        v2.removeLast();
                        result = true;
                    }
                }

                if (0 < index && index < n) {
                    int[] prev = v2.get(index - 1);
                    int[] next = v2.get(index);
                    if (prev[1] + 1 == x[0]) {
                        y[0] = prev[0];
                        v2.remove(prev);
                        result = true;
                    }
                    if (next[0] == x[1] + 1) {
                        y[1] = next[1];
                        v2.remove(next);
                        result = true;
                    }
                }
                if (result) {
                    merged.add(y);
                }
                return result;
            });
            //print(merged);
            return getLongest(merged);
        }

        private int[] getOrNull(Iterator<int[]> list) {
            if(!list.hasNext()) {
                return null;
            } else {
                return list.next();
            }
        }

        private int getLongest(LinkedList<int[]> merged) {
            //print(merged);
            int max = 0;
            int[] u = null;
            while(!merged.isEmpty()) {
                if (u == null) {
                    u = merged.removeFirst();
                } else {
                    var w = merged.peekFirst();
                    if (u[1] + 1 == w[0]) {
                        u = new int[] {u[0], w[1]};
                        max = Math.max(max, u[1] - u[0] + 1);
                    } else {
                        u = w;
                    }
                    merged.removeFirst();
                }
            }
            if (u != null) {
                max = Math.max(max, u[1] - u[0] + 1);
            }
            return max;
        }

        private int getLongest(TreeMap<Character, LinkedList<int[]>> graph) {
            int max = 0;
            for (char c : graph.keySet()) {
                max = Math.max(max, getLongest(graph.get(c)));
            }
            return max;
        }
    }
}
