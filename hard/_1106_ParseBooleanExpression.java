package leetcode.hard;

import java.util.Stack;

/**
 * 1106. Parsing A Boolean Expression
 * Hard
 *
 * 619
 *
 * 35
 *
 * Add to List
 *
 * Share
 * Return the result of evaluating a given boolean expression, represented as a string.
 *
 * An expression can either be:
 *
 * "t", evaluating to True;
 * "f", evaluating to False;
 * "!(expr)", evaluating to the logical NOT of the inner expression expr;
 * "&(expr1,expr2,...)", evaluating to the logical AND of 2 or more inner expressions expr1, expr2, ...;
 * "|(expr1,expr2,...)", evaluating to the logical OR of 2 or more inner expressions expr1, expr2, ...
 *
 *
 * Example 1:
 *
 * Input: expression = "!(f)"
 * Output: true
 * Example 2:
 *
 * Input: expression = "|(f,t)"
 * Output: true
 * Example 3:
 *
 * Input: expression = "&(t,f)"
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= expression.length <= 2 * 104
 * expression[i] consists of characters in {'(', ')', '&', '|', '!', 't', 'f', ','}.
 * expression is a valid expression representing a boolean, as given in the description.
 *
 * Success
 * Details
 * Runtime: 2131 ms, faster than 5.38% of Java online submissions for Parsing A Boolean Expression.
 * Memory Usage: 118.1 MB, less than 5.38% of Java online submissions for Parsing A Boolean Expression.
 * Next challenges:
 * Fraction Addition and Subtraction
 * Minimum Distance to Type a Word Using Two Fingers
 * Longest Subsequence Repeated k Times
 */
public class _1106_ParseBooleanExpression {

    private static class Solution {
        public boolean parseBoolExpr(String expression) {
            if (expression == null || expression.isEmpty()) {
                return false;
            }

            //add an end char
            String exp = expression + "$";
            Stack<Character> ops = new Stack<>();
            Stack<Boolean> vals = new Stack<>();

            int i = 0;
            char c = exp.charAt(i);

            while (i < exp.length() && c != '$') {
                c = exp.charAt(i);
                if (c == '|' || c == '&' || c == '!') {
                    ops.push(c);
                }
                if (c == '|') {
                    vals.push(false);
                }
                if (c == '&') {
                    vals.push(true);
                }
                if (c == '(') {}

                if (c == ',') {
                    if (ops.isEmpty() || ops.peek() == '!') {
                        return false;
                    }
                }

                if (c == 't' || c == 'f') {
                    boolean val = c == 't';
                    pushVal(ops, vals, val);
                }

                if (c == ')') {
                    popVal(ops, vals);
                }

                i++;
                System.out.printf("ops: %s, vals: %s\n", String.valueOf(ops), String.valueOf(vals));
            }

            return vals.pop();

        }

        private static void pushVal(Stack<Character> ops, Stack<Boolean> vals, boolean val) {
            char op = ' ';
            Boolean a = null;
            if (!ops.isEmpty()) {
                op = ops.peek();
            }
            if (!vals.isEmpty()) {
                a = vals.peek();
            }
            boolean sameLevel = ops.size() == vals.size();
            if (a == null) {
                vals.push(val);
            } else {
                if (sameLevel) {
                    a = vals.pop();
                    if (op == '|') {
                        vals.push(a | val);
                    } else if(op == '&') {
                        vals.push(a & val);
                    } else {
                        vals.push(val);
                    }
                } else {
                    vals.push(val);
                }
            }
        }

        private static void popVal(Stack<Character> ops, Stack<Boolean> vals) {
            if (!vals.isEmpty()) {
                boolean val = vals.pop();
                if (!ops.isEmpty()) {
                    char op = ops.peek();
                    if (op == '!') {
                        val = !val;
                    }
                    ops.pop();
                }
                pushVal(ops, vals, val);
            }
        }

    }
}
