package leetcode.medium;

import java.util.Stack;

/**
 * 1762. Buildings With an Ocean View
 * Medium
 *
 * 853
 *
 * 114
 *
 * Add to List
 *
 * Share
 * There are n buildings in a line. You are given an integer array heights of size n that represents the heights of the buildings in the line.
 *
 * The ocean is to the right of the buildings. A building has an ocean view if the building can see the ocean without obstructions. Formally, a building has an ocean view if all the buildings to its right have a smaller height.
 *
 * Return a list of indices (0-indexed) of buildings that have an ocean view, sorted in increasing order.
 *
 *
 *
 * Example 1:
 *
 * Input: heights = [4,2,3,1]
 * Output: [0,2,3]
 * Explanation: Building 1 (0-indexed) does not have an ocean view because building 2 is taller.
 * Example 2:
 *
 * Input: heights = [4,3,2,1]
 * Output: [0,1,2,3]
 * Explanation: All the buildings have an ocean view.
 * Example 3:
 *
 * Input: heights = [1,3,2,4]
 * Output: [3]
 * Explanation: Only building 3 has an ocean view.
 *
 *
 * Constraints:
 *
 * 1 <= heights.length <= 105
 * 1 <= heights[i] <= 109
 * Accepted
 * 118,385
 * Submissions
 * 148,755
 *
 * Success
 * Details
 * Runtime: 19 ms, faster than 42.68% of Java online submissions for Buildings With an Ocean View.
 * Memory Usage: 89 MB, less than 16.82% of Java online submissions for Buildings With an Ocean View.
 * Next challenges:
 * Number of Visible People in a Queue
 */
public class _1762_BuildingsWithAnOceanView {

    class Solution {
        public int[] findBuildings(int[] heights) {
            if (heights == null || heights.length < 1) {
                return new int[0];
            }

            int len = heights.length;
            int x = Integer.MIN_VALUE;
            Stack<Integer> stack = new Stack<>();

            for (int i = len - 1; i >= 0; i--) {
                if (heights[i] > x) {
                    x = heights[i];
                    stack.push(i);
                }
            }

            int[] result = new int[stack.size()];
            int i = 0;
            while (!stack.isEmpty()) {
                result[i++] = stack.pop();
            }

            return result;
        }
    }
}
