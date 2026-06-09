# 20. Valid Parentheses

🔗 [LeetCode](https://leetcode.com/problems/valid-parentheses/) | 📺 [NeetCode](https://neetcode.io/problems/validate-parentheses)

**Difficulty:** Easy
**Category:** Stack

## Problem

Given a string `s` containing only `'('`, `')'`, `'{'`, `'}'`, `'['`, `']'`,
return `true` if the string is valid. A string is valid when:

1. Every opening bracket is closed by the same type of bracket.
2. Opening brackets are closed in the correct order.
3. Every closing bracket has a corresponding opening bracket.

## Approach

**LIFO stack.**

The key observation is that the **last opened bracket must be the first
closed**. This is exactly the LIFO (Last In, First Out) property of a stack.

Algorithm:
1. For each character `c`:
   - If `c` is an opener (`(`, `[`, `{`), push it onto the stack.
   - If `c` is a closer (`)`, `]`, `}`), check the top of the stack:
     - If the stack is empty → no matching opener → invalid.
     - If the top does not match `c`'s expected opener → mismatch → invalid.
     - Otherwise → pop the matched opener.
2. After the loop, the stack must be empty (no unclosed openers).

A `HashMap` maps each closer to its expected opener, making the match
check a clean O(1) lookup.

## Why `return stack.isEmpty()` and not `return true`

If the input is `"((("`, the loop ends without ever finding a closer,
and no `return false` is triggered. But there are three unclosed openers
still on the stack. Returning `stack.isEmpty()` catches this case:
`false` because the stack is non-empty.

## Complexity

- **Time:** O(n) — single pass over the string
- **Space:** O(n) — worst case: all characters are openers (e.g. `"((((("`)
  and the stack grows to size n

## Trace with "([{}])"

    '(' → push. stack: [(]
    '[' → push. stack: [(, []
    '{' → push. stack: [(, [, {]
    '}' → peek='{', pairs.get('}')='{' → match! pop. stack: [(, []
    ']' → peek='[', pairs.get(']')='[' → match! pop. stack: [(]
    ')' → peek='(', pairs.get(')')='(' → match! pop. stack: []
    End: stack empty → true ✅

## Trace with "([)]" (invalid nesting)

    '(' → push. stack: [(]
    '[' → push. stack: [(, []
    ')' → peek='[', pairs.get(')')='(' → '[' ≠ '(' → return false ✅

## Notes

Valid Parentheses is the canonical introduction to the stack pattern.
The same "push openers, match closers" template generalizes to harder
problems:

- **Min Stack** (#155) — augmented stack tracking the running minimum.
- **Daily Temperatures** (#739) — monotonic stack finding the next
  greater element.
- **Largest Rectangle in Histogram** (#84) — monotonic stack computing
  areas.

The mental model: *"Whenever you need to match or pair things in reverse
order of their appearance, think stack."*
