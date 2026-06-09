package neetcode.stack.p0020_validparentheses;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Solution {
    /**
     * Returns true if the string of brackets is valid:
     * every opening bracket must be closed by the same type
     * in the correct order.
     *
     * Approach: LIFO stack.
     * - Push opening brackets onto the stack.
     * - For each closing bracket, check that the top of the stack
     *   is the matching opener. If not (or the stack is empty), invalid.
     * - At the end, the stack must be empty (no unclosed openers).
     *
     * Time:  O(n)
     * Space: O(n) — worst case all openers, e.g. "((((("
     */
    public boolean isValid(String s) {
        Map<Character, Character> pairs = new HashMap<>();
        pairs.put(')', '(');
        pairs.put(']', '[');
        pairs.put('}', '{');

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else if (c == ')' || c == ']' || c == '}') {
                if (stack.isEmpty() || stack.peek() != pairs.get(c)) {
                    return false;
                }
                stack.pop();
            }
        }

        return stack.isEmpty();
    }
}
