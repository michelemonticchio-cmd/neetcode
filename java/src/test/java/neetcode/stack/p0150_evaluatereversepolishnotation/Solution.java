package neetcode.stack.p0150_evaluatereversepolishnotation;

import java.util.Stack;

public class Solution {
    /**
     * Evaluates an arithmetic expression in Reverse Polish Notation (RPN).
     *
     * Approach: single stack.
     * - Numbers are pushed onto the stack.
     * - Operators pop two operands (b = right, a = left), apply the
     *   operation, and push the result back.
     * - The final value on the stack is the answer.
     *
     * Time:  O(n) — each token is processed once
     * Space: O(n) — worst case all tokens are numbers
     */
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();

        for (String token : tokens) {
            if ("+-*/".contains(token)) {
                int b = stack.pop();   // right operand
                int a = stack.pop();   // left operand
                int result = switch (token) {
                    case "+" -> a + b;
                    case "-" -> a - b;
                    case "*" -> a * b;
                    case "/" -> a / b;
                    default  -> 0;     // unreachable given valid input
                };
                stack.push(result);
            } else {
                stack.push(Integer.parseInt(token));
            }
        }

        return stack.pop();
    }
}
