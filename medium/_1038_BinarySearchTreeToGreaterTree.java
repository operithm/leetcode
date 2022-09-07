package leetcode.medium;

import leetcode.TreeNode;

import java.util.Stack;

/**
 * 1038. Binary Search Tree to Greater Sum Tree
 * Medium
 *
 * 2601
 *
 * 134
 *
 * Add to List
 *
 * Share
 * Given the root of a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to the original key plus the sum of all keys greater than the original key in BST.
 *
 * As a reminder, a binary search tree is a tree that satisfies these constraints:
 *
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 *
 * Success
 * Details
 * Runtime: 1 ms, faster than 8.42% of Java online submissions for Binary Search Tree to Greater Sum Tree.
 * Memory Usage: 41.6 MB, less than 39.64% of Java online submissions for Binary Search Tree to Greater Sum Tree.
 * Next challenges:
 * Convert Sorted List to Binary Search Tree
 * Populating Next Right Pointers in Each Node
 *
 */
public class _1038_BinarySearchTreeToGreaterTree {

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
    private static class Solution {
        public TreeNode bstToGst(TreeNode root) {
            if (root == null) {
                return root;
            }

            Stack<TreeNode> stack = new Stack<>();
            TreeNode current = root;
            TreeNode lastVisited = null;

            while (!stack.isEmpty() || current != null) {
                if (current != null) {
                    stack.push(current);
                    current = current.right;
                } else {
                    current = stack.pop();
                    if (lastVisited != null) {
                        current.val += lastVisited.val;
                    }
                    lastVisited = current;
                    current = current.left;
                }
            }

            return root;
        }

    }

}
