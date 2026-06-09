package neetcode.stack.p0155_minstack;

import java.util.Stack;

/**
 * A stack that supports push, pop, top, and getMin in O(1) time.
 *
 * Approach: two parallel stacks.
 * - {@code stack} holds all values in insertion order.
 * - {@code minStack} holds the running minimum at each stack level:
 *   {@code minStack.peek()} is always the minimum of all current elements.
 *
 * When pushing, the new minimum is min(val, current minimum).
 * When popping, both stacks are popped together — the level below already
 * has the correct minimum recorded for the remaining elements.
 *
 * Time:  O(1) for all operations
 * Space: O(n) — minStack mirrors the main stack in size
 */
public class MinStack {

    private final Stack<Integer> stack;
    private final Stack<Integer> minStack;

    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int val) {
        stack.push(val);
        if (minStack.isEmpty()) {
            minStack.push(val);
        } else {
            minStack.push(Math.min(val, minStack.peek()));
        }
    }

    public void pop() {
        stack.pop();
        minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
