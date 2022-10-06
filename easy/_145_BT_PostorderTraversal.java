package leetcode.easy;

import leetcode.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 145. Binary Tree Postorder Traversal
 * Easy
 *
 * 4216
 *
 * 141
 *
 * Add to List
 *
 * Share
 * Given the root of a binary tree, return the postorder traversal of its nodes' values.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,null,2,3]
 * Output: [3,2,1]
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
 * The number of the nodes in the tree is in the range [0, 100].
 * -100 <= Node.val <= 100
 *
 *
 * Follow up: Recursive solution is trivial, could you do it iteratively?
 *
 * Success
 * Details
 * Runtime: 1 ms, faster than 44.57% of Java online submissions for Binary Tree Postorder Traversal.
 * Memory Usage: 42.4 MB, less than 36.51% of Java online submissions for Binary Tree Postorder Traversal.
 * Next challenges:
 * N-ary Tree Postorder Traversal
 */
public class _145_BT_PostorderTraversal {

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
        public List<Integer> postorderTraversal(TreeNode root) {
            List<Integer> list = new ArrayList<>();
            Stack<TreeNode> stack = new Stack<>();
            TreeNode lastVisited = null;
            TreeNode node = root;

            while(!stack.isEmpty() || node != null) {
                if(node != null) {
                    stack.push(node);
                    node = node.left;
                } else {
                    TreeNode peek = stack.peek();
                    if (peek.right != null && peek.right != lastVisited) {
                        node = peek.right;
                    } else {
                        list.add(peek.val);
                        lastVisited = stack.pop();
                    }
                }
            }

            return list;

        }
    }


}
