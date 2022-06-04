package leetcode;

public class MedianOfTwoSortedArrays {

    private static final class Solution {

        public static final void main(String[] args) {

            double result1 = MedianOfTwoSortedArrays.findMedianSortedArrays(new int[] {1,3}, new int[]{2});
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

}
