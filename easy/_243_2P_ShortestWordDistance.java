package leetcode.easy;

/**
 * Success
 * Details
 * Runtime: 6 ms, faster than 13.90% of Java online submissions for Shortest Word Distance.
 * Memory Usage: 44.6 MB, less than 51.77% of Java online submissions for Shortest Word Distance.
 * Next challenges:
 * Shortest Word Distance II
 * Shortest Word Distance III
 * Find All K-Distant Indices in an Array
 *
 * 243. Shortest Word Distance
 * Easy
 *
 * 1113
 *
 * 97
 *
 * Add to List
 *
 * Share
 * Given an array of strings wordsDict and two different strings that already exist in the array word1 and word2, return the shortest distance between these two words in the list.
 *
 *
 *
 * Example 1:
 *
 * Input: wordsDict = ["practice", "makes", "perfect", "coding", "makes"], word1 = "coding", word2 = "practice"
 * Output: 3
 * Example 2:
 *
 * Input: wordsDict = ["practice", "makes", "perfect", "coding", "makes"], word1 = "makes", word2 = "coding"
 * Output: 1
 *
 *
 * Constraints:
 *
 * 2 <= wordsDict.length <= 3 * 104
 * 1 <= wordsDict[i].length <= 10
 * wordsDict[i] consists of lowercase English letters.
 * word1 and word2 are in wordsDict.
 * word1 != word2
 *
 */
public class _243_2P_ShortestWordDistance {

    class Solution {
        public int shortestDistance(String[] wordsDict, String word1, String word2) {
            if (wordsDict == null || word1.equals(word2)) {
                return 0;
            }

            int ls1 = -1;
            int ls2 = -1;
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < wordsDict.length; i++) {
                if (wordsDict[i].equals(word1)) {
                    ls1 = i;
                    if (ls2 != -1) {
                        min = Math.min(min, Math.abs(ls1 - ls2));
                    }
                }
                if (wordsDict[i].equals(word2)) {
                    ls2 = i;
                    if (ls1 != -1) {
                        min = Math.min(min, Math.abs(ls1 - ls2));
                    }
                }
            }

            return min;
        }
    }

}
