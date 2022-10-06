package leetcode.medium;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Success
 * Details
 * Runtime: 643 ms, faster than 5.09% of Java online submissions for Minimum Time Difference.
 * Memory Usage: 61.4 MB, less than 5.23% of Java online submissions for Minimum Time Difference.
 * Next challenges:
 * Best Meeting Point
 * Number of Good Ways to Split a String
 *
 * 539. Minimum Time Difference
 * Medium
 *
 * 1292
 *
 * 229
 *
 * Add to List
 *
 * Share
 * Given a list of 24-hour clock time points in "HH:MM" format, return the minimum minutes difference between any two time-points in the list.
 *
 *
 * Example 1:
 *
 * Input: timePoints = ["23:59","00:00"]
 * Output: 1
 * Example 2:
 *
 * Input: timePoints = ["00:00","23:59","00:00"]
 * Output: 0
 *
 *
 * Constraints:
 *
 * 2 <= timePoints.length <= 2 * 104
 * timePoints[i] is in the format "HH:MM".
 */
public class _539_Sort_MinimumTimeDifference {
    static class TimePoint {
        private final int hour;
        private final int minute;
        public TimePoint(String timePoint) {
            var str = timePoint.split(":");
            this.hour = Integer.parseInt(str[0]);
            this.minute = Integer.parseInt(str[1]);
        }
        public int getHour() {
            return this.hour;
        }
        public int getMinute() {
            return this.minute;
        }
        public int getDiffAsMinutes(TimePoint another) {
            int diff = Math.abs(this.hour * 60 + this.minute - another.hour * 60 - another.minute);
            return Math.min(diff, 24 * 60 - diff);
        }

        @Override
        public String toString() {
            return String.format("%02d:%02d", hour, minute);
        }
    }

    class Solution {
        public int findMinDifference(List<String> timePoints) {
            if (timePoints == null || timePoints.size() == 0) {
                return 0;
            }

            var list = new LinkedList<TimePoint>();
            list.addAll(timePoints.stream()
                    .map(str -> new TimePoint(str))
                    .collect(Collectors.toList()));
            Collections.sort(list, Comparator.comparing(TimePoint::getHour).thenComparing(TimePoint::getMinute));
            var head = list.peekFirst();

            int minimum = Integer.MAX_VALUE;
            TimePoint current = null;

            while(!list.isEmpty()) {
                if (current == null) {
                    current = list.remove(0);
                } else {
                    int diff = current.getDiffAsMinutes(list.peekFirst());
                    if (diff < minimum) {
                        minimum = diff;
                    }
                    current = list.remove(0);
                }
            }
            int diff_ht = head.getDiffAsMinutes(current);
            //System.out.printf("head: %s, current: %s, min: %s, ht: %s\n", head, current, minimum, diff_ht);
            return Math.min(minimum, diff_ht);
        }
    }
}
