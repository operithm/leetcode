package leetcode.medium;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Success
 * Details
 * Runtime: 2 ms, faster than 52.61% of Java online submissions for Populating Next Right Pointers in Each Node II.
 * Memory Usage: 45.1 MB, less than 43.20% of Java online submissions for Populating Next Right Pointers in Each Node II.
 * Next challenges:
 * Populating Next Right Pointers in Each Node
 *
 * 117. Populating Next Right Pointers in Each Node II
 * Medium
 *
 * 4778
 *
 * 273
 *
 * Add to List
 *
 * Share
 * Given a binary tree
 *
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
 *
 * Initially, all next pointers are set to NULL.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,2,3,4,5,null,7]
 * Output: [1,#,2,3,#,4,5,7,#]
 * Explanation: Given the above binary tree (Figure A), your function should populate each next pointer to point to its next right node, just like in Figure B. The serialized output is in level order as connected by the next pointers, with '#' signifying the end of each level.
 * Example 2:
 *
 * Input: root = []
 * Output: []
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 6000].
 * -100 <= Node.val <= 100
 *
 *
 * Follow-up:
 *
 * You may only use constant extra space.
 * The recursive approach is fine. You may assume implicit stack space does not count as extra space for this problem.
 *
 */
public class _117_BinaryTreePopulateNextRightPointersInEachNode_II_BFS {
//
// Definition for a Node.

    /**
     *
     */
    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    class Solution {
        public Node connect(Node root) {
            if (root != null) {
                DFS(root);
            }
            return root;
        }

        /**
         *
         * @param root
         */
        private void DFS(Node root) {

            Queue<Iterator> queue = new LinkedList<>();
            var init = new LinkedList<Node>();
            init.add(root);
            queue.offer(init.iterator());


            while (!queue.isEmpty()) {
                Iterator current = queue.poll();
                List<Node> children = new LinkedList<>();

                Node head = null;
                while (current.hasNext()) {
                    Node next = (Node) current.next();
                    next.next = null;
                    if (next.left != null) {
                        children.add(next.left);
                    }
                    if (next.right != null) {
                        children.add(next.right);
                    }

                    if (head != null) {
                        head.next = next;
                    }
                    head = next;
                    //System.out.printf("head: %s, head.next: %s\n", head.val, head.next == null ? null : head.next.val);
                }
                /**
                 * Iterator hasNext() == node != null
                 */
                if (!children.isEmpty()) {
                    queue.offer(children.iterator());
                }
            }
        }
    }
}
