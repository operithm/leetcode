package leetcode.easy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Success
 * Details
 * Runtime: 253 ms, faster than 5.01% of Java online submissions for Meeting Rooms.
 * Memory Usage: 54.9 MB, less than 5.03% of Java online submissions for Meeting Rooms.
 * Next challenges:
 * Merge Intervals
 * Meeting Rooms III
 *
 * 252. Meeting Rooms
 * Easy
 *
 * 1624
 *
 * 79
 *
 * Add to List
 *
 * Share
 * Given an array of meeting time intervals where intervals[i] = [starti, endi], determine if a person could attend all meetings.
 *
 *
 *
 * Example 1:
 *
 * Input: intervals = [[0,30],[5,10],[15,20]]
 * Output: false
 * Example 2:
 *
 * Input: intervals = [[7,10],[2,4]]
 * Output: true
 *
 *
 * Constraints:
 *
 * 0 <= intervals.length <= 104
 * intervals[i].length == 2
 * 0 <= starti < endi <= 106
 */
public class _252_ITV_MeetingRooms {

    class Solution {
        public boolean canAttendMeetings(int[][] intervals) {
            if(intervals == null || intervals.length <= 1) {
                return true;
            }
            Arrays.sort(intervals, Comparator.comparing(a -> a[0]));

            int[] a = intervals[0];
            for (int i = 1; i < intervals.length; i++) {
                int[] b = intervals[i];
                if (interact(a,b)) {
                    return false;
                }
                a = b;
            }

            return true;
        }

        private boolean interact(int[] a, int[]b) {
            boolean result = !(b[0] >= a[1] || a[0] >= b[1]);
            System.out.printf("a: %s, b: %s, %s\n", Arrays.toString(a), Arrays.toString(b), result);
            return result;
        }
    }
}
