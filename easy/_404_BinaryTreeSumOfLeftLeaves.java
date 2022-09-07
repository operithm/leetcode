package leetcode.easy;

import leetcode.TreeNode;

import java.util.Stack;

/**
 * 404. Sum of Left Leaves
 * Easy
 *
 * 3349
 *
 * 247
 *
 * Add to List
 *
 * Share
 * Given the root of a binary tree, return the sum of all left leaves.
 *
 * A leaf is a node with no children. A left leaf is a leaf that is the left child of another node.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [3,9,20,null,null,15,7]
 * Output: 24
 * Explanation: There are two left leaves in the binary tree, with values 9 and 15 respectively.
 * Example 2:
 *
 * Input: root = [1]
 * Output: 0
 *
 * Success
 * Details
 * Runtime: 2 ms, faster than 5.16% of Java online submissions for Sum of Left Leaves.
 * Memory Usage: 42.1 MB, less than 32.48% of Java online submissions for Sum of Left Leaves.
 * Next challenges:
 * Symmetric Tree
 * Binary Search Tree Iterator
 * Maximum Difference Between Node and Ancestor
 */
public class _404_BinaryTreeSumOfLeftLeaves {

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
    class Solution {
        public int sumOfLeftLeaves(TreeNode root) {
            if (root == null) {
                return -1;
            }

            Stack<TreeNode> lefts = new Stack<>();
            TreeNode current = root;
            int sum = 0;
            while (!lefts.isEmpty() || current != null) {
                if (current != null) {
                    if (current.left != null) {
                        lefts.push(current.left);
                    }
                    current = current.right;
                } else {
                    current = lefts.pop();
                    if (current.left == null && current.right == null) {
                        sum += current.val;
                    }
                }
            }

            return sum;

        }
    }
}
