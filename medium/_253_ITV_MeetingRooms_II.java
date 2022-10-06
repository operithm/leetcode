package leetcode.medium;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * Success
 * Details
 * Runtime: 60 ms, faster than 5.22% of Java online submissions for Meeting Rooms II.
 * Memory Usage: 56.6 MB, less than 5.12% of Java online submissions for Meeting Rooms II.
 * Next challenges:
 *
 * Merge Intervals
 * Meeting Rooms
 * Minimum Number of Arrows to Burst Balloons
 * Car Pooling
 * Number of Flowers in Full Bloom
 * Meeting Rooms III

 253. Meeting Rooms II
 Medium

 5965

 125

 Add to List

 Share
 Given an array of meeting time intervals intervals where intervals[i] = [starti, endi], return the minimum number of conference rooms required.



 Example 1:

 Input: intervals = [[0,30],[5,10],[15,20]]
 Output: 2
 Example 2:

 Input: intervals = [[7,10],[2,4]]
 Output: 1


 Constraints:

 1 <= intervals.length <= 104
 0 <= starti < endi <= 106

 */
public class _253_ITV_MeetingRooms_II {

    static class Event {
        private final int id;
        private int in;
        private int out;

        public Event(int id) { this.id = id; }

        public int getId() { return this.id; }

        @Override
        public String toString() {
            return String.format("id: %s, in: %s, out: %s", id, in, out);
        }
    }

    class Solution {

        public int minMeetingRooms(int[][] intervals) {
            if (intervals == null || intervals.length < 1) {
                return 0;
            }

            //List<int[]> input = Arrays.stream(intervals).collect(Collectors.toList());
            //Collections.sort(input, ((a,b) -> Integer.compare(a[1], b[1])));
            final var map = new TreeMap<Integer, Event>();
            Arrays.stream(intervals).filter(x -> x[0] != x[1])
                    .forEach(x -> {
                        var a = map.getOrDefault(x[0], new Event(x[0]));
                        a.in ++;
                        map.put(x[0], a);
                        var b = map.getOrDefault(x[1], new Event(x[1]));
                        b.out ++;
                        map.put(x[1], b);
                    });

            PriorityQueue<Event> queue = new PriorityQueue<>(map.size(), Comparator.comparing(Event::getId));
            queue.addAll(map.values());

            int max = 0;
            int size = 0;

            while (!queue.isEmpty()) {
                var evt = queue.poll();
                //System.out.println(evt);
                if (size > max) {
                    max = size;
                }
                size -= evt.out;
                size += evt.in;
            }

            return max;
        }
    }

}
