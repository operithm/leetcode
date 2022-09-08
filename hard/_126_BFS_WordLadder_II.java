package leetcode.hard;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Success
 * Details
 * Runtime: 54 ms, faster than 14.86% of Java online submissions for Word Ladder II.
 * Memory Usage: 47.4 MB, less than 59.41% of Java online submissions for Word Ladder II.
 * Next challenges:
 * Word Ladder
 * Groups of Strings
 *
 * 126. Word Ladder II
 * Hard
 *
 * 4695
 *
 * 602
 *
 * Add to List
 *
 * Share
 * A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:
 *
 * Every adjacent pair of words differs by a single letter.
 * Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
 * sk == endWord
 * Given two words, beginWord and endWord, and a dictionary wordList, return all the shortest transformation sequences from beginWord to endWord, or an empty list if no such sequence exists. Each sequence should be returned as a list of the words [beginWord, s1, s2, ..., sk].
 *
 *
 *
 * Example 1:
 *
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * Output: [["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
 * Explanation: There are 2 shortest transformation sequences:
 * "hit" -> "hot" -> "dot" -> "dog" -> "cog"
 * "hit" -> "hot" -> "lot" -> "log" -> "cog"
 * Example 2:
 *
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
 * Output: []
 * Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.
 *
 *
 * Constraints:
 *
 * 1 <= beginWord.length <= 5
 * endWord.length == beginWord.length
 * 1 <= wordList.length <= 500
 * wordList[i].length == beginWord.length
 * beginWord, endWord, and wordList[i] consist of lowercase English letters.
 * beginWord != endWord
 * All the words in wordList are unique.
 * The sum of all shortest transformation sequences does not exceed 105.
 *
 */
public class _126_BFS_WordLadder_II {

    class Solution {
        public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
            if (wordList == null || wordList.isEmpty() || beginWord == null || endWord == null) {
                return new LinkedList<>();
            }

            String[] words = wordList.toArray(new String[0]);
            System.out.printf("pre-sort words: %s\n", Arrays.toString(words));
            Arrays.sort(words);
            System.out.printf("post-sort words: %s\n", Arrays.toString(words));

            int n = words.length;
            int v = Arrays.binarySearch(words, beginWord);
            int w = Arrays.binarySearch(words, endWord);
            if (w < 0) {
                return new LinkedList<>();
            }

            System.out.printf("v: %s, w: %s->%s\n", v, w, words[w]);
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

            final var  graph = makeGraph(vertices);
            v = 0;
            w = vertices.length - 1;
            System.out.printf("words: %s, vertices: %s\n", Arrays.toString(words), Arrays.toString(vertices));
            final var parent = getEmptyArray(vertices.length);
            BFS(graph, parent, w);

            final var paths = new LinkedList<List<String>>();
            final var path = new LinkedList<Integer>();
            path.add(w);
            getPaths(paths, path, parent, vertices, w);
            return paths;

        }

        private void getPaths(LinkedList<List<String>> paths, List<Integer> path, List<Integer>[] parent, String[] vertices, int u) {
            if (u == -1) {
                var pathStr = path.stream().filter(x -> x != -1).map(x -> vertices[x]).collect(Collectors.toList());
                paths.add(pathStr);
                return;
            }

            for (int v : parent[u]) {
                path.add(0, v);
                getPaths(paths, path, parent, vertices, v);
                path.remove(0);
            }

        }

        private void BFS(List<Integer>[] graph, List<Integer>[] parent, int w) {
            int n = graph.length;
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(0);

            int[] dist = new int[n];
            Arrays.fill(dist, Integer.MAX_VALUE);

            dist[0] = 0;
            parent[0] = new LinkedList<Integer>();
            parent[0].add(-1);


            while (!queue.isEmpty()) {
                int v = queue.poll();

                for (int u: graph[v]) {
                    if (dist[u] > dist[v] + 1) {
                        dist[u] = dist[v] + 1;
                        parent[u].clear();
                        parent[u].add(v);
                        queue.offer(u);
                    } else if (dist[u] == dist[v] + 1) {
                        parent[u].add(v);
                    }
                }

            }
        }

        private List<List<Integer>> DFS (List<Integer>[] graph, int w) {
            var stack = new Stack<Iterator<Integer>>();
            stack.push(graph[0].iterator());

            var output = new HashMap<Integer, List<List<Integer>>>();
            var visited = new LinkedList<Integer>();

            while(!stack.isEmpty()) {

                if (stack.peek().hasNext()) {
                    int v = (Integer) stack.peek().next();
                    /**
                     cycle check
                     */
                    if (!visited.contains(v)) {
                        visited.add(v);
                        if (v == w) {
                            int size = visited.size();
                            var outputList = output.getOrDefault(size, new LinkedList<>());
                            outputList.add(new LinkedList<Integer>(visited));
                            output.put(size, outputList);
                        }
                        var list = graph[v];
                        if (!list.isEmpty()) {
                            stack.push(list.iterator());
                        }
                    }

                } else {
                    stack.pop();
                    if (!visited.isEmpty()) {
                        visited.removeLast();
                    }
                }
            }

            java.util.Optional<Integer> key = output.keySet().stream().min(Integer::compare);
            return (key.isPresent()) ? output.get(key.get()) : new LinkedList<>();

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
