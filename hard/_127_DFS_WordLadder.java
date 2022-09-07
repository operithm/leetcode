package leetcode.hard;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Success
 * Details
 * Runtime: 2378 ms, faster than 5.01% of Java online submissions for Word Ladder.
 * Memory Usage: 121.4 MB, less than 5.07% of Java online submissions for Word Ladder.
 * Next challenges:
 * Minimum Genetic Mutation
 *
 * 127. Word Ladder
 * Hard
 *
 * 8950
 *
 * 1709
 *
 * Add to List
 *
 * Share
 * A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:
 *
 * Every adjacent pair of words differs by a single letter.
 * Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
 * sk == endWord
 * Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.
 *
 *
 *
 * Example 1:
 *
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * Output: 5
 * Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog", which is 5 words long.
 * Example 2:
 *
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
 * Output: 0
 * Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.
 *
 *
 * Constraints:
 *
 * 1 <= beginWord.length <= 10
 * endWord.length == beginWord.length
 * 1 <= wordList.length <= 5000
 * wordList[i].length == beginWord.length
 * beginWord, endWord, and wordList[i] consist of lowercase English letters.
 * beginWord != endWord
 * All the words in wordList are unique.
 *
 */
public class _127_DFS_WordLadder {
    class Solution {
        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            if (wordList == null || wordList.isEmpty() || beginWord == null || endWord == null) {
                return 0;
            }

            String[] words = wordList.toArray(new String[0]);
            //System.out.printf("pre-sort words: %s\n", Arrays.toString(words));
            Arrays.sort(words);
            //System.out.printf("post-sort words: %s\n", Arrays.toString(words));

            int n = words.length;
            int v = Arrays.binarySearch(words, beginWord);
            int w = Arrays.binarySearch(words, endWord);
            if (w < 0) {
                return 0;
            }

            //System.out.printf("v: %s, w: %s->%s\n", v, w, words[w]);
            /**
             * always swap
             */
            if (words[n-1].equals(beginWord)) {
                swap(words, n - 1, 0);
            }
            if (!words[n-1].equals(endWord)) {
                swap(words, n - 1, w);
            }
            if (v >= 0 && !words[0].equals(beginWord)) {
                swap(words, 0, v);
            }

            String[] vertices = words;

            if(v < 0) {
                vertices = new String[n+1];
                System.arraycopy(words, 0, vertices, 1, n);
                vertices[0] = beginWord;
            }

            final var graph = makeGraph(vertices);
            v = 0;
            w = vertices.length - 1;
            //System.out.printf("words: %s, vertices: %s\n", Arrays.toString(words), Arrays.toString(vertices));
            int min = DFS(graph, w);

            return min != Integer.MAX_VALUE ? min + 1 : 0;

        }

        private int DFS (List<Integer>[] graph, int w) {
            int n = graph.length;
            var stack = new Stack<Integer>();
            stack.push(0);
            int[] dist = new int[n];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[0] = 0;

            while(!stack.isEmpty()) {
                int v = stack.pop();
                for (int u : graph[v]) {
                    if (dist[u] > dist[v] + 1) {
                        dist[u] = dist[v] + 1;
                        if (u != w) {
                            stack.push(u);
                        }
                    }
                }
            }
            return dist[w];
        }

        private List<Integer>[] makeGraph(String[] words) {
            int n = words.length;
            LinkedList<Integer>[] graph = getEmptyArray(n);

            for (int i = 0; i < n - 1; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (connected(words[i], words[j])) {
                        //System.out.printf("connected: %s: %s - %s: %s\n", i, words[i], j, words[j]);
                        graph[i].addLast(j);
                        graph[j].addLast(i);
                    }
                }
            }
            System.out.printf("graph: %s\n", Arrays.toString(graph));
            return graph;
        }

        private LinkedList<Integer>[] getEmptyArray(int n) {
            LinkedList<Integer>[] array = new LinkedList[n];
            for (int k = 0; k < n; k++) {
                array[k] = new LinkedList<>();
            }
            return array;
        }

        /**
         *
         */
        private boolean connected(String a, String b) {
            int count = 0;
            if (a.length() != b.length()) {
                return false;
            }

            int len = a.length();
            for (int i = 0; i < len; i++) {
                if (a.charAt(i) - b.charAt(i) != 0) {
                    count++;
                }
            }
            return count == 1;
        }

        private void swap(String[] words, int i, int j) {
            String tmp = words[j];
            words[j] = words[i];
            words[i] = tmp;
        }
    }

}
