package leetcode.hard;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 Success
 Details
 Runtime: 283 ms, faster than 5.18% of Java online submissions for Longest Increasing Path in a Matrix.
 Memory Usage: 49.5 MB, less than 86.11% of Java online submissions for Longest Increasing Path in a Matrix.
 Next challenges:
 Number of Increasing Paths in a Grid

 329. Longest Increasing Path in a Matrix
 Hard

 7142

 109

 Add to List

 Share
 Given an m x n integers matrix, return the length of the longest increasing path in matrix.

 From each cell, you can either move in four directions: left, right, up, or down. You may not move diagonally or move outside the boundary (i.e., wrap-around is not allowed).



 Example 1:


 Input: matrix = [[9,9,4],[6,6,8],[2,1,1]]
 Output: 4
 Explanation: The longest increasing path is [1, 2, 6, 9].
 Example 2:


 Input: matrix = [[3,4,5],[3,2,6],[2,2,1]]
 Output: 4
 Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
 Example 3:

 Input: matrix = [[1]]
 Output: 1


 Constraints:

 m == matrix.length
 n == matrix[i].length
 1 <= m, n <= 200
 0 <= matrix[i][j] <= 231 - 1

 */
public class _329_BFS_LongestIncreasingPathInMatrix {

    class Solution {
        public int longestIncreasingPath(int[][] matrix) {
            if (matrix == null || matrix.length < 1) {
                return 0;
            }

            List<Integer>[] graph = getGraph(matrix);

            return BFS(graph);
        }

        private int BFS(List<Integer>[] graph) {
            Queue<List<Integer>> queue = new LinkedList<>();
            int[] dist = new int[graph.length];

            //add the first list
            var list = new LinkedList<Integer>();
            for (int i = 0; i < graph.length; i++) {
                if (!graph[i].isEmpty()) {
                    list.add(i);
                    dist[i] = 1;
                }
            }

            if (list.isEmpty()) {
                return 1;
            }

            queue.offer(list);
            while(!queue.isEmpty()) {
                var v = queue.poll();
                var next = new LinkedList<Integer>();

                v.stream().forEach( i -> {
                    var u = graph[i];
                    u.stream().forEach(j -> {
                        if (dist[j] < dist[i] + 1) {
                            dist[j] = dist[i] + 1;
                            next.offer(j);
                        }
                    });
                });

                if (!next.isEmpty()) {
                    queue.offer(next);
                }
            }
            return Arrays.stream(dist).max().getAsInt();
        }

        /**
         * Convert a matrix to an adjacent list graph
         * @param matrix
         * @return
         */
        private List<Integer>[] getGraph(int[][] matrix) {
            int m = matrix.length;
            int n = matrix[0].length;
            int k = m * n;

            List<Integer>[] graph = new LinkedList[k];
            for (int i = 0; i < k; i++) {
                graph[i] = new LinkedList<>();
            }

            int[] x = new int[] {0, -1, +1, 0};
            int[] y = new int[] {-1, 0, 0, +1};

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    int[] p = new int[] {i, j};
                    for (int z = 0; z < 4; z++) {
                        int[] q = new int[] {i+x[z], j+y[z]};
                        //System.out.printf("p: %s, q: %s\n", Arrays.toString(p), Arrays.toString(q));
                        Integer v = getOrNull(q, matrix, m, n);
                        if (v != null && matrix[i][j] < v) {
                            graph[getIndex(p, m, n)].add(getIndex(q, m, n));
                        }
                    }
                }
            }

            System.out.printf("graph: %s\n", Arrays.toString(graph));
            return graph;
        }

        private Integer getOrNull(int[] pos, int[][] matrix, int m, int n) {
            if (pos[0] >= 0 && pos[0] < m && pos[1] >= 0 && pos[1] < n) {
                return matrix[pos[0]][pos[1]];
            }
            return null;
        }
        private int getIndex(int[] pos, int m, int n) {
            int result = pos[0] * n + pos[1];
            //System.out.printf("pos: %s, index: %s\n", Arrays.toString(pos), result);
            return result;
        }
    }

}
