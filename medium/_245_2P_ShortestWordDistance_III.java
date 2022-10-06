package leetcode.medium;

/**
 * Success
 * Details
 * Runtime: 27 ms, faster than 28.42% of Java online submissions for Shortest Word Distance III.
 * Memory Usage: 70.5 MB, less than 72.60% of Java online submissions for Shortest Word Distance III.
 * Next challenges:
 * Shortest Word Distance II
 *
 * 245. Shortest Word Distance III
 * Medium
 *
 * 378
 *
 * 90
 *
 * Add to List
 *
 * Share
 * Given an array of strings wordsDict and two strings that already exist in the array word1 and word2, return the shortest distance between these two words in the list.
 *
 * Note that word1 and word2 may be the same. It is guaranteed that they represent two individual words in the list.
 *
 *
 *
 * Example 1:
 *
 * Input: wordsDict = ["practice", "makes", "perfect", "coding", "makes"], word1 = "makes", word2 = "coding"
 * Output: 1
 * Example 2:
 *
 * Input: wordsDict = ["practice", "makes", "perfect", "coding", "makes"], word1 = "makes", word2 = "makes"
 * Output: 3
 *
 *
 * Constraints:
 *
 * 1 <= wordsDict.length <= 105
 * 1 <= wordsDict[i].length <= 10
 * wordsDict[i] consists of lowercase English letters.
 * word1 and word2 are in wordsDict.
 *
 */
public class _245_2P_ShortestWordDistance_III {

    class Solution {
        public int shortestWordDistance(String[] wordsDict, String word1, String word2) {
            if (wordsDict == null) {
                return 0;
            }

            if (word1.equals(word2)) {
                return minDistance(wordsDict, word1);
            } else {
                return minDistance(wordsDict, word1, word2);
            }

        }

        private int minDistance(String[] wordsDict, String word) {
            int ls = -1;
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < wordsDict.length; i++) {
                if (wordsDict[i].equals(word)) {
                    if (ls != -1) {
                        min = Math.min(min, i - ls);
                    }
                    ls = i;
                }
            }
            return min;
        }

        private int minDistance(String[] wordsDict, String word1, String word2) {
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
