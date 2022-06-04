package leetcode.medium;

/**
 * There is an integer array nums sorted in ascending order (with distinct values).
 *
 * Prior to being passed to your function, nums is possibly rotated at an unknown pivot index k (1 <= k < nums.length) such that
 * the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,5,6,7]
 * might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
 *
 * Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.
 *
 * You must write an algorithm with O(log n) runtime complexity.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 * Example 2:
 *
 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * Output: -1
 * Example 3:
 *
 * Input: nums = [1], target = 0
 * Output: -1
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 5000
 * -104 <= nums[i] <= 104
 * All values of nums are unique.
 * nums is an ascending array that is possibly rotated.
 * -104 <= target <= 104
 */
public class _33_SearchInRotatedSortedArray {

    class Solution {
        public int search(int[] nums, int target) {
            if (nums == null || nums.length < 1) {
                return -1;
            }

            int len = nums.length;
            int pivot = findPivot(nums, 0, len - 1);

            if (pivot != -1) {
                if (target == nums[pivot]) {
                    return pivot;
                }

                if (pivot == 0) {
                    pivot = search(nums, target, 0, len - 1);
                } else {
                    if (target >= nums[0] && target <= nums[pivot - 1]) {
                        pivot = search(nums, target, 0, pivot - 1);
                    } else {
                        pivot = search(nums, target, pivot + 1, len - 1);
                    }
                }
            }
            return pivot;
        }

        private int search(int[] nums, int target, int start, int end) {
            if (start > end || start < 0 || end < 0 || start > nums.length || end > nums.length) {
                return -1;
            }
            if (start >= end) {
                return  (nums[start] == target) ? start : -1;
            }

            int middle = start + (end - start) / 2;
            if (nums[middle] == target) {
                return middle;
            }

            //normal order
            if (nums[middle] < target) {
                return search(nums, target, middle + 1, end);
            } else {
                return search(nums, target, start, middle - 1);
            }

        }

        private int findPivot(int[] nums, int start, int end) {
            if (start >= end) {
                return start;
            }
            int len = end - start + 1;
            if (len == 2) {
                if (nums[start] > nums[end]) {
                    return end;
                } else {
                    return start;
                }
            }

            int middle = start + (end - start) / 2;
            //System.out.printf("> middle: %s, start: %s, end: %s\n", nums[middle], nums[start], nums[end]);
            if (nums[middle] > nums[middle + 1]) {
                return middle + 1;
            }

            if (middle - 1 >= 0 && nums[middle] < nums[middle - 1]) {
                return middle;
            }

            if (nums[start] > nums[middle - 1]) {
                return findPivot(nums, start, middle - 1);
            } else {
                return findPivot(nums, middle + 1, end);
            }
        }
    }

}
