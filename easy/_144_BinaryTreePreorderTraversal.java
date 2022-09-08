package leetcode.easy;

import leetcode.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 144. Binary Tree Preorder Traversal
 * Easy
 *
 * 4138
 *
 * 130
 *
 * Add to List
 *
 * Share
 * Given the root of a binary tree, return the preorder traversal of its nodes' values.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,null,2,3]
 * Output: [1,2,3]
 * Example 2:
 *
 * Input: root = []
 * Output: []
 * Example 3:
 *
 * Input: root = [1]
 * Output: [1]
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 100].
 * -100 <= Node.val <= 100
 *
 *
 * Follow up: Recursive solution is trivial, could you do it iteratively?
 *
 * Success
 * Details
 * Runtime: 2 ms, faster than 43.58% of Java online submissions for Binary Tree Preorder Traversal.
 * Memory Usage: 41.9 MB, less than 61.12% of Java online submissions for Binary Tree Preorder Traversal.
 * Next challenges:
 * Verify Preorder Sequence in Binary Search Tree
 * N-ary Tree Preorder Traversal
 */
public class _144_BinaryTreePreorderTraversal {
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
        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> list = new ArrayList<>();

            Stack<TreeNode> stack = new Stack<>();
            TreeNode node = root;

            if (node != null) {
                stack.push(node);
            }
            while(!stack.isEmpty()) {
                node = stack.pop();
                list.add(node.val);

                if (node.right != null) {
                    stack.push(node.right);
                }

                if (node.left != null) {
                    stack.push(node.left);
                }
            }

            return list;
        }
    }

}
