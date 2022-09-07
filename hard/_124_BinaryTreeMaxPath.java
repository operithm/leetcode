package leetcode.hard;

import leetcode.TreeNode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 124. Binary Tree Maximum Path Sum
 *
 * A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them.
 * A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.
 *
 * The path sum of a path is the sum of the node's values in the path.
 *
 * Given the root of a binary tree, return the maximum path sum of any non-empty path.
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,2,3]
 * Output: 6
 * Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.
 * Example 2:
 *
 *
 * Input: root = [-10,9,20,null,null,15,7]
 * Output: 42
 * Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 3 * 104].
 * -1000 <= Node.val <= 1000
 *
 * Success
 * Details
 *
 * Runtime: 21 ms, faster than 5.06% of Java online submissions for Binary Tree Maximum Path Sum.
 * Memory Usage: 51.2 MB, less than 5.10% of Java online submissions for Binary Tree Maximum Path Sum.
 *
 * Next challenges:
 *
 * Sum Root to Leaf Numbers
 * Path Sum IV
 * Longest Univalue Path
 * Time Needed to Inform All Employees
 */
public class _124_BinaryTreeMaxPath {
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

    private static class Data {
        private final int end;
        private final int path;
        public Data(int end, int path) {
            this.end = end;
            this.path = path;
        }
    }

    public static class Solution {
        
        private final Map<TreeNode, Data> dataMap = new HashMap<>();

        public int maxPathSum(TreeNode root) {
            TreeNode node = root;
            Stack<TreeNode> stack = new Stack<>();
            TreeNode lastVisited = null;

            int max = Integer.MIN_VALUE;
            /**
             * Postorder traversal so that parent can always get children's path
             */
            while(!stack.isEmpty() || node != null) {
                if(node != null) {
                    stack.push(node);
                    node = node.left;
                } else {
                    TreeNode peek = stack.peek();
                    if (peek.right != null && peek.right != lastVisited) {
                        node = peek.right;
                    } else {
                        int len = visit(peek);
                        if (len > max) {
                            max = len;
                        }
                        lastVisited = stack.pop();
                    }
                }
            }
            return max;
        }

        /**
         * Visit function
         * @param peek
         * @return
         */
        private int visit(TreeNode peek) {
            Data left = peek.left != null ? dataMap.get(peek.left) : null;
            Data right = peek.right != null ? dataMap.get(peek.right) : null;

            int toLeft = left != null ? left.path : 0;
            int toRight = right != null ? right.path : 0;

            int path = Math.max(Math.max(toLeft, toRight) + peek.val, peek.val);
            int end = toLeft + peek.val + toRight;

            dataMap.put(peek, new Data(end, path));

            return Math.max(end, path);
        }
    }
}
