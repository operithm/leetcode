package leetcode.easy;

import leetcode.TreeNode;

import java.util.*;

/**
 * 653. Two Sum IV - Input is a BST
 * Easy
 *
 * 3705
 *
 * 202
 *
 * Add to List
 *
 * Share
 * Given the root of a Binary Search Tree and a target number k, return true if there exist two elements in the BST such that their sum is equal to the given target.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [5,3,6,2,4,null,7], k = 9
 * Output: true
 * Example 2:
 *
 *
 * Input: root = [5,3,6,2,4,null,7], k = 28
 * Output: false
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 104].
 * -104 <= Node.val <= 104
 * root is guaranteed to be a valid binary search tree.
 * -105 <= k <= 105
 *
 * Runtime: 44 ms, faster than 5.07% of Java online submissions for Two Sum IV - Input is a BST.
 * Memory Usage: 56.2 MB, less than 5.54% of Java online submissions for Two Sum IV - Input is a BST.
 * Next challenges:
 * Two Sum II - Input Array Is Sorted
 * Two Sum III - Data structure design
 * Two Sum BSTs
 */
public class _653_BT_SumOfTwoNode {

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
    private static class Solution {
        public boolean findTarget(TreeNode root, int k) {
            boolean result = false;

            Stack<TreeNode> stack = new Stack<>();
            Map<Integer, Set<TreeNode>> map = new TreeMap<>();

            TreeNode current = root;
            while (!stack.isEmpty() || current != null) {
                if (current != null) {
                    stack.push(current);
                    current = current.left;
                } else {
                    current = stack.pop();
                    int comKey = k - current.val;
                    result |= (map.containsKey(comKey));
                    put(map, current);
                    current = current.right;
                }
            }

            return result;

        }

        private static void put (Map<Integer, Set<TreeNode>> map, TreeNode node) {
            Set<TreeNode> set = map.getOrDefault(node.val, new HashSet<>());
            set.add(node);
            map.put(node.val, set);
        }

    }
}
