package leetcode.medium;

/**
 * Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.
 *
 * If target is not found in the array, return [-1, -1].
 *
 * You must write an algorithm with O(log n) runtime complexity.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 * Example 2:
 *
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1,-1]
 * Example 3:
 *
 * Input: nums = [], target = 0
 * Output: [-1,-1]
 *
 *
 * Constraints:
 *
 * 0 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * nums is a non-decreasing array.
 * -109 <= target <= 109
 */
public class _34_BS_FindRangeSortedArray {

    class Solution {
        public int[] searchRange(int[] nums, int target) {

            if (nums == null || nums.length < 1) {
                return new int[] {-1, -1};
            }

            int len = nums.length;
            if (len == 1) {
                return nums[0] == target ? new int[] {0,0} : new int[] {-1, -1};
            }

            return searchRange(nums, new int[]{0, len -1}, target);
        }


        private int[] searchRange(int[] nums, int[] range, int target) {
            if (range[0] < 0 || range[1] < 0 || range[0] > nums.length || range[1] > nums.length || range[0] > range[1]) {
                return new int[]{-1, -1};
            }
            
            int len = range[1] - range[0] + 1;
            if (len == 1) {
                return nums[range[0]] == target ? new int[] {range[0],range[0]} : new int[] {-1, -1};
            }

            int[] nextRange = new int[]{range[0], range[1]};

            if(nums[nextRange[0]] == target && nums[nextRange[1]] == target) {
                return nextRange;
            } else if (nums[nextRange[0]] > target || nums[nextRange[1]] < target) {
                return new int[] {-1, -1};
            }

            if (nums[range[0]] < target) {
                nextRange[0]++;
            }

            if(nums[range[1]] > target) {
                nextRange[1]--;
            }

            int quarter =  (nextRange[1] - nextRange[0] + 1) / 4;
            int left = nextRange[0] + quarter;
            int right = nextRange[1] - quarter;

            if (nums[left] > target) {
                nextRange[1] = left-1;
            } else {
                if (target < nums[right]) {
                    nextRange[1] = right;
                }
            }
            return searchRange(nums, nextRange, target);

        }
    }


}
