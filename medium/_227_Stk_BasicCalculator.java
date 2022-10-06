package leetcode.medium;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 227. Basic Calculator II
 * Medium
 *
 * 4526
 *
 * 588
 *
 * Add to List
 *
 * Share
 * Given a string s which represents an expression, evaluate this expression and return its value.
 *
 * The integer division should truncate toward zero.
 *
 * You may assume that the given expression is always valid. All intermediate results will be in the range of [-231, 231 - 1].
 *
 * Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().
 *
 *
 *
 * Example 1:
 *
 * Input: s = "3+2*2"
 * Output: 7
 * Example 2:
 *
 * Input: s = " 3/2 "
 * Output: 1
 * Example 3:
 *
 * Input: s = " 3+5 / 2 "
 * Output: 5
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 3 * 105
 * s consists of integers and operators ('+', '-', '*', '/') separated by some number of spaces.
 * s represents a valid expression.
 * All the integers in the expression are non-negative integers in the range [0, 231 - 1].
 * The answer is guaranteed to fit in a 32-bit integer.
 *
 * Success
 * Details
 * Runtime: 1535 ms, faster than 5.09% of Java online submissions for Basic Calculator II.
 * Memory Usage: 96.3 MB, less than 5.01% of Java online submissions for Basic Calculator II.
 * Next challenges:
 * Basic Calculator
 * Expression Add Operators
 * Basic Calculator III
 */
public class _227_Stk_BasicCalculator {

    class Solution {
        public int calculate(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }

            Stack<Character> stack = new Stack<>();
            LinkedList<Integer> queue = new LinkedList<>();
            Queue<Character> chars = new LinkedList<>();
            s = s.replace(" ","");
            for (char c : s.toCharArray()) {
                chars.offer(c);
            }

            String number = "";
            while(!chars.isEmpty()) {
                char c = chars.poll();
                /**
                 * Parsing continuous digit -> number
                 */
                if (Character.isDigit(c)) {
                    number += c;
                    if (!chars.isEmpty()) {
                        continue;
                    } else {
                        queue.offer(Integer.parseInt(number));
                    }

                }

                if(!Character.isDigit(c)) {
                    if (!number.equals("") ) {
                        queue.offer(Integer.parseInt(number));
                        number = "";
                    }

                    if (stack.isEmpty()) {
                        stack.push(c);
                        continue;
                    } else {
                        peekAndPush(queue, stack, c);
                    }
                }

            }

            int result = 0;
            while(!stack.isEmpty()) {
                char op = stack.pop();
                result = calculate(queue, op);
                //System.out.printf("op: %s, result %s, size: %s\n", op, result, stack.size());
                queue.offer(result);
            }

            return queue.poll();
        }

        /**
         *
         */
        private void peekAndPush(LinkedList<Integer> queue, Stack<Character> stack, char c) {
            char peek = stack.peek();
            if ( precedence(peek)  >= precedence(c)) {
                int cal = calculate(queue, peek);
                queue.offer(cal);
                stack.pop();
                if (!stack.isEmpty()) {
                    peekAndPush(queue, stack, c);
                } else {
                    stack.push(c);
                }
            } else {
                stack.push(c);
            }

        }

        private int precedence(char op) {
            switch (op)  {
                case '+' :
                    return 0;
                case '-' :
                    return 0;
                case '*' :
                    return 1;
                case '/' :
                    return 1;
                default :
                    return 0;
            }
        }

        private int calculate(LinkedList<Integer> queue, char op) {
            System.out.printf("queue: %s, op: %s\n", queue, op);
            int n2 = !queue.isEmpty() ? queue.removeLast() : 0;
            int n1 = !queue.isEmpty() ? queue.removeLast() : 0;
            switch (op)  {
                case '+' :
                    return n1 + n2;
                case '-' :
                    return n1 - n2;
                case '*' :
                    return n1 * n2;
                case '/' :
                    if (n2 == 0) {
                        throw new IllegalArgumentException("divided by zero error");
                    }
                    return n1 / n2;
                default :
                    return 0;
            }
        }
    }
}
