package leetcode.medium;

import leetcode.TreeNode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class _2265_BinaryTreeAverageNodeCount {

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
        private final int sum;
        private final int count;
        public Data(int sum, int count) {
            this.sum = sum;
            this.count = count;
        }
    }

    public static class Solution {

        private static final Map<TreeNode, Data> dataMap = new HashMap<>();

        public int averageOfSubtree(TreeNode root) {
            if (root == null) { return 0; }
            Stack<TreeNode> stack = new Stack<>();
            TreeNode node = root;
            TreeNode lastVisited = null;

            int result = 0;

            while(!stack.isEmpty() || node != null) {
                if (node != null) {
                    stack.push(node);
                    node = node.left;
                } else {
                    TreeNode peek = stack.peek();
                    if (peek.right != null && peek.right != lastVisited) {
                        node = peek.right;
                    } else {
                        //visit node, post order so children already visited
                        result += visit(peek);
                        //System.out.printf("sum: %s, count: %s, val: %s, result: %s\n", sum, count, peek.val, result);
                        lastVisited = stack.pop();
                    }
                }
            }
            return result;
        }

        /**
         *
         * @param peek
         * @return
         */
        private int visit (TreeNode peek) {
            Data left = peek.left != null ? dataMap.get(peek.left) : null;
            Data right = peek.right != null ? dataMap.get(peek.right) : null;
            int sum = peek.val
                    + (left != null ? left.sum : 0)
                    + (right != null ? right.sum : 0);
            int count = 1 + (left != null ? left.count : 0) + (right != null ? right.count : 0);
            dataMap.put(peek, new Data(sum, count));

            return ((int)(Math.floor(sum/count)) == peek.val) ? 1 : 0;
        }

    }
}
