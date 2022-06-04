package leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
 *
 * Merge all the linked-lists into one sorted linked-list and return it.
 *
 * Example 1:
 *
 * Input:   lists = [[1,4,5],[1,3,4],[2,6]]
 * Output:  [1,1,2,3,4,4,5,6]
 * Explanation: The linked-lists are:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * merging them into one sorted list:
 * 1->1->2->3->4->4->5->6
 * Example 2:
 *
 * Input: lists = []
 * Output: []
 * Example 3:
 *
 * Input: lists = [[]]
 * Output: []
 *
 *
 * Constraints:
 *
 * k == lists.length
 * 0 <= k <= 10^4
 * 0 <= lists[i].length <= 500
 * -10^4 <= lists[i][j] <= 10^4
 * lists[i] is sorted in ascending order.
 * The sum of lists[i].length won't exceed 10^4.
 *
 */
public class MergeKSortedLists {

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode() {}
     *     ListNode(int val) { this.val = val; }
     *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    public static final class Solution {

        public ListNode mergeKLists(ListNode[] lists) {
            ListNode result = null;
            ListNode tail = null;

            final PriorityQueue<ListNode> queue = new PriorityQueue<>(new ListNodeComparator());
            if (lists != null) {
                for (int i = 0; i < lists.length; i++) {
                    if (lists[i] != null) {
                        queue.add(lists[i]);
                    }
                }
            }

            while(!queue.isEmpty()) {
                ListNode header = queue.remove();
                ListNode next = new ListNode(header.val);

                if (result == null) {
                    result = next;
                    tail = next;
                } else {
                    tail.next = next;
                    tail = next;
                }

                if (header.next != null) {
                    header = header.next;
                    queue.add(header);
                }
            }
            return result;
        }
    }

    private static class ListNodeComparator implements Comparator<ListNode> {
        @Override
        public int compare(ListNode one, ListNode another) {
            if (one == null) {
                return -1;
            }
            if (another == null) {
                return 1;
            }
            return Integer.compare(one.val, another.val);
        }
    }

}
