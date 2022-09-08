package leetcode.hard;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 527. Word Abbreviation
 * Hard
 *
 * 352
 *
 * 265
 *
 * Add to List
 *
 * Share
 * Given an array of distinct strings words, return the minimal possible abbreviations for every word.
 *
 * The following are the rules for a string abbreviation:
 *
 * The initial abbreviation for each word is: the first character, then the number of characters in between, followed by the last character.
 * If more than one word shares the same abbreviation, then perform the following operation:
 * Increase the prefix (characters in the first part) of each of their abbreviations by 1.
 * For example, say you start with the words ["abcdef","abndef"] both initially abbreviated as "a4f". Then, a sequence of operations would be ["a4f","a4f"] -> ["ab3f","ab3f"] -> ["abc2f","abn2f"].
 * This operation is repeated until every abbreviation is unique.
 * At the end, if an abbreviation did not make a word shorter, then keep it as the original word.
 *
 *
 * Example 1:
 *
 * Input: words = ["like","god","internal","me","internet","interval","intension","face","intrusion"]
 * Output: ["l2e","god","internal","me","i6t","interval","inte4n","f2e","intr4n"]
 * Example 2:
 *
 * Input: words = ["aa","aaa"]
 * Output: ["aa","aaa"]
 *
 *
 * Constraints:
 *
 * 1 <= words.length <= 400
 * 2 <= words[i].length <= 400
 * words[i] consists of lowercase English letters.
 * All the strings of words are unique.
 *
 */
public class TODO_527_String_WordAbbreviation {

    private static class Node implements Comparable<Node> {
        final char val;
        List<Node> next;

        public Node(char val) {
            this.val = val;
        }

        public void addNext(Node node) {
            if (next == null) {
                next = new LinkedList<>();
            }

            if (!next.contains(node)) {
                next.add(node);
            }
        }

        public List<Node> getNext() {
            return next;
        }

        public Node findNext(Node node) {
            if (next == null) {
                return null;
            }
            for (Node n : next) {
                if (n.val == node.val) {
                    return n;
                }
            }
            return null;
        }

        public int nextSize() {
            if (next == null || next.isEmpty()) {
                return 0;
            }
            return next.size();
        }

        @Override
        public int compareTo(Node other) {
            return val - other.val;
        }

        @Override
        public boolean equals(Object other) {
            if (!(other instanceof Node)) {
                return false;
            }
            Node otherNode = (Node) other;
            return val == otherNode.val;
        }

        @Override
        public int hashCode() {
            return 31 * val;
        }

    }

    class Solution {

        public List<String> wordsAbbreviation(List<String> words) {
            if (words == null || words.isEmpty()) {
                return List.of();
            }

            Map<Character, Node> map = new LinkedHashMap<>();
            for (String word : words) {
                char r = word.charAt(0);
                Node tree = map.getOrDefault(r, makeTree(null, word));
                map.put(r, tree);
            }

            System.out.printf("map: %s\n", map);
            List<String> result = new LinkedList<>();
            for (String word : words) {
                result.add(makeAbbreviation(map.get(word.charAt(0)), word));
            }

            return result;
        }


        private Node makeTree(Node root, String str) {
            char[] arr = str.toCharArray();
            Node current = root;
            //System.out.printf("word: %s\n", str);
            for (char c : arr) {
                Node node = new Node(c);
                if (current == null) {
                    root = node;
                    current = node;
                    continue;
                } else {
                    current.addNext(node);
                    current = current.findNext(node);
                    //System.out.printf("c: %s, node: %s, current: %s\n", c, node.val, current.val);
                }
            }

            return root;
        }

        private String makeAbbreviation(Node node, String str) {
            String prefix = "";
            String suffix = "" + node.val;

            for (int i = 1; i < str.length(); i++) {
                System.out.printf("str: %s, node: %s, i: %s\n", str, node, i);
                Node n = new Node(str.charAt(i));
                int nextSize = node.nextSize();
                node = node.findNext(n);
                /**
                 */
                if (nextSize > 1) {
                    prefix += suffix;
                    suffix = "";
                }
                if (node != null) {
                    suffix += node.val;
                }
            }
            return prefix + shorten(suffix);
        }

        /**
         */
        private String shorten(String str) {
            int len = str.length();
            if (len >= 4) {
                return "" + str.charAt(0) + (len-2) + str.charAt(len-1);
            }
            return str;
        }
    }
}
