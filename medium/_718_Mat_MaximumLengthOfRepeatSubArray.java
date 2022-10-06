package leetcode.medium;

import java.util.Collections;
import java.util.LinkedList;
import java.util.TreeMap;

/**
 * Next question
 * 719. Find K-th Smallest Pair Distance
 * More challenges
 * 209. Minimum Size Subarray Sum
 * 1923. Longest Common Subpath
 *
 * 718. Maximum Length of Repeated Subarray
 * Medium
 * 5.7K
 * 142
 * company
 * Citadel
 * Given two integer arrays nums1 and nums2, return the maximum length of a subarray that appears in both arrays.
 *
 *
 *
 * Example 1:
 *
 * Input: nums1 = [1,2,3,2,1], nums2 = [3,2,1,4,7]
 * Output: 3
 * Explanation: The repeated subarray with maximum length is [3,2,1].
 * Example 2:
 *
 * Input: nums1 = [0,0,0,0,0], nums2 = [0,0,0,0,0]
 * Output: 5
 * Explanation: The repeated subarray with maximum length is [0,0,0,0,0].
 *
 *
 * Constraints:
 *
 * 1 <= nums1.length, nums2.length <= 1000
 * 0 <= nums1[i], nums2[i] <= 100
 */
public class _718_Mat_MaximumLengthOfRepeatSubArray {

    class Solution {
        public int findLength(int[] nums1, int[] nums2) {
            if (nums1.length < 1 || nums2.length < 1) {
                return 0;
            }

            int m = nums1.length;
            int n = nums2.length;
            int[][] mat = new int[m][n];
            int max = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (nums1[i] == nums2[j]) {
                        mat[i][j] = 1 + getOrDefault(i-1, j-1, m, n, mat);
                        max = Math.max(max, mat[i][j]);
                    }
                }
            }
            return max;
        }

        private int getOrDefault(int i, int j, int m, int n, int[][] mat) {
            if (i >= 0 && i < m && j >= 0 && j < n) {
                return mat[i][j];
            } else {
                return 0;
            }
        }

        public int findLength1(int[] nums1, int[] nums2) {
            if (nums1.length < 1 || nums2.length < 1) {
                return 0;
            }
            var map = new TreeMap<Integer, LinkedList<Integer>>();
            for (int i = 0; i < nums2.length; i++) {
                var list = map.getOrDefault(nums2[i], new LinkedList<>());
                list.add(i);
                map.put(nums2[i], list);
            }

            int max = 0;
            int n = nums1.length;
            LinkedList<Integer>[] indexList = new LinkedList[n];
            for (int i = 0; i < n; i++) {
                indexList[i] = map.getOrDefault(nums1[i], new LinkedList<>());
            }
            //System.out.printf("list: %s\n", Arrays.toString(indexList));

            for (int i = 0; i < n; i++) {
                var current = indexList[i].iterator();
                while (current.hasNext()) {
                    int count = 1;
                    int v = current.next();
                    int j = i+1;
                    while (j < n) {
                        int nextIndex = Collections.binarySearch(indexList[j], v + 1);
                        //System.out.printf("next: %s\n", String.valueOf(indexList[j]));
                        if (nextIndex >= 0) {
                            v = indexList[j].get(nextIndex);
                            count++;
                            j++;
                        } else {
                            break;
                        }
                    }
                    max = Math.max(max, count);
                }
            }
            return max;
        }
    }

}
