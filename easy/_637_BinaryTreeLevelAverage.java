package leetcode.easy;

import leetcode.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 637. Average of Levels in Binary Tree
 * Easy
 *
 * 3009
 *
 * 238
 *
 * Add to List
 *
 * Share
 * Given the root of a binary tree, return the average value of the nodes on each level in the form of an array. Answers within 10-5 of the actual answer will be accepted.
 *
 *
 * Example 1:
 *
 *
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [3.00000,14.50000,11.00000]
 * Explanation: The average value of nodes on level 0 is 3, on level 1 is 14.5, and on level 2 is 11.
 * Hence return [3, 14.5, 11].
 * Example 2:
 *
 *
 * Input: root = [3,9,20,15,7]
 * Output: [3.00000,14.50000,11.00000]
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 104].
 * -231 <= Node.val <= 231 - 1
 *
 * Success
 * Details
 * Runtime: 3 ms, faster than 59.93% of Java online submissions for Average of Levels in Binary Tree.
 * Memory Usage: 44.1 MB, less than 74.92% of Java online submissions for Average of Levels in Binary Tree.
 * Next challenges:
 * Binary Tree Level Order Traversal
 * Binary Tree Level Order Traversal II
 */
public class _637_BinaryTreeLevelAverage {

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
        public List<Double> averageOfLevels(TreeNode root) {
            if (root == null) {
                return List.of(0.0d);
            }
            List<Double> result = new ArrayList<>();

            Queue<TreeNode> parents = new LinkedList<>();
            Queue<TreeNode> children = new LinkedList<>();

            parents.add(root);

            while(!parents.isEmpty()) {
                children.clear();
                children.addAll(parents);

                parents.clear();

                double sum = 0.0d;
                for (TreeNode node : children) {
                    sum += node.val;
                    if (node.left != null) {
                        parents.add(node.left);
                    }
                    if (node.right != null) {
                        parents.add(node.right);
                    }
                }
                result.add(sum / children.size());
            }

            return result;

        }

    }
}
