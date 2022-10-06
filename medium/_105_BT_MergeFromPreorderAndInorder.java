package leetcode.medium;

import leetcode.TreeNode;

import java.util.Stack;

/**
 *
 * 105. Construct Binary Tree from Preorder and Inorder Traversal
 * Medium
 *
 * 8585
 *
 * 235
 *
 * Add to List
 *
 * Share
 * Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree and inorder is the inorder traversal of the same tree, construct and return the binary tree.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * Output: [3,9,20,null,null,15,7]
 * Example 2:
 *
 * Input: preorder = [-1], inorder = [-1]
 * Output: [-1]
 *
 *
 * Constraints:
 *
 * 1 <= preorder.length <= 3000
 * inorder.length == preorder.length
 * -3000 <= preorder[i], inorder[i] <= 3000
 * preorder and inorder consist of unique values.
 * Each value of inorder also appears in preorder.
 * preorder is guaranteed to be the preorder traversal of the tree.
 * inorder is guaranteed to be the inorder traversal of the tree.
 * Success
 * Details
 * Runtime: 126 ms, faster than 5.13% of Java online submissions for Construct Binary Tree from Preorder and Inorder Traversal.
 * Memory Usage: 48.7 MB, less than 6.30% of Java online submissions for Construct Binary Tree from Preorder and Inorder Traversal.
 * Next challenges:
 * Construct Binary Tree from Inorder and Postorder Traversal
 */
public class _105_BT_MergeFromPreorderAndInorder {

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
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            if (preorder == null || inorder == null || preorder.length != inorder.length) {
                return null;
            }
            TreeNode node = new TreeNode(preorder[0]);
            TreeNode root = node;
            Stack<TreeNode> nodes = new Stack<>();
            nodes.push(node);

            TreeNode left = null;

            int len = preorder.length;
            int i = 0, j = 0;

            while((i < len && j < len) || !nodes.isEmpty()) {

                if (!nodes.isEmpty()) {
                    TreeNode peek = nodes.peek();

                    if (j < len && inorder[j] != peek.val) {
                        i++;
                        if (i < len) {
                            TreeNode next = new TreeNode(preorder[i]);
                            if (left == null) {
                                peek.left = next;
                            } else {
                                left.right = next;
                                left = null;
                            }

                            nodes.push(next);
                            System.out.printf("push: %s, i: %s\n", next.val, i);
                        }
                    } else {
                        left = nodes.pop();
                        j++;
                    }

                } else {
                    i++;
                    if (i < len) {
                        TreeNode next = new TreeNode(preorder[i]);
                        if (left != null) {
                            left.right = next;
                            left = null;
                        }
                        nodes.push(next);
                        System.out.printf("push: %s, i: %s\n", next.val, i);
                    }
                }
            }

            return root;

        }
    }
}
