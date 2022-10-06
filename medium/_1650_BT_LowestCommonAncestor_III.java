package leetcode.medium;

import java.util.Stack;

/**
 * 1650. Lowest Common Ancestor of a Binary Tree III
 * Medium
 *
 * 965
 *
 * 32
 *
 * Add to List
 *
 * Share
 * Given two nodes of a binary tree p and q, return their lowest common ancestor (LCA).
 *
 * Each node will have a reference to its parent node. The definition for Node is below:
 *
 * class Node {
 *     public int val;
 *     public Node left;
 *     public Node right;
 *     public Node parent;
 * }
 * According to the definition of LCA on Wikipedia: "The lowest common ancestor of two nodes p and q in a tree T is the lowest node that has both p and q as descendants (where we allow a node to be a descendant of itself)."
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
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * Output: 5
 * Explanation: The LCA of nodes 5 and 4 is 5 since a node can be a descendant of itself according to the LCA definition.
 * Example 3:
 *
 * Input: root = [1,2], p = 1, q = 2
 * Output: 1
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [2, 105].
 * -109 <= Node.val <= 109
 * All Node.val are unique.
 * p != q
 * p and q exist in the tree.
 *
 * Success
 * Details
 * Runtime: 47 ms, faster than 27.50% of Java online submissions for Lowest Common Ancestor of a Binary Tree III.
 * Memory Usage: 47.4 MB, less than 57.46% of Java online submissions for Lowest Common Ancestor of a Binary Tree III.
 * Next challenges:
 * Lowest Common Ancestor of a Binary Search Tree
 * Lowest Common Ancestor of a Binary Tree
 * Lowest Common Ancestor of a Binary Tree II
 * Lowest Common Ancestor of a Binary Tree IV
 */
public class _1650_BT_LowestCommonAncestor_III {

    // Definition for a Node.
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    };

    class Solution {

        public Node lowestCommonAncestor(Node p, Node q) {
            if (findChild(q, p) != null) {
                return q;
            }

            Node current = p;
            while (current != null) {
                Node child = findChild(current, q);
                if ( child != null) {
                    return current;
                } else {
                    current = current.parent;
                }
            }
            return current;
        }

        private Node findChild(Node p, Node q) {
            if (p == null || q == null) {
                return p;
            }

            Stack<Node> stack = new Stack<>();
            stack.push(p);

            while(!stack.isEmpty()) {
                Node current = stack.pop();

                if (current.val == q.val) {
                    return current;
                }

                if (current.right != null) {
                    stack.push(current.right);
                }
                if (current.left != null) {
                    stack.push(current.left);
                }
            }
            return null;
        }
    }
}
