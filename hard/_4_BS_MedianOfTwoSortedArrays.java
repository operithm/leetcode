package leetcode.hard;

/**
 * Success
 * Details
 * Runtime: 4 ms, faster than 72.43% of Java online submissions for Median of Two Sorted Arrays.
 * Memory Usage: 49.8 MB, less than 57.87% of Java online submissions for Median of Two Sorted Arrays.
 * Next challenges:
 * Median of a Row Wise Sorted Matrix
 *
 * 4. Median of Two Sorted Arrays
 * Hard
 *
 * 19471
 *
 * 2221
 *
 * Add to List
 *
 * Share
 * Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
 *
 * The overall run time complexity should be O(log (m+n)).
 *
 *
 *
 * Example 1:
 *
 * Input: nums1 = [1,3], nums2 = [2]
 * Output: 2.00000
 * Explanation: merged array = [1,2,3] and median is 2.
 * Example 2:
 *
 * Input: nums1 = [1,2], nums2 = [3,4]
 * Output: 2.50000
 * Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
 *
 *
 * Constraints:
 *
 * nums1.length == m
 * nums2.length == n
 * 0 <= m <= 1000
 * 0 <= n <= 1000
 * 1 <= m + n <= 2000
 * -106 <= nums1[i], nums2[i] <= 106
 */
public class _4_BS_MedianOfTwoSortedArrays {

    private static final class Solution {

        public static final void main(String[] args) {

            double result1 = _4_BS_MedianOfTwoSortedArrays.findMedianSortedArrays(new int[] {1,3}, new int[]{2});
            System.out.printf("The median is %s. \n", result1);

        }

    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) {
            throw new IllegalArgumentException("Both inputs must be non-null.");
        }

        double median1 = nums1.length > 0 ? nums1[Math.min(Math.round(Math.abs(nums1.length / 2 + 1)), nums1.length -1)] : 0;
        double median2 = nums2.length > 0 ? nums2[Math.min(Math.abs(Math.round(nums2.length / 2 - 1)), nums2.length -1)] : 0;

        return (median1 + median2) /2;
    }

    class Solution2 {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            if (nums1 == null || nums2 == null || nums1.length + nums2.length == 0) {
                throw new IllegalArgumentException("Both inputs must be non-null.");
            }

            int len1 = nums1.length;
            int len2 = nums2.length;
            int sum =  len1 + len2;
            if (sum == 1) {
                return len1 == 1 ? nums1[0] * 1.0 : nums2[0] * 1.0;
            }

            int medianIndex = sum >> 1;
            int lowerIndex = sum % 2 == 0 ? medianIndex - 1 : medianIndex;
            //System.out.printf("Sum: %d, median: %d, lower: %d\n", sum, medianIndex, lowerIndex);
            int median = 0;
            int lower = 0;

            for (int a = 0, b = 0; (a+b) <= medianIndex; ) {
                if (a < nums1.length) {
                    if (b < nums2.length) {
                        median = (nums1[a] <= nums2[b]) ? nums1[a++] :nums2[b++];
                    } else {
                        median = nums1[a++];
                    }
                } else {
                    median = nums2[b++];
                }

                if ((a+b)-1 == lowerIndex) {
                    lower = median;
                }
            }

            return (lower + median) / 2.0;
        }
    }

}
