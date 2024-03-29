package leetcode.medium;

import leetcode.TreeNode;

/**
 * 654. Maximum Binary Tree
 * Medium
 *
 * 3715
 *
 * 297
 *
 * Add to List
 *
 * Share
 * You are given an integer array nums with no duplicates. A maximum binary tree can be built recursively from nums using the following algorithm:
 *
 * Create a root node whose value is the maximum value in nums.
 * Recursively build the left subtree on the subarray prefix to the left of the maximum value.
 * Recursively build the right subtree on the subarray suffix to the right of the maximum value.
 * Return the maximum binary tree built from nums.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: nums = [3,2,1,6,0,5]
 * Output: [6,3,5,null,2,0,null,null,1]
 * Explanation: The recursive calls are as follow:
 * - The largest value in [3,2,1,6,0,5] is 6. Left prefix is [3,2,1] and right suffix is [0,5].
 *     - The largest value in [3,2,1] is 3. Left prefix is [] and right suffix is [2,1].
 *         - Empty array, so no child.
 *         - The largest value in [2,1] is 2. Left prefix is [] and right suffix is [1].
 *             - Empty array, so no child.
 *             - Only one element, so child is a node with value 1.
 *     - The largest value in [0,5] is 5. Left prefix is [0] and right suffix is [].
 *         - Only one element, so child is a node with value 0.
 *         - Empty array, so no child.
 * Example 2:
 *
 *
 * Input: nums = [3,2,1]
 * Output: [3,null,2,null,1]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 1000
 * 0 <= nums[i] <= 1000
 * All integers in nums are unique.
 *
 * Runtime: 8 ms, faster than 17.77% of Java online submissions for Maximum Binary Tree.
 * Memory Usage: 53.8 MB, less than 53.83% of Java online submissions for Maximum Binary Tree.
 * Next challenges:
 * Maximum Binary Tree II
 */
public class _654_BT_BuildFromNumsMax {

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
        public TreeNode constructMaximumBinaryTree(int[] nums) {
            return buildTree(nums);
        }

        private TreeNode buildTree(int[] nums) {
            if (nums == null || nums.length < 1) {
                return null;
            }
            int len = nums.length;
            int max = Integer.MIN_VALUE;
            int j = -1;
            for (int i = 0; i < len; i++) {
                if (max < nums[i]) {
                    max = nums[i];
                    j = i;
                }
            }

            TreeNode root = new TreeNode(max);
            int len1 = Math.max(j, 0);
            int len2 = Math.max(len-1 - j, 0);
            int[] nums1 = new int[j - 0];       //[0, j-1]
            int[] nums2 = new int[Math.max(len - 1 - j, 0)]; //[j+1, len -1]


            System.arraycopy(nums, 0, nums1, 0, len1);
            System.arraycopy(nums, Math.min(j+1, len-1), nums2, 0, len2);

            TreeNode left = buildTree(nums1);
            TreeNode right = buildTree(nums2);

            if (left != null) {
                root.left = left;
            }
            if (right != null) {
                root.right = right;
            }

            return root;

        }
    }
}
