package leetcode.easy;

import leetcode.TreeNode;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 501. Find Mode in Binary Search Tree
 * Easy
 *
 * 2157
 *
 * 561
 *
 * Add to List
 *
 * Share
 * Given the root of a binary search tree (BST) with duplicates, return all the mode(s) (i.e., the most frequently occurred element) in it.
 *
 * If the tree has more than one mode, return them in any order.
 *
 * Assume a BST is defined as follows:
 *
 * The left subtree of a node contains only nodes with keys less than or equal to the node's key.
 * The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
 * Both the left and right subtrees must also be binary search trees.
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,null,2,2]
 * Output: [2]
 * Example 2:
 *
 * Input: root = [0]
 * Output: [0]
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 104].
 * -105 <= Node.val <= 105
 *
 *
 * Follow up: Could you do that without using any extra space? (Assume that the implicit stack space incurred due to recursion does not count).
 * Success
 * Details
 * Runtime: 34 ms, faster than 5.18% of Java online submissions for Find Mode in Binary Search Tree.
 * Memory Usage: 48.9 MB, less than 5.47% of Java online submissions for Find Mode in Binary Search Tree.
 * Next challenges:
 * Validate Binary Search Tree
 */
public class _501_BST_Mode {
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
        public int[] findMode(TreeNode root) {
            if (root == null) {
                return new int[0];
            }
            List<Integer> list = new ArrayList<>();
            Stack<TreeNode> stack = new Stack<>();
            TreeNode current = root;

            while(!stack.isEmpty() || current != null) {
                if (current != null) {
                    stack.push(current);
                    current = current.left;
                } else {
                    current = stack.pop();
                    list.add(current.val);
                    current = current.right;
                }
            }

            List<Integer> modes =
                list.stream()
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())) // Map<Integer,Long>
                    .entrySet()
                    .stream()
                    .collect(
                        Collectors.groupingBy(
                            Map.Entry::getValue, // TreeMap<Long,List<Integer>>
                            TreeMap::new,
                            Collectors.mapping(Map.Entry::getKey,Collectors.toList())
                        )
                    )
                    .lastEntry()
                    .getValue();

            return modes.stream().mapToInt(i->i).toArray();
        }
    }


}
