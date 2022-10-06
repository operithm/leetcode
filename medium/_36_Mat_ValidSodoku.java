package leetcode.medium;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Success
 * Details
 * Runtime: 250 ms, faster than 5.60% of Java online submissions for Valid Sudoku.
 * Memory Usage: 55.2 MB, less than 5.14% of Java online submissions for Valid Sudoku.
 * Next challenges:
 * Sudoku Solver
 * Check if Every Row and Column Contains All Numbers
 *
 * 36. Valid Sudoku
 * Medium
 *
 * 6469
 *
 * 796
 *
 * Add to List
 *
 * Share
 * Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
 *
 * Each row must contain the digits 1-9 without repetition.
 * Each column must contain the digits 1-9 without repetition.
 * Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
 * Note:
 *
 * A Sudoku board (partially filled) could be valid but is not necessarily solvable.
 * Only the filled cells need to be validated according to the mentioned rules.
 *
 *
 * Example 1:
 *
 *
 * Input: board =
 * [["5","3",".",".","7",".",".",".","."]
 * ,["6",".",".","1","9","5",".",".","."]
 * ,[".","9","8",".",".",".",".","6","."]
 * ,["8",".",".",".","6",".",".",".","3"]
 * ,["4",".",".","8",".","3",".",".","1"]
 * ,["7",".",".",".","2",".",".",".","6"]
 * ,[".","6",".",".",".",".","2","8","."]
 * ,[".",".",".","4","1","9",".",".","5"]
 * ,[".",".",".",".","8",".",".","7","9"]]
 * Output: true
 * Example 2:
 *
 * Input: board =
 * [["8","3",".",".","7",".",".",".","."]
 * ,["6",".",".","1","9","5",".",".","."]
 * ,[".","9","8",".",".",".",".","6","."]
 * ,["8",".",".",".","6",".",".",".","3"]
 * ,["4",".",".","8",".","3",".",".","1"]
 * ,["7",".",".",".","2",".",".",".","6"]
 * ,[".","6",".",".",".",".","2","8","."]
 * ,[".",".",".","4","1","9",".",".","5"]
 * ,[".",".",".",".","8",".",".","7","9"]]
 * Output: false
 * Explanation: Same as Example 1, except with the 5 in the top left corner being modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.
 *
 *
 * Constraints:
 *
 * board.length == 9
 * board[i].length == 9
 * board[i][j] is a digit 1-9 or '.'.
 *
 */
public class _36_Mat_ValidSodoku {

    class Solution {
        public boolean isValidSudoku(char[][] board) {
            if (board == null || board.length != 9 || board[0].length != 9) {
                return false;
            }
            var map = new TreeMap<String, Set<Character>>();
            for (int i = 0 ; i < 9; i++) {
                for (int j = 0; j < 9; j ++) {
                    char a =  board[i][j];
                    if (a != '.') {
                        var rowSet = map.getOrDefault(getSetKey(i, -1), new TreeSet<Character>());
                        var colSet = map.getOrDefault(getSetKey(-1, j), new TreeSet<Character>());
                        String boxStr = getSetKey(i/3, j/3);
                        var box = map.getOrDefault(boxStr, new TreeSet<Character>());
                        System.out.printf("row: %s\tcol: %s\tbox: %s\n",
                                String.valueOf(rowSet), String.valueOf(colSet), String.valueOf(map.get(boxStr)));
                        if (rowSet.contains(a) || colSet.contains(a) || box.contains(a)) {
                            return false;
                        }
                        rowSet.add(a);
                        colSet.add(a);
                        box.add(a);
                        map.put(getSetKey(i, -1), rowSet);
                        map.put(getSetKey(-1, j), colSet);
                        map.put(boxStr, box);
                    }
                }
            }
            return true;
        }
    }

    private String getSetKey(int x, int y) {
        return Arrays.toString(new int[] {x, y});
    }
}
