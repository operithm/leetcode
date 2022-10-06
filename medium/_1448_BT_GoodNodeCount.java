package leetcode.medium;

import leetcode.TreeNode;

import java.util.Stack;

/**
 * 1448. Count Good Nodes in Binary Tree
 * Medium
 *
 * 2427
 *
 * 79
 *
 * Add to List
 *
 * Share
 * Given a binary tree root, a node X in the tree is named good if in the path from root to X there are no nodes with a value greater than X.
 *
 * Return the number of good nodes in the binary tree.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: root = [3,1,4,3,null,1,5]
 * Output: 4
 * Explanation: Nodes in blue are good.
 * Root Node (3) is always a good node.
 * Node 4 -> (3,4) is the maximum value in the path starting from the root.
 * Node 5 -> (3,4,5) is the maximum value in the path
 * Node 3 -> (3,1,3) is the maximum value in the path.
 * Example 2:
 *
 *
 *
 * Input: root = [3,3,null,4,2]
 * Output: 3
 * Explanation: Node 2 -> (3, 3, 2) is not good, because "3" is higher than it.
 * Example 3:
 *
 * Input: root = [1]
 * Output: 1
 * Explanation: Root is considered as good.
 *
 *
 * Constraints:
 *
 * The number of nodes in the binary tree is in the range [1, 10^5].
 * Each node's value is between [-10^4, 10^4].
 *
 * Success
 * Details
 * Runtime: 25 ms, faster than 5.08% of Java online submissions for Count Good Nodes in Binary Tree.
 * Memory Usage: 51.7 MB, less than 72.53% of Java online submissions for Count Good Nodes in Binary Tree.
 * Next challenges:
 * Binary Tree Coloring Game
 * Find a Corresponding Node of a Binary Tree in a Clone of That Tree
 * Closest Node to Path in Tree
 */
public class _1448_BT_GoodNodeCount {
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
    /**
     * Helper class as a struct
     */
    private static class MaxTreeNode {
        private final TreeNode node;
        private final int max;
        public MaxTreeNode(TreeNode node, int max) {
            this.node = node;
            this.max = max;
        }
    }

    private static class Solution {

        public int goodNodes(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int sum = 0;
            Stack<MaxTreeNode> stack = new Stack<>();
            MaxTreeNode current = new MaxTreeNode(root, root.val);

            while (!stack.isEmpty() || current.node != null) {
                if (current.node != null) {
                    stack.push(current);
                    current = new MaxTreeNode(current.node.left, Math.max(current.node.val, current.max));
                } else {
                    current = stack.pop();
                    if (current.node.val >= current.max) {
                        sum++;
                    }
                    current = new MaxTreeNode(current.node.right, Math.max(current.node.val, current.max));
                }
            }

            return sum;
        }
    }



}
