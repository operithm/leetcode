package leetcode.medium;

import leetcode.TreeNode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 *
 * 173. Binary Search Tree Iterator
 * Medium
 *
 * 6052
 *
 * 398
 *
 * Add to List
 *
 * Share
 * Implement the BSTIterator class that represents an iterator over the in-order traversal of a binary search tree (BST):
 *
 * BSTIterator(TreeNode root) Initializes an object of the BSTIterator class. The root of the BST is given as part of the constructor. The pointer should be initialized to a non-existent number smaller than any element in the BST.
 * boolean hasNext() Returns true if there exists a number in the traversal to the right of the pointer, otherwise returns false.
 * int next() Moves the pointer to the right, then returns the number at the pointer.
 * Notice that by initializing the pointer to a non-existent smallest number, the first call to next() will return the smallest element in the BST.
 *
 * You may assume that next() calls will always be valid. That is, there will be at least a next number in the in-order traversal when next() is called.
 *
 *
 *
 * Example 1:
 *
 *
 * Input
 * ["BSTIterator", "next", "next", "hasNext", "next", "hasNext", "next", "hasNext", "next", "hasNext"]
 * [[[7, 3, 15, null, null, 9, 20]], [], [], [], [], [], [], [], [], []]
 * Output
 * [null, 3, 7, true, 9, true, 15, true, 20, false]
 *
 * Explanation
 * BSTIterator bSTIterator = new BSTIterator([7, 3, 15, null, null, 9, 20]);
 * bSTIterator.next();    // return 3
 * bSTIterator.next();    // return 7
 * bSTIterator.hasNext(); // return True
 * bSTIterator.next();    // return 9
 * bSTIterator.hasNext(); // return True
 * bSTIterator.next();    // return 15
 * bSTIterator.hasNext(); // return True
 * bSTIterator.next();    // return 20
 * bSTIterator.hasNext(); // return False
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 105].
 * 0 <= Node.val <= 106
 * At most 105 calls will be made to hasNext, and next.
 *
 *
 * Follow up:
 *
 * Could you implement next() and hasNext() to run in average O(1) time and use O(h) memory, where h is the height of the tree?
 *
 * Success
 * Details
 * Runtime: 17 ms, faster than 80.22% of Java online submissions for Binary Search Tree Iterator.
 * Memory Usage: 46.4 MB, less than 83.42% of Java online submissions for Binary Search Tree Iterator.
 * Next challenges:
 * Binary Tree Inorder Traversal
 * Flatten 2D Vector
 * Zigzag Iterator
 * Peeking Iterator
 * Inorder Successor in BST
 * Binary Search Tree Iterator II
 * Show off your acceptance:
 */
public class _173_BinaryTreeIterator {

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode() {}
     *     TreeNode(int val) { this.val = val; }
     *     TreeNode(int val, TreeNode left, TreeNode right) {
     *         this.val = val;
     *         this.left = left;
     *         this.right = right;
     *     }
     * }
     */
    class BSTIterator {

        private final List<Integer> list;
        private final Iterator<Integer> iterator;

        public BSTIterator(TreeNode root) {
            this.list = inOrder(root);
            this.iterator = list.iterator();
        }

        public int next() {
            if (iterator.hasNext()) {
                return iterator.next();
            } else {
                throw new RuntimeException("End of iterator reached.");
            }
        }

        public boolean hasNext() {
            return iterator.hasNext();
        }

        private List<Integer> inOrder(TreeNode root) {
            final List<Integer> list = new ArrayList<>();
            TreeNode current = root;
            Stack<TreeNode> stack = new Stack<>();

            while(!stack.isEmpty() || current != null) {
                if(current != null) {
                    stack.push(current);
                    current = current.left;
                } else {
                    current = stack.pop();
                    list.add(current.val);
                    current = current.right;
                }
            }

            return list;
        }
    }

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
}
