package leetcode.medium;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Success
 * Details
 * Runtime: 6 ms, faster than 91.37% of Java online submissions for Evaluate Reverse Polish Notation.
 * Memory Usage: 42.1 MB, less than 91.01% of Java online submissions for Evaluate Reverse Polish Notation.
 * Next challenges:
 * Basic Calculator
 * Expression Add Operators
 *
 * 150. Evaluate Reverse Polish Notation
 * Medium
 *
 * 3859
 *
 * 688
 *
 * Add to List
 *
 * Share
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 *
 * Valid operators are +, -, *, and /. Each operand may be an integer or another expression.
 *
 * Note that division between two integers should truncate toward zero.
 *
 * It is guaranteed that the given RPN expression is always valid. That means the expression would always evaluate to a result, and there will not be any division by zero operation.
 *
 *
 *
 * Example 1:
 *
 * Input: tokens = ["2","1","+","3","*"]
 * Output: 9
 * Explanation: ((2 + 1) * 3) = 9
 * Example 2:
 *
 * Input: tokens = ["4","13","5","/","+"]
 * Output: 6
 * Explanation: (4 + (13 / 5)) = 6
 * Example 3:
 *
 * Input: tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
 * Output: 22
 * Explanation: ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 * = ((10 * (6 / (12 * -11))) + 17) + 5
 * = ((10 * (6 / -132)) + 17) + 5
 * = ((10 * 0) + 17) + 5
 * = (0 + 17) + 5
 * = 17 + 5
 * = 22
 *
 *
 * Constraints:
 *
 * 1 <= tokens.length <= 104
 * tokens[i] is either an operator: "+", "-", "*", or "/", or an integer in the range [-200, 200].
 */
public class _150_Stack_EvaluateReversePolishNotation {

    class Solution {
        public int evalRPN(String[] tokens) {
            if (tokens == null || tokens.length < 1) {
                return 0;
            }
            var operators = getOperatorSet();
            var output = new Stack<String>();
            for (String token : tokens) {
                if (!operators.contains(token)) {
                    output.push(token);
                } else {
                    int b = Integer.parseInt(output.pop());
                    int a = Integer.parseInt(output.pop());
                    output.push(
                            String.valueOf(eval(a, b, token)));
                }
            }
            return Integer.parseInt(output.pop());
        }

        /**
         *
         * @return
         */
        private Set<String> getOperatorSet() {
            var operators = new HashSet<String>();
            operators.add("+");
            operators.add("-");
            operators.add("*");
            operators.add("/");
            return operators;
        }

        /**
         *
         * @param a
         * @param b
         * @param operator
         * @return
         */
        private int eval(int a, int b, String operator) {
            char c = operator.charAt(0);
            switch (c) {
                case '+' :
                    return a + b;
                case '-' :
                    return a - b;
                case '*' :
                    return a * b;
                case '/' :
                    return a / b;
                default :
                    return 0;
            }
        }
    }
}
