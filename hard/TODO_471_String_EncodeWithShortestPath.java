package leetcode.hard;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 471. Encode String with Shortest Length
 * Hard
 *
 * 577
 *
 * 41
 *
 * Add to List
 *
 * Share
 * Given a string s, encode the string such that its encoded length is the shortest.
 *
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. k should be a positive integer.
 *
 * If an encoding process does not make the string shorter, then do not encode it. If there are several solutions, return any of them.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aaa"
 * Output: "aaa"
 * Explanation: There is no way to encode it such that it is shorter than the input string, so we do not encode it.
 * Example 2:
 *
 * Input: s = "aaaaa"
 * Output: "5[a]"
 * Explanation: "5[a]" is shorter than "aaaaa" by 1 character.
 * Example 3:
 *
 * Input: s = "aaaaaaaaaa"
 * Output: "10[a]"
 * Explanation: "a9[a]" or "9[a]a" are also valid solutions, both of them have the same length = 5, which is the same as "10[a]".
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 150
 * s consists of only lowercase English letters.
 * Accepted
 * 28,709
 * Submissions
 * 56,577
 * Seen this question in a real interview before?
 *
 * Yes
 *
 * No
 */
public class TODO_471_String_EncodeWithShortestPath {

    private static class Code {
        private String id;
        private int count;

        public Code(String id) {
            this.id = id;
            this.count = 1;
        }

        public boolean canAppend(Code a) {
            return a != null &&
                    (id.equals(a.toString()) || (count == 1 && a.count == 1));
        }

        public Code append(Code a) {
            if (a != null) {
                String aStr = a.toString();
                System.out.printf("comparing: id: %s, aStr: %s\n", id, aStr);
                if (this.id.equals(aStr)) {
                    this.count++;
                } else {
                    if (this.count == 1) {
                        return new Code(id + aStr);
                    }
                }
            }
            return this;
        }

        private void split() {
            int len = id.length();
            if (len >= 6 && len % 2 == 0) {
                int mid = len / 2;

                if (id.substring(0, mid).equals(id.substring(mid))) {
                    count = count << 1;
                    //id = id.substring()
                }
            }
        }

        public String encode() {
            return String.format("%d[%s]", count, id);
        }

        public String decode() {
            StringBuilder sb = new StringBuilder("");
            int total = count;
            while (total > 0) {
                sb.append(id);
                total--;
            }
            return sb.toString();
        }

        @Override
        public String toString() {
            return encode().length() < decode().length() ? encode() : decode();
        }

    }

    class Solution {


        public String encode(String s) {
            if (s == null) {
                return s;
            }

            String sub = s.substring(0, 1);
            s = s.substring(1);

            Code code = new Code(sub);
            Queue<Code> parent = new LinkedList<>();
            Code next = null;

            while (s.length() > 0) {
                sub = s.substring(0, 1);
                s = s.substring(1);
                next = new Code(sub);
                if (code.canAppend(next)) {
                    code = code.append(next);
                    //next = null;
                } else {
                    parent.add(code);
                    code = next;
                }
            }

            if (next != null) {
                parent.add(next);
            }

            System.out.printf("p: %s\n", parent.size());
            Queue<Code> children = new LinkedList<>();
            Code node = null;

            while (!parent.isEmpty()) {
                children.addAll(parent);
                parent.clear();

                node = new Code(children.remove().toString());

                while (!children.isEmpty()) {
                    Code peek = children.peek();
                    if (node.canAppend(peek)) {
                        node.append(peek);
                        children.remove();
                    } else {
                        parent.add(node);
                        node = new Code(children.remove().toString());
                    }
                }
                //parent.add(node);
                System.out.printf("children: %s\n", children.size());
            }

            return node == null ? null : node.toString();
        }
    }
}
