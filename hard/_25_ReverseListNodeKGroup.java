package leetcode.hard;

import leetcode.ListNode;

/**
 *25. Reverse Nodes in k-Group
 * Hard
 *
 * Given the head of a linked list, reverse the nodes of the list k at a time, and return the modified list.
 *
 * k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a
 * multiple of k then left-out nodes, in the end,
 * should remain as it is.
 *
 * You may not alter the values in the list's nodes, only nodes themselves may be changed.
 *
 * Example 1:
 *
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [2,1,4,3,5]
 * Example 2:
 *
 * Input: head = [1,2,3,4,5], k = 3
 * Output: [3,2,1,4,5]
 *
 *
 * Constraints:
 *
 * The number of nodes in the list is n.
 * 1 <= k <= n <= 5000
 * 0 <= Node.val <= 1000
 *
 *
 *Success
 * Details
 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Reverse Nodes in k-Group.
 * Memory Usage: 45.3 MB, less than 65.99% of Java online submissions for Reverse Nodes in k-Group
 */
public class _25_ReverseListNodeKGroup {

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
    private static class Solution {
        /**
         * A recursive callable method
         * @param head
         * @param k
         * @return
         */
        public ListNode reverseKGroup(ListNode head, int k) {
            /**
             * Check if is empty
             */
            if (k <= 1 || head == null) {
                return head;
            }

            int i = k;
            ListNode next = head;
            /**
             *  check if length >= k
             *  if length > k, reverse the first k, return a new head; also let the reversed list tail's next point to
             *  the next group's head for a recursion
             *
             */
            while (i > 0 && next != null) {
                i--;
                next = next.next;
            }

            ListNode result = null;
            if (i == 0) {
                result = reverseWindow(head, k);
                head.next = reverseKGroup(next, k);
            } else {
                result = head;
            }
            return result;
        }

        /**
         * Helper method to reverse a window.
         * @param head
         * @param k
         * @return
         */
        private ListNode reverseWindow(ListNode head, int k) {
            //three pointers, prev, current, and next
            ListNode current = head;
            ListNode prev = null;
            ListNode next = null;

            int i = 0;
            while (i < k && current != null) {
                next = current.next;
                current.next = prev;
                prev = current;
                current = next;
                i++;
            }
            return prev;
        }

    }
}
