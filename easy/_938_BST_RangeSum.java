package leetcode.easy;

import leetcode.TreeNode;

import java.util.Stack;

/**
 *
 * 938. Range Sum of BST
 * Easy
 *
 * 4191
 *
 * 332
 *
 * Add to List
 *
 * Share
 * Given the root node of a binary search tree and two integers low and high, return the sum of values of all nodes with a value in the inclusive range [low, high].
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [10,5,15,3,7,null,18], low = 7, high = 15
 * Output: 32
 * Explanation: Nodes 7, 10, and 15 are in the range [7, 15]. 7 + 10 + 15 = 32.
 * Example 2:
 *
 *
 * Input: root = [10,5,15,3,7,13,18,1,null,6], low = 6, high = 10
 * Output: 23
 * Explanation: Nodes 6, 7, and 10 are in the range [6, 10]. 6 + 7 + 10 = 23.
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 2 * 104].
 * 1 <= Node.val <= 105
 * 1 <= low <= high <= 105
 * All Node.val are unique.
 *
 * Success
 * Details
 * Runtime: 44 ms, faster than 5.18% of Java online submissions for Range Sum of BST.
 * Memory Usage: 65.9 MB, less than 54.52% of Java online submissions for Range Sum of BST.
 */
public class _938_BST_RangeSum {

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
        public int rangeSumBST(TreeNode root, int low, int high) {
            if (root == null) {
                return 0;
            }

            int sum = 0;
            Stack<TreeNode> stack = new Stack<>();
            TreeNode node = root;

            while(!stack.isEmpty() || node != null) {
                if (node != null) {
                    stack.push(node);
                    node = node.left;
                } else {
                    node = stack.pop();
                    if (inRange(node, low, high))  {
                        sum+= node.val;
                    }
                    node = node.right;
                }
            }
            return sum;
        }

        private boolean inRange(TreeNode node, int low, int high) {
            return node.val >= low && node.val <= high;
        }

    }
}
