# 20. Valid Parentheses — Python

🔗 [LeetCode](https://leetcode.com/problems/valid-parentheses/) | 📺 [NeetCode](https://neetcode.io/problems/validate-parentheses)

**Difficulty:** Easy
**Category:** Stack

## Problem

Given a string of brackets `()[]{}`, return `True` if every opening
bracket is closed by the same type in the correct order.

## Approach

**LIFO stack.**

- Opening bracket → push onto stack.
- Closing bracket → check top of stack is the matching opener.
  If stack is empty or top doesn't match → `False`.
- End: stack must be empty (no unclosed openers).

A dict `{')':'(', ']':'[', '}':'{'}` maps each closer to its opener
for an O(1) match check.

## Complexity

- **Time:** O(n)
- **Space:** O(n)

## Java vs Python

    // Java
    Stack<Character> stack = new Stack<>();
    stack.push(c);
    stack.peek();
    stack.pop();

    # Python
    stack = []
    stack.append(c)
    stack[-1]        # peek
    stack.pop()

Key differences:
- Python list used as stack — no import needed
- `stack[-1]` to peek instead of `.peek()`
- `c in '([{'` instead of `c == '(' || c == '[' || c == '{'`
- `not stack` instead of `stack.isEmpty()`
- `len(stack) == 0` instead of `stack.isEmpty()`
