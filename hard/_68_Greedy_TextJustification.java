package leetcode.hard;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Success
 * Details
 * Runtime: 6 ms, faster than 11.94% of Java online submissions for Text Justification.
 * Memory Usage: 42.5 MB, less than 49.49% of Java online submissions for Text Justification.
 * Next challenges:
 * Rearrange Spaces Between Words
 * Divide a String Into Groups of Size k
 *
 * 68. Text Justification
 * Hard
 *
 * 2006
 *
 * 3197
 *
 * Add to List
 *
 * Share
 * Given an array of strings words and a width maxWidth, format the text such that each line has exactly maxWidth characters and is fully (left and right) justified.
 *
 * You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.
 *
 * Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line does not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.
 *
 * For the last line of text, it should be left-justified, and no extra space is inserted between words.
 *
 * Note:
 *
 * A word is defined as a character sequence consisting of non-space characters only.
 * Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
 * The input array words contains at least one word.
 *
 *
 * Example 1:
 *
 * Input: words = ["This", "is", "an", "example", "of", "text", "justification."], maxWidth = 16
 * Output:
 * [
 *    "This    is    an",
 *    "example  of text",
 *    "justification.  "
 * ]
 * Example 2:
 *
 * Input: words = ["What","must","be","acknowledgment","shall","be"], maxWidth = 16
 * Output:
 * [
 *   "What   must   be",
 *   "acknowledgment  ",
 *   "shall be        "
 * ]
 * Explanation: Note that the last line is "shall be    " instead of "shall     be", because the last line must be left-justified instead of fully-justified.
 * Note that the second line is also left-justified because it contains only one word.
 * Example 3:
 *
 * Input: words = ["Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"], maxWidth = 20
 * Output:
 * [
 *   "Science  is  what we",
 *   "understand      well",
 *   "enough to explain to",
 *   "a  computer.  Art is",
 *   "everything  else  we",
 *   "do                  "
 * ]
 *
 */
public class _68_Greedy_TextJustification {

    static class FittedLine {
        private final List<String> line;
        private final int maxWidth;
        private int size;

        public FittedLine (int maxWidth) {
            this.line = new LinkedList<>();
            this.maxWidth = maxWidth;
        }

        public void add(String word) {
            if (canAdd(word)) {
                line.add(word);
                size += 1 + word.length();
            }
        }

        public boolean canAdd(String word) {
            if (word == null) {
                return false;
            }
            return size + word.length() <= maxWidth;
        }

        public int getSize() {
            return line.size() == 1 ? size - 1 : size;
        }

        /**
         *
         */
        private final static String getSpace(int n) {
            var sb = new StringBuilder("");
            while(n-- > 0) {
                sb.append(" ");
            }
            return sb.toString();
        }

        /**
         *
         */
        private List<String> allocateSpace(int space, int count) {
            int n = space / count ;
            int m = space % count;
            //System.out.printf("space: %s, count: %s\n", space, count);
            int[] nums = new int[count];
            Arrays.fill(nums, n);
            for (int i = 0; i < m; i++) {
                nums[i]++;
            }
            var spaces = new LinkedList<String>();
            /**
             * TODO: map a int -> String from primitive stream
             */
            Arrays.stream(nums).forEach(num -> spaces.add(getSpace(num)));
            return spaces;
        }

        /**
         *
         */
        public String leftAligned() {
            var sb = new StringBuilder("");
            var copy = new LinkedList<>(line);
            while (!copy.isEmpty()) {
                sb.append(copy.remove(0));
                sb.append(" ");
            }

            var result = sb.toString().trim();
            return result + getSpace(maxWidth - result.length());
        }

        /**
         *
         */
        public String fullAligned() {
            int count = line.size() - 1;
            //System.out.printf("size: %s\n", size);
            if (count != 0) {
                int space = maxWidth - (size - 1) + count;

                var spaces = allocateSpace(space, count);
                var copy = new LinkedList<>(line);
                var sb = new StringBuilder("");
                while (!spaces.isEmpty() || !copy.isEmpty()) {
                    if (!copy.isEmpty()) {
                        sb.append(copy.remove(0));
                    }
                    if (!spaces.isEmpty()) {
                        sb.append(spaces.remove(0));
                    }
                }
                return sb.toString();
            }
            return leftAligned();
        }

        @Override
        public String toString() {
            return fullAligned();
        }
    }

    class Solution {
        public List<String> fullJustify(String[] words, int maxWidth) {
            if (words == null || words.length < 1 || maxWidth <= 0) {
                return new LinkedList<>();
            }

            var output = new LinkedList<String>();
            var line = new FittedLine(maxWidth);

            int len = words.length;
            for (int i = 0; i < len; i ++) {
                String word = words[i];
                if (!line.canAdd(word)) {
                    output.add(line.toString());
                    line = new FittedLine(maxWidth);
                }
                line.add(word);
            }

            output.add(line.leftAligned());
            return output;
        }

    }
}
