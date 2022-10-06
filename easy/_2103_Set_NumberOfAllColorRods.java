package leetcode.easy;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * 2103. Rings and Rods
 * Easy
 *

 * There are n rings and each ring is either red, green, or blue. The rings are distributed across ten rods labeled from 0 to 9.
 *
 * You are given a string rings of length 2n that describes the n rings that are placed onto the rods. Every two characters in
 * rings forms a color-position pair that is used to describe each ring where:
 *
 * The first character of the ith pair denotes the ith ring's color ('R', 'G', 'B').
 * The second character of the ith pair denotes the rod that the ith ring is placed on ('0' to '9').
 * For example, "R3G2B1" describes n == 3 rings: a red ring placed onto the rod labeled 3, a green ring placed onto the rod labeled 2,
 * and a blue ring placed onto the rod labeled 1.
 *
 * Return the number of rods that have all three colors of rings on them.
 *
 * Example 1:
 *
 * Input: rings = "B0B6G0R6R0R6G9"
 * Output: 1
 * Explanation:
 * - The rod labeled 0 holds 3 rings with all colors: red, green, and blue.
 * - The rod labeled 6 holds 3 rings, but it only has red and blue.
 * - The rod labeled 9 holds only a green ring.
 * Thus, the number of rods with all three colors is 1.
 * Example 2:
 *
 *
 * Input: rings = "B0R0G0R9R0B0G0"
 * Output: 1
 * Explanation:
 * - The rod labeled 0 holds 6 rings with all colors: red, green, and blue.
 * - The rod labeled 9 holds only a red ring.
 * Thus, the number of rods with all three colors is 1.
 * Example 3:
 *
 * Input: rings = "G4"
 * Output: 0
 * Explanation:
 * Only one ring is given. Thus, no rods have all three colors.
 *
 *
 * Constraints:
 *
 * rings.length == 2 * n
 * 1 <= n <= 100
 * rings[i] where i is even is either 'R', 'G', or 'B' (0-indexed).
 * rings[i] where i is odd is a digit from '0' to '9' (0-indexed).
 *
 * Success
 * Details
 * Runtime: 4 ms, faster than 20.09% of Java online submissions for Rings and Rods.
 * Memory Usage: 40.5 MB, less than 79.69% of Java online submissions for Rings and Rods.
 * Next challenges:
 * Check if All Characters Have Equal Number of Occurrences
 */
public class _2103_Set_NumberOfAllColorRods {

    class Solution {
        public int countPoints(String rings) {
            if (rings == null || rings.length() < 6) {
                return 0;
            }
            Map<Character, Set<Character>> rodMap = new TreeMap<>();
            int len = rings.length();
            //System.out.printf("len: %s\n", len);
            for (int i = 0; i < rings.length(); i+= 2) {
                char r = rings.charAt(i);
                if (i+1 < len) {
                    char c = rings.charAt(i+1);
                    Set<Character> set = rodMap.getOrDefault(c, new TreeSet<>());
                    set.add(r);
                    rodMap.put(c, set);
                    //System.out.printf("i: %s, r: %s, c: %s, map: %s\n", i, r, c, String.valueOf(rodMap));
                }
            }

            return rodMap.values().stream()
                    .filter(set -> set.size() == 3)
                    .map(set -> 1).reduce(0, Integer::sum);

        }
    }
}
