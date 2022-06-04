package leetcode.easy;

import leetcode.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 101. Symmetric Tree
 * Easy
 *
 * 9518
 *
 * 234
 *
 * Add to List
 *
 * Share
 * Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,2,2,3,4,4,3]
 * Output: true
 * Example 2:
 *
 *
 * Input: root = [1,2,2,null,3,null,3]
 * Output: false
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 1000].
 * -100 <= Node.val <= 100
 *
 *
 * Follow up: Could you solve it both recursively and iteratively?
 *
 * Runtime: 125 ms, faster than 10.87% of Java online submissions for Symmetric Tree.
 * Memory Usage: 51.4 MB, less than 7.45% of Java online submissions for Symmetric Tree.
 * Next challenges:
 * Logical OR of Two Binary Grids Represented as Quad-Trees
 * All Nodes Distance K in Binary Tree
 * Step-By-Step Directions From a Binary Tree Node to Another
 */
public class _101_BinaryTreeIsSymmertic {

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
        public boolean isSymmetric(TreeNode root) {
            if (root == null) {
                return true;
            }

            boolean result = true;
            List<TreeNode> parents = new ArrayList<>();
            List<TreeNode> children = new ArrayList<>();

            parents.add(root);

            while (!parents.isEmpty()) {

                children.clear();
                children.addAll(parents);
                parents.clear();

                int len = children.size();
                int i = 0;
                int j = len - 1;

                while (i <= j) {
                    result &= equals(children.get(i), children.get(j));
                    i++;
                    j--;
                }

                for (TreeNode node : children) {
                    if (node != null) {
                        parents.add(node.left);
                        parents.add(node.right);
                    }
                }

                System.out.printf("p: %s, c: %s\n", String.valueOf(parents), String.valueOf(children));
            }

            return result;

        }

        private boolean equals(TreeNode a, TreeNode b) {
            boolean result = (a == null && b == null) || (a != null && b != null && a.val == b.val);
            System.out.printf("a: %s, b: %s, result: %s \n", a == null ? null : a.val, b == null ? null : b.val, result);
            return result;
        }
    }


}
