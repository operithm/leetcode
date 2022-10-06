package leetcode.medium;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k,
 * and nums[i] + nums[j] + nums[k] == 0.
 *
 * Notice that the solution set must not contain duplicate triplets.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [-1,0,1,2,-1,-4]
 * Output: [[-1,-1,2],[-1,0,1]]
 * Example 2:
 *
 * Input: nums = []
 * Output: []
 * Example 3:
 *
 * Input: nums = [0]
 * Output: []
 *
 *
 * Constraints:
 *
 * 0 <= nums.length <= 3000
 * -105 <= nums[i] <= 105
 */
public class _15_2P_3Sum {

    private static class ListComparator<T extends Comparable<T>> implements Comparator<List<T>> {
        @Override
        public int compare(List<T> o1, List<T> o2) {
            for (int i = 0; i < Math.min(o1.size(), o2.size()); i++) {
                int c = o1.get(i).compareTo(o2.get(i));
                if (c != 0) {
                    return c;
                }
            }
            return Integer.compare(o1.size(), o2.size());
        }

    }

    public static class Solution {

        public List<List<Integer>> threeSum(int[] nums) {
            final Set<List<Integer>> triSet = new HashSet<>();
            int len = nums.length;

            Arrays.sort(nums);
            for (int i = 0; i < len - 2; i++) {
                int n1 = -nums[i];

                int low = i+1;
                int high = len - 1;

                while (low < high) {
                    int n2 = nums[low];
                    int n3 = nums[high];

                    int twin = n2 + n3;
                    if (twin == n1) {
                        List<Integer> list = List.of(-n1, n2, n3);
                        triSet.add(list);
                        low++;
                        high--;
                    } else if (twin < n1) {
                        low++;
                    } else {
                        high--;
                    }
                }

            }

            List<List<Integer>> triList = new ArrayList<>();
            triList.addAll(triSet);
            Collections.sort(triList, new ListComparator<>() );

            return triList;

        }

        /**
         *
         * @param nums
         * @return
         */
        private static List<List<Integer>> threeSum1(int[] nums) {
            final List<List<Integer>> triList = new ArrayList<>();

            Map<Integer, List<Set<Integer>>> sumMap = new TreeMap<>();
            int len = nums.length;
            //O(n^2)
            for (int i = 0; i < len - 1; i++) {
                for (int j = i+1; j < len; j++) {
                    Set<Integer> set = new TreeSet<>();
                    set.add(i);
                    set.add(j);
                    put(nums[i] + nums[j], set, sumMap);
                }
            }

            for (int i = 0; i < len; i++) {
                int n1 = -nums[i];
                final int idx = i;
                List<Set<Integer>> lists = get(n1, sumMap);
                if (!lists.isEmpty()) {
                    lists.stream().filter(set -> !set.contains(idx)).forEach(set -> {
                        List<Integer> list = new ArrayList<>();
                        list.add(-n1);
                        list.addAll(set.stream().map(item -> nums[item]).collect(Collectors.toList()));
                        Collections.sort(list);
                        if (!triList.contains(list)) {
                            triList.add(list);
                        }

                    });
                }
            }
            return triList;

        }
    }

    private static void put(Integer key, Set<Integer> value, Map<Integer, List<Set<Integer>>> map) {
        List<Set<Integer>> list = map.getOrDefault(key, new ArrayList<>());
        list.add(value);
        map.put(key, list);
    }

    private static List<Set<Integer>> get(Integer key, Map<Integer, List<Set<Integer>>> map) {
        return map.getOrDefault(key, new ArrayList<>());
    }
}
