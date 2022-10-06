package leetcode.medium;

import leetcode.TreeNode;

import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

/**
 *
 * 1644. Lowest Common Ancestor of a Binary Tree II
 * Medium
 *
 * 447
 *
 * 23
 *
 * Add to List
 *
 * Share
 * Given the root of a binary tree, return the lowest common ancestor (LCA) of two given nodes, p and q. If either node p or q does not exist in the tree, return null. All values of the nodes in the tree are unique.
 *
 * According to the definition of LCA on Wikipedia: "The lowest common ancestor of two nodes p and q in a binary tree T is the lowest node that has both p and q as descendants (where we allow a node to be a descendant of itself)". A descendant of a node x is a node y that is on the path from node x to some leaf node.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * Output: 3
 * Explanation: The LCA of nodes 5 and 1 is 3.
 * Example 2:
 *
 *
 *
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * Output: 5
 * Explanation: The LCA of nodes 5 and 4 is 5. A node can be a descendant of itself according to the definition of LCA.
 * Example 3:
 *
 *
 *
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 10
 * Output: null
 * Explanation: Node 10 does not exist in the tree, so return null.
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 104].
 * -109 <= Node.val <= 109
 * All Node.val are unique.
 * p != q
 *
 *
 * Follow up: Can you find the LCA traversing the tree, without checking nodes existence?
 *
 * Success
 * Details
 * Runtime: 113 ms, faster than 1.43% of Java online submissions for Lowest Common Ancestor of a Binary Tree II.
 * Memory Usage: 72.1 MB, less than 38.05% of Java online submissions for Lowest Common Ancestor of a Binary Tree II.
 * Next challenges:
 * Lowest Common Ancestor of a Binary Search Tree
 * Lowest Common Ancestor of a Binary Tree
 * Lowest Common Ancestor of a Binary Tree IV
 */
public class _1644_BT_LowestCommonAncestor_II {

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    private static class Data {
        private final int val;
        private final boolean isParentA;
        private final boolean isParentB;

        public Data(int val, boolean isParentA, boolean isParentB) {
            this.val = val;
            this.isParentA = isParentA;
            this.isParentB = isParentB;
        }
    }

    class Solution {

        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null || p == null || q == null) {
                return null;
            }

            Map<Integer, Data> ancestors = new TreeMap<>();
            TreeNode node = root;
            TreeNode lastVisited = null;
            Stack<TreeNode> stack = new Stack<>();

            while(!stack.isEmpty() || node != null) {
                if (node != null) {
                    stack.push(node);
                    node = node.left;
                } else {
                    TreeNode peek = stack.peek();
                    if (peek.right != null && peek.right != lastVisited) {
                        node = peek.right;
                    } else {
                        if(visit(peek, p, q, ancestors)) {
                            return peek;
                        }
                        lastVisited = stack.pop();
                    }
                }
            }
            return null;
        }

        /**
         *
         */
        private boolean visit(TreeNode node, TreeNode p, TreeNode q, Map<Integer, Data> ancestors) {
            boolean isParentA = node.val == p.val;
            boolean isParentB = node.val == q.val;

            TreeNode left = node.left;
            TreeNode right = node.right;

            if (left != null) {
                Data leftData = ancestors.get(left.val);
                isParentA |= leftData.isParentA;
                isParentB |= leftData.isParentB;
            }

            if (right != null) {
                Data rightData = ancestors.get(right.val);
                isParentA |= rightData.isParentA;
                isParentB |= rightData.isParentB;
            }

            ancestors.put(node.val, new Data(node.val, isParentA, isParentB));

            return isParentA & isParentB;
        }
    }
}
