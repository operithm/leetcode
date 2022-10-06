package leetcode.medium;

import leetcode.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 107. Binary Tree Level Order Traversal II
 * Medium
 *
 *
 * Given the root of a binary tree, return the bottom-up level order traversal of its nodes' values. (i.e., from left to right, level by level from leaf to root).
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[15,7],[9,20],[3]]
 * Example 2:
 *
 * Input: root = [1]
 * Output: [[1]]
 * Example 3:
 *
 * Input: root = []
 * Output: []
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 2000].
 * -1000 <= Node.val <= 1000
 *
 * Success
 * Details
 * Runtime: 3 ms, faster than 12.12% of Java online submissions for Binary Tree Level Order Traversal II.
 * Memory Usage: 44.1 MB, less than 7.47% of Java online submissions for Binary Tree Level Order Traversal II.
 * Next challenges:
 * Binary Tree Zigzag Level Order Traversal
 * Delete Leaves With a Given Value
 * Clone Binary Tree With Random Pointer
 */
public class _107_BT_LevelTraversal_II {
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
        public List<List<Integer>> levelOrderBottom(TreeNode root) {
            List<List<Integer>> result = new ArrayList<>();
            if (root == null) {
                return result;
            }

            List<TreeNode> parents = new ArrayList<>();
            List<TreeNode> children = new ArrayList<>();
            Stack<List<Integer>> stack = new Stack<>();

            parents.add(root);

            while (!parents.isEmpty()) {
                List<Integer> vals = new ArrayList<>();
                children.clear();
                children.addAll(parents);
                parents.clear();

                for (TreeNode node : children) {
                    vals.add(node.val);
                    if (node.left != null) {
                        parents.add(node.left);
                    }
                    if (node.right != null) {
                        parents.add(node.right);
                    }
                }

                stack.push(vals);
            }

            while(!stack.isEmpty()) {
                result.add(stack.pop());
            }

            return result;

        }
    }

}
