package leetcode.easy;

/**
 * 496. Next Greater Element I
 * Easy
 *
 * 3031
 *
 * 197
 *
 * Add to List
 *
 * Share
 * The next greater element of some element x in an array is the first greater element that is to the right of x in the same array.
 *
 * You are given two distinct 0-indexed integer arrays nums1 and nums2, where nums1 is a subset of nums2.
 *
 * For each 0 <= i < nums1.length, find the index j such that nums1[i] == nums2[j] and determine the next greater element of nums2[j] in nums2. If there is no next greater element, then the answer for this query is -1.
 *
 * Return an array ans of length nums1.length such that ans[i] is the next greater element as described above.
 *
 *
 *
 * Example 1:
 *
 * Input: nums1 = [4,1,2], nums2 = [1,3,4,2]
 * Output: [-1,3,-1]
 * Explanation: The next greater element for each value of nums1 is as follows:
 * - 4 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
 * - 1 is underlined in nums2 = [1,3,4,2]. The next greater element is 3.
 * - 2 is underlined in nums2 = [1,3,4,2]. There is no next greater element, so the answer is -1.
 * Example 2:
 *
 * Input: nums1 = [2,4], nums2 = [1,2,3,4]
 * Output: [3,-1]
 * Explanation: The next greater element for each value of nums1 is as follows:
 * - 2 is underlined in nums2 = [1,2,3,4]. The next greater element is 3.
 * - 4 is underlined in nums2 = [1,2,3,4]. There is no next greater element, so the answer is -1.
 *
 *
 * Constraints:
 *
 * 1 <= nums1.length <= nums2.length <= 1000
 * 0 <= nums1[i], nums2[i] <= 104
 * All integers in nums1 and nums2 are unique.
 * All the integers of nums1 also appear in nums2.
 *
 *
 * Follow up: Could you find an O(nums1.length + nums2.length) solution?
 *
 *
 * Success
 * Details
 * Runtime: 417 ms, faster than 5.13% of Java online submissions for Next Greater Element I.
 * Memory Usage: 54 MB, less than 6.88% of Java online submissions for Next Greater Element I.
 * Next challenges:
 *
 * Next Greater Element II
 * Next Greater Element III
 * Daily Temperatures
 * Sum of Subarray Ranges
 * Sum of Total Strength of Wizards
 *
 */
public class _496_NextGreaterElement_I {

    class Solution {
        public int[] nextGreaterElement(int[] nums1, int[] nums2) {
            if (nums1 == null || nums1.length < 1 || nums2 == null || nums2.length < 1) {
                return new int[0];
            }

            int m = nums1.length;
            int n = nums2.length;
            int[] result = new int[m];

            int j = 0 ;
            for (int i = 0; i < m; i++) {

                while(j < n && nums2[j] != nums1[i]) {
                    j++;
                }
                //System.out.printf("i: %s,j: %s\n", i, j);
                int bigger = -1;
                while (j < n) {
                    System.out.printf("j: %s\n", j);
                    if (nums2[j] > nums1[i]) {
                        bigger = nums2[j];
                        System.out.printf("bigger: %s\n", bigger);
                        break;
                    }
                    j++;
                }

                result[i] = bigger;
                j = 0;
            }

            return result;
        }
    }

}
