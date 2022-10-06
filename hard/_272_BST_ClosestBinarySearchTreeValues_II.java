package leetcode.hard;

import leetcode.TreeNode;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * Runtime 9 ms Beats 20.65% Memory 42.8 MB Beats 85.86%
 *
 * Next question
 *
 * 273. Integer to English Words
 * More challenges
 * 270. Closest Binary Search Tree Value
 * 272. Closest Binary Search Tree Value II
 * Hard
 *
 * company
 * Google
 * Given the root of a binary search tree, a target value, and an integer k, return the k values in the BST that are closest to the target.
 * You may return the answer in any order.
 *
 * You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
 *
 * Example 1:
 *
 * Input: root = [4,2,5,1,3], target = 3.714286, k = 2
 * Output: [4,3]
 * Example 2:
 *
 * Input: root = [1], target = 0.000000, k = 1
 * Output: [1]
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is n.
 * 1 <= k <= n <= 104.
 * 0 <= Node.val <= 109
 * -109 <= target <= 109
 *
 *
 * Follow up: Assume that the BST is balanced. Could you solve it in less than O(n) runtime (where n = total nodes)?
 *
 */
public class _272_BST_ClosestBinarySearchTreeValues_II {

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
        public List<Integer> closestKValues(TreeNode root, double target, int k) {
            if (root == null || k <= 0) {
                return List.of();
            }
            PriorityQueue<double[]> pq = new PriorityQueue<double[]>(k, (a, b) -> (int)(b[1] * 100000 - a[1] * 100000));
            inOrderTraversal(root, pq, target, k);

            return pq.stream().map( x -> (int)x[0]).collect(Collectors.toList());
        }


        private void inOrderTraversal(TreeNode root,
                                      PriorityQueue<double[]> pq, double target, int k) {
            Stack<TreeNode> stack = new Stack<>();
            var node = root;
            while (!stack.isEmpty() || node != null) {
                if (node != null) {
                    stack.push(node);
                    node = node.left;
                } else {
                    node = stack.pop();
                    /**
                     * The visit function
                     */
                    var diff = new double[] {node.val * 1.0d, Math.abs(node.val * 1.0d - target)};
                    pq.offer(diff);
                    if (pq.size() > k) {
                        pq.poll();
                    }
                    node = node.right;
                }
            }
        }

    }
}
