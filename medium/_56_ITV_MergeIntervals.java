package leetcode.medium;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * Runtime 28 ms Beats 7.29% Memory 55.5 MB Beats 31.50%
 *
 * Next question
 *
 * 57. Insert Interval
 * More challenges
 * 495. Teemo Attacking
 * 616. Add Bold Tag in String
 * 715. Range Module
 *
 * 56. Merge Intervals (Medium)
 *
 * company
 * Facebook, Bloomberg, Google
 *
 * Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of
 * the non-overlapping intervals that cover all the intervals in the input.
 *
 *
 *
 * Example 1:
 *
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
 * Example 2:
 *
 * Input: intervals = [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 *
 *
 * Constraints:
 *
 * 1 <= intervals.length <= 104
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 104
 *
 */
public class _56_ITV_MergeIntervals {
    class Solution {
        public int[][] merge(int[][] intervals) {
            if (intervals == null || intervals.length < 1) {
                return new int[0][0];
            }

            var list = new LinkedList<int[]>();
            list.addAll(Arrays.stream(intervals).collect(Collectors.toList()));
            Collections.sort(list, (a, b) -> (a[0] - b[0]));

            var result = new LinkedList<int[]>();
            int[] current = null;
            while(!list.isEmpty()) {
                if (current == null) {
                    current = list.removeFirst();
                } else {
                    var peek = list.peekFirst();
                    if (canMerge(current, peek)) {
                        current = merge(current, peek);
                        list.removeFirst();
                    } else {
                        result.add(current);
                        current = list.removeFirst();
                    }
                }

                if (list.isEmpty()) {
                    result.add(current);
                }
            }

            return result.toArray(new int[0][0]);
        }

        private boolean canMerge(int[] a, int[] b) {
            sort(a, b);
            return (a[1] >= b[0]);
        }

        private int[] merge(int[] a, int[] b) {
            if (canMerge(a, b)) {
                return new int[] {a[0], Math.max(a[1], b[1])};
            }
            return a;
        }

        private void sort(int[] a, int[]b) {
            if (a[0] > b[0]) {
                var tmp = a;
                a = b;
                b = tmp;
            }
        }
    }
}
