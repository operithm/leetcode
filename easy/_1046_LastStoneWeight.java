package leetcode.easy;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/**
 * 1046. Last Stone Weight
 * Easy
 *
 * You are given an array of integers stones where stones[i] is the weight of the ith stone.
 *
 * We are playing a game with the stones. On each turn, we choose the heaviest two stones and smash them together. Suppose the heaviest two stones
 * have weights x and y with x <= y. The result of this smash is:
 *
 * If x == y, both stones are destroyed, and
 * If x != y, the stone of weight x is destroyed, and the stone of weight y has new weight y - x.
 * At the end of the game, there is at most one stone left.
 *
 * Return the smallest possible weight of the left stone. If there are no stones left, return 0.
 *
 * Example 1:
 *
 * Input: stones = [2,7,4,1,8,1]
 * Output: 1
 * Explanation:
 * We combine 7 and 8 to get 1 so the array converts to [2,4,1,1,1] then,
 * we combine 2 and 4 to get 2 so the array converts to [2,1,1,1] then,
 * we combine 2 and 1 to get 1 so the array converts to [1,1,1] then,
 * we combine 1 and 1 to get 0 so the array converts to [1] then that's the value of the last stone.
 *
 * Example 2:
 *
 * Input: stones = [1]
 * Output: 1
 *
 * Constraints:
 *
 * 1 <= stones.length <= 30
 * 1 <= stones[i] <= 1000
 *
 * Success
 * Details
 * Runtime: 5 ms, faster than 5.66% of Java online submissions for Last Stone Weight.
 * Memory Usage: 41.5 MB, less than 51.89% of Java online submissions for Last Stone Weight.
 *
 * Next challenges:
 *
 * Sparse Matrix Multiplication
 * Arithmetic Slices
 * The Maze II
 */
public class _1046_LastStoneWeight {

    class Solution {
        public int lastStoneWeight(int[] stones) {
            if (stones == null || stones.length < 1) {
                return 0;
            }
            if (stones.length == 1) {
                return stones[0];
            }

            PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
            queue.addAll(Arrays.stream(stones).boxed().collect(Collectors.toList()));

            while(!queue.isEmpty() && queue.size() >=2) {
                int a = queue.remove();
                int b = queue.remove();
                if (b < a) {
                    queue.add(a-b);
                }
            }

            return queue.isEmpty() ?  0 : queue.peek();

        }
    }
}
