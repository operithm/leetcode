package leetcode.medium;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 Success
 Details
 Runtime: 3 ms, faster than 43.54% of Java online submissions for Insert Interval.
 Memory Usage: 44.3 MB, less than 99.02% of Java online submissions for Insert Interval.
 Next challenges:
 Merge Intervals
 Range Module
 Count Integers in Intervals

 *
 * 57. Insert Interval
 * Medium
 *
 * 5793
 *
 * 395
 *
 * Add to List
 *
 * Share
 * You are given an array of non-overlapping intervals intervals where intervals[i] = [starti, endi] represent the start and the end of the ith interval and intervals is sorted in ascending order by starti. You are also given an interval newInterval = [start, end] that represents the start and end of another interval.
 *
 * Insert newInterval into intervals such that intervals is still sorted in ascending order by starti and intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).
 *
 * Return intervals after the insertion.
 *
 *
 *
 * Example 1:
 *
 * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * Output: [[1,5],[6,9]]
 * Example 2:
 *
 * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * Output: [[1,2],[3,10],[12,16]]
 * Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
 *
 */
public class _57_ITV_InsertInterval {

    class Solution {
        public int[][] insert(int[][] intervals, int[] newInterval) {
            if (newInterval == null || newInterval.length < 1) {
                return intervals;
            }
            if (intervals == null || intervals.length < 1) {
                return new int[][]{newInterval};
            }

            List<int[]> list = merge(intervals, newInterval);
            return list.toArray(new int[0][0]);

        }

        private List<int[]> merge(int[][] intervals, int[] interval) {
            final Comparator<int[]> comp = Comparator.comparing((arr) -> arr[0]);
            int i = Arrays.binarySearch(intervals, new int[]{interval[0]}, comp);
            int j = Arrays.binarySearch(intervals, new int[]{interval[1]}, comp);

            System.out.printf("i: %s, j: %s\n", i, j);
            int len = intervals.length;
            List<int[]> list = new LinkedList<int[]>();

            int[] merged = new int[2];

            /**
             * 1. Start value found in one interval
             1.a. Add all previous intervals before this found one, then merge start from this index
             2. Start value not found in any interval
             2.a. If the start value larger (>) than its preceeding interval (out of the range), then all all previous intervals as 1.a
             2.b. If this is the last interval, than set the merged to this one
             2.c. Else, compare the
             */
            if (i >= 0) {
                merged[0] = intervals[i][0];
                merge(list, 0, i, intervals);
            } else {
                i = negateIndexOrSelf(i);
                if (intervals[Math.max(i-1, 0)][1] < interval[0]) {
                    merge(list, 0, i, intervals);
                    if (i == len) {
                        merged = interval;
                    } else {
                        merged[0] = Math.min(intervals[i][0], interval[0]);
                    }
                } else {
                    merge(list, 0, i - 1, intervals);
                    merged[0] = Math.min(intervals[Math.max(i-1, 0)][0], interval[0]);
                }
            }

            list.add(merged);

            if (j >= 0) {
                merged[1] = intervals[j][1];
                merge(list, j + 1, len, intervals);
            } else {
                j =  negateIndexOrSelf(j);
                merged[1] = (j <= len && j >= 1) ? Math.max(intervals[j-1][1], interval[1]) : interval[1];
                merge(list, j, len, intervals);
            }

            return list;
        }

        /**
         *  i, j >= 0 && i, j < intervals.length
         *
         */
        private void merge(List<int[]> output, int i, int j, int[][] intervals) {
            for (int k = i; k < j && k < intervals.length; k++) {
                output.add(intervals[k]);
            }
        }

        /**
         * normalize the negative position, with the same as positive
         */
        private int negateIndexOrSelf(int i) {
            if (i < 0) {
                return -i - 1;
            }
            return i;
        }

    }

}
