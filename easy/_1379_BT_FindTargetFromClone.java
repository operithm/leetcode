package leetcode.easy;

import leetcode.TreeNode;

import java.util.Stack;

/**
 * 1379. Find a Corresponding Node of a Binary Tree in a Clone of That Tree
 * Easy
 *
 * 1184
 *
 * 1493
 *
 * Add to List
 *
 * Share
 * Given two binary trees original and cloned and given a reference to a node target in the original tree.
 *
 * The cloned tree is a copy of the original tree.
 *
 * Return a reference to the same node in the cloned tree.
 *
 * Note that you are not allowed to change any of the two trees or the target node and the answer must be a reference to a node in the cloned tree.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: tree = [7,4,3,null,null,6,19], target = 3
 * Output: 3
 * Explanation: In all examples the original and cloned trees are shown. The target node is a green node from the original tree. The answer is the yellow node from the cloned tree.
 * Example 2:
 *
 *
 * Input: tree = [7], target =  7
 * Output: 7
 * Example 3:
 *
 *
 * Input: tree = [8,null,6,null,5,null,4,null,3,null,2,null,1], target = 4
 * Output: 4
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 104].
 * The values of the nodes of the tree are unique.
 * target node is a node from the original tree and is not null.
 *
 *
 * Follow up: Could you solve the problem if repeated values on the tree are allowed?
 *
 * Accepted
 * 151,075
 * Submissions
 * 172,677
 *
 * * Success
 *      * Details
 *      *
 *      * Runtime: 12 ms, faster than 10.02% of Java online submissions for Find a Corresponding Node of a Binary Tree in a Clone of That Tree.
 *      * Memory Usage: 105.3 MB, less than 22.08% of Java online submissions for Find a Corresponding Node of a Binary Tree in a Clone of That Tree.
 *      *
 *      * Next challenges:
 *      *
 *      * Lowest Common Ancestor of a Binary Search Tree
 *      * Maximum Level Sum of a Binary Tree
 *      * Count Nodes With the Highest Score
 */
public class _1379_BT_FindTargetFromClone {

    /**

     *
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */

    class Solution {
        public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
            if (original == null || cloned == null || target == null) { return null; }

            TreeNode node = cloned;
            Stack<TreeNode> stack = new Stack<>();
            stack.push(node);

            while(!stack.isEmpty()) {
                node = stack.pop();

                if (isClone(target, node)) { return node; }
                if (node.right != null) { stack.push(node.right); }
                if (node.left != null) { stack.push(node.left); }

            }
            return node;
        }

        private boolean isClone(TreeNode original, TreeNode cloned) {
            if (original == null) { return cloned == null; }
            if (cloned == null) { return false; }

            boolean result = original.val == cloned.val;
            if (result) {
                result &= isClone(original.left, cloned.left);
                result &= isClone(original.right, cloned.right);
            }
            return result;
        }
    }
}
