package leetcode.medium;

import leetcode.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 102. Binary Tree Level Order Traversal
 * Medium
 *
 * 8301
 *
 * 163
 *
 * Add to List
 *
 * Share
 * Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[3],[9,20],[15,7]]
 * Example 2:
 *
 * Input: root = [1]
 * Output: [[1]]
 * Example 3:
 *
 * Input: root = []
 * Output: []
 *
 * Success
 * Details
 * Runtime: 1 ms, faster than 85.66% of Java online submissions for Binary Tree Level Order Traversal.
 * Memory Usage: 42.4 MB, less than 83.23% of Java online submissions for Binary Tree Level Order Traversal.
 * Next challenges:
 * Binary Tree Zigzag Level Order Traversal
 * Binary Tree Level Order Traversal II
 * Minimum Depth of Binary Tree
 * Binary Tree Vertical Order Traversal
 * N-ary Tree Level Order Traversal
 * Cousins in Binary Tree
 */
public class _102_BT_LevelTraversal {

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
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> result = new ArrayList<>();
            if (root == null) {
                return result;
            }

            List<TreeNode> parents = new ArrayList<>();
            List<TreeNode> children = new ArrayList<>();
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
                result.add(vals);

            }

            return result;

        }
    }
}
