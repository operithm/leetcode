package leetcode.medium;

import java.util.Arrays;
import java.util.Comparator;

/**
 * A company is planning to interview 2n people. Given the array costs where costs[i] = [aCosti, bCosti], the cost of flying the ith person to city a is aCosti, and the cost of flying the ith person to city b is bCosti.
 *
 * Return the minimum cost to fly every person to a city such that exactly n people arrive in each city.
 *
 *
 *
 * Example 1:
 *
 * Input: costs = [[10,20],[30,200],[400,50],[30,20]]
 * Output: 110
 * Explanation:
 * The first person goes to city A for a cost of 10.
 * The second person goes to city A for a cost of 30.
 * The third person goes to city B for a cost of 50.
 * The fourth person goes to city B for a cost of 20.
 *
 * The total minimum cost is 10 + 30 + 50 + 20 = 110 to have half the people interviewing in each city.
 * Example 2:
 *
 * Input: costs = [[259,770],[448,54],[926,667],[184,139],[840,118],[577,469]]
 * Output: 1859
 */
public class _1029_2P_TwoCitySchedule {

    private static class ArrayComparator implements Comparator<int[]> {
        @Override
        public int compare(int[] one, int[] other) {
            if (one == other) {
                return 0;
            }
            return -1 * Integer.valueOf(Math.abs(one[0] - one[1])).compareTo(Math.abs(other[0] - other[1]));
        }
    }

    class Solution {
        public int twoCitySchedCost(int[][] costs) {
            Arrays.sort(costs, new ArrayComparator());

            int n = costs.length / 2;
            int a = 0;
            int b = 0;
            long total = 0L;
            int i = 0;

            while (a < n && b < n && i < costs.length) {
                if (costs[i][0] < costs[i][1]) {
                    a++;
                    total += costs[i][0];
                } else {
                    b++;
                    total += costs[i][1];
                }
                i++;
            }

            for (; i < costs.length; i++) {
                if (a == n) {
                    total += costs[i][1];
                } else {
                    total += costs[i][0];
                }
            }

            return (int) total;
        }
    }
}
