package leetcode.easy;

import leetcode.TreeNode;

import java.util.Stack;

/**
 * 112. Path Sum
 * Easy
 *
 * 5545
 *
 * 790
 *
 * Add to List
 *
 * Share
 * Given the root of a binary tree and an integer targetSum, return true if the tree has a root-to-leaf path such that adding up all the values along the path equals targetSum.
 *
 * A leaf is a node with no children.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
 * Output: true
 * Explanation: The root-to-leaf path with the target sum is shown.
 * Example 2:
 *
 *
 * Input: root = [1,2,3], targetSum = 5
 * Output: false
 * Explanation: There two root-to-leaf paths in the tree:
 * (1 --> 2): The sum is 3.
 * (1 --> 3): The sum is 4.
 * There is no root-to-leaf path with sum = 5.
 * Example 3:
 *
 * Input: root = [], targetSum = 0
 * Output: false
 * Explanation: Since the tree is empty, there are no root-to-leaf paths.
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 5000].
 * -1000 <= Node.val <= 1000
 * -1000 <= targetSum <= 1000
 *
 * Success
 * Details
 * Runtime: 2 ms, faster than 9.84% of Java online submissions for Path Sum.
 * Memory Usage: 42.1 MB, less than 91.96% of Java online submissions for Path Sum.
 * Next challenges:
 * Path Sum II
 * Binary Tree Maximum Path Sum
 * Sum Root to Leaf Numbers
 * Path Sum III
 * Path Sum IV
 */
public class _112_BT_PathWithTargetSum {

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
        public boolean hasPathSum(TreeNode root, int targetSum) {
            if (root == null) {
                return false;
            }

            Stack<TreeNodeX> stack = new Stack<>();
            TreeNodeX current = new TreeNodeX(root, root.val);
            stack.push(current);
            boolean result = false;
            /**
             * Pre-order traversal
             */
            while(!stack.isEmpty()) {
                current = stack.pop();
                if (current.node.left == null && current.node.right == null) {
                    if (result |= (targetSum == current.sum)) {
                        return result;
                    }
                }
                if (current.node.right != null) {
                    stack.push(new TreeNodeX(current.node.right, current.node.right.val + current.sum));
                }
                if (current.node.left != null) {
                    stack.push(new TreeNodeX(current.node.left, current.node.left.val + current.sum));
                }
            }

            return result;
        }


    }

    private static class TreeNodeX {
        private final TreeNode node;
        private final int sum;
        public TreeNodeX(TreeNode node, int sum) {
            this.node = node;
            this.sum = sum;
        }
    }
}
