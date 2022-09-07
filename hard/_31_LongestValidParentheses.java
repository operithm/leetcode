package leetcode.hard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "(()"
 * Output: 2
 * Explanation: The longest valid parentheses substring is "()".
 * Example 2:
 *
 * Input: s = ")()())"
 * Output: 4
 * Explanation: The longest valid parentheses substring is "()()".
 * Example 3:
 *
 * Input: s = ""
 * Output: 0
 *
 *
 * Constraints:
 *
 * 0 <= s.length <= 3 * 104
 * s[i] is '(', or ')'.
 */
public class _31_LongestValidParentheses {

    private static class Segment implements Comparable<Segment> {
        final int a;
        final int b;

        public Segment(int a, int b) {
            this.a = Math.min(a, b);
            this.b = Math.max(a, b);
        }

        public int length() {
            return b - a + 1;
        }

        public boolean contains(Segment other) {
            return this.a <= other.a && this.b >= other.b;
        }

        public boolean connects(Segment other) {
            return this.b + 1 == other.a;
        }


        public Segment union(Segment other) {
            if (this.contains(other)) {
                return this;
            } else if (this.connects(other)){
                return this.joint(other);
            } else {
                return this;
            }
        }

        public Segment joint(Segment other) {
            if(this.connects(other)) {
                return new Segment(this.a, other.b);
            } else {
                return null;
            }
        }

        @Override
        public int compareTo(Segment other) {
            return Integer.valueOf(a).compareTo(other.a);
        }

        @Override
        public String toString() {
            return String.format("[%s, %s]", a, b);
        }
    }

    public int longestValidParentheses(String s) {
        if (s == null || s.length() < 1) {
            return 0;
        }
        int len = s.length();

        List<Segment> segments = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == ')') {
                if(!stack.isEmpty()) {
                    segments.add(new Segment(stack.pop(), i));
                }
            } else {
                stack.push(i);
            }
        }

        Collections.sort(segments);
        if (segments.isEmpty()) {
            return 0;
        }

        //System.out.printf("size = %s\n", segments.size());
        Segment current = segments.remove(0);
        int maxLen = current.length();
        //System.out.printf("current len: %s\n", maxLen);

        while (!segments.isEmpty()) {
            //System.out.printf("current: %s\n", current);
            Segment first = segments.remove(0);
            if (current.contains(first) || current.connects(first)) {
                current = current.union(first);
                if (maxLen < current.length()) {
                    maxLen = current.length();
                }
            } else {
                current = first;
            }
        }
        return Math.max(maxLen, current.length());
    }
    
}

/**
 * NOTE:
 *
 * The solution can be simplify to one pass O(n) as following:
 *
 * 1. Push the index of "(" into stack
 * 2. When a ")" is seen, pop an integer from the stack if exists.
 * 3. Diff of index(")") - index("(") + 1 is the current well formed parenthesis length
 * 4. Compare the length from 3 with the global one
 * 5. Return the global max
 */
