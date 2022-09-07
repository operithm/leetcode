package leetcode.medium;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Success
 * Details
 * Runtime: 1145 ms, faster than 5.01% of Java online submissions for Word Search.
 * Memory Usage: 147.2 MB, less than 6.39% of Java online submissions for Word Search.
 * Next challenges:
 * Word Search II
 * Show off your acceptance:
 * 79. Word Search
 * Medium
 *
 * 10746
 *
 * 399
 *
 * Add to List
 *
 * Share
 * Given an m x n grid of characters board and a string word, return true if word exists in the grid.
 *
 * The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * Output: true
 * Example 2:
 *
 *
 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
 * Output: true
 * Example 3:
 *
 *
 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
 * Output: false
 *
 *
 * Constraints:
 *
 * m == board.length
 * n = board[i].length
 * 1 <= m, n <= 6
 * 1 <= word.length <= 15
 * board and word consists of only lowercase and uppercase English letters.
 *
 *
 * Follow up: Could you use search pruning to make your solution faster with a larger board?
 *
 */
public class _79_DFS_WordSearch {

    class Solution {
        public boolean exist(char[][] board, String word) {
            if (board == null) {
                return false;
            }

            int row = board.length;
            int col = board[0].length;

            var taken = new boolean[row][col];
            var initList = getPositionList(board, word.charAt(0));
            if (initList.isEmpty()) {
                return false;
            }

            return DFS(board, taken, initList, word);

        }

        /**
         *
         * @param board
         * @param taken
         * @param pos
         * @param word
         * @return
         */
        private boolean DFS (char[][] board, boolean[][] taken, List<int[]> pos, String word) {
            Stack<Iterator> stack = new Stack<>();
            stack.push(pos.iterator());

            int n = word.length();
            int m = 0;
            LinkedList<int[]> visited = new LinkedList<>();
            int[] curr = null;

            while(!stack.isEmpty() && m < n) {
                if (m == n - 1) {
                    return true;
                }
                char c = getNext(word, m + 1);

                if (stack.peek().hasNext()) {
                    curr = (int[]) stack.peek().next();
                    var list = getAdjacentList(board, taken, curr, c);
                    //System.out.printf("curr: %s, m: %s, list: %s\n", Arrays.toString(curr), m, list.size());
                    if (!list.isEmpty()) {
                        m++ ;
                        stack.push(list.iterator());
                        taken[curr[0]][curr[1]] = true;
                        visited.addLast(curr);
                    }
                } else {
                    stack.pop();
                    if (!visited.isEmpty()) {
                        curr = visited.removeLast();
                        taken[curr[0]][curr[1]] = false;
                    }
                    m --;
                }
            }
            return false;
        }

        /**
         *
         * @param word
         * @param pos
         * @return
         */
        private char getNext(String word, int pos) {
            if (word != null && pos >= 0 && pos < word.length()) {
                return word.charAt(pos);
            }
            throw new IllegalArgumentException(String.format("out of bound: %s, %s", pos, word));
        }

        /**
         *
         * @param board
         * @param c
         * @return
         */
        private List<int[]> getPositionList(char[][] board, char c) {
            var list = new LinkedList<int[]>();
            int row = board.length;
            int col = board[0].length;

            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j ++) {
                    if (board[i][j] == c) {
                        list.add(new int[]{i, j});
                    }
                }
            }
            return list;
        }

        /**
         *
         * @param board
         * @param taken
         * @param pos
         * @param c
         * @return
         */
        private List<int[]> getAdjacentList(char[][] board, boolean[][] taken, int[] pos, char c) {
            var list = new LinkedList<int[]>();
            int[] h = new int[]{-1, 0, 0, 1};
            int[] v = new int[]{0, -1, +1, 0};
            for (int i = 0; i < h.length; i++) {
                int x = h[i] + pos[0];
                int y = v[i] + pos[1];
                var next = new int[]{x, y};
                if (connected(board, taken, next, c)) {
                    list.add(next);
                }
            }
            return list;
        }

        /**
         *
         * @param board
         * @param taken
         * @param pos
         * @param c
         * @return
         */
        private boolean connected(char[][] board, boolean[][] taken, int[] pos, char c) {
            int x = pos[0];
            int y = pos[1];
            int row = board.length;
            int col = board[0].length;
            return (x >= 0 && x < row && y >= 0 && y < col && !taken[x][y] && board[x][y] == c);
        }

    }
}
