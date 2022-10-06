package leetcode.hard;

/**
 * Success
 * Details
 * Runtime: 2 ms, faster than 61.44% of Java online submissions for Trapping Rain Water.
 * Memory Usage: 49.5 MB, less than 14.74% of Java online submissions for Trapping Rain Water.
 * Next challenges:
 * Container With Most Water
 * Product of Array Except Self
 * Trapping Rain Water II
 * Pour Water
 *
 * 42. Trapping Rain Water
 * Hard
 *
 * 22360
 *
 * 299
 *
 * Add to List
 *
 * Share
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 * Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.
 * Example 2:
 *
 * Input: height = [4,2,0,3,2,5]
 * Output: 9
 *
 *
 * Constraints:
 *
 * n == height.length
 * 1 <= n <= 2 * 104
 * 0 <= height[i] <= 105
 */
public class _42_2P_TrappingRainWater {
    class Solution {
        public int trap(int[] height) {
            int n = height.length;
            int[] left = new int[n];
            int[] right = new int[n];

            int leftMax = Integer.MIN_VALUE;
            for (int i = 0; i < n; i++) {
                leftMax = Math.max(leftMax, height[i]);
                left[i] = leftMax;
            }

            int rightMax = Integer.MIN_VALUE;
            for (int j = n -1; j >= 0; j--) {
                rightMax = Math.max(rightMax, height[j]);
                right[j] = rightMax;
            }

            int sum = 0;
            for (int k = 0; k < n; k ++) {
                sum += Math.min(left[k], right[k]) - height[k];
            }

            return sum;
        }
    }
}
