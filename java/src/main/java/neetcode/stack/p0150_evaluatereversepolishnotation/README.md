# 150. Evaluate Reverse Polish Notation

🔗 [LeetCode](https://leetcode.com/problems/evaluate-reverse-polish-notation/) | 📺 [NeetCode](https://neetcode.io/problems/evaluate-reverse-polish-notation)

**Difficulty:** Medium
**Category:** Stack

## Problem

Given an array of strings `tokens` representing an arithmetic expression in
**Reverse Polish Notation** (RPN), evaluate and return the result as an integer.

Valid operators: `+`, `-`, `*`, `/`. Each token is either an integer or an
operator. Division truncates toward zero.

## What is RPN?

In standard infix notation, operators appear *between* operands: `(2 + 1) * 3`.
In RPN, operators appear *after* their operands: `2 1 + 3 *`.

RPN eliminates the need for parentheses because the order of operations is
fully determined by the position of tokens.

## Approach

**Single stack.**

Scan the token array left to right:
- **Number** → push onto the stack.
- **Operator** → pop two operands, apply the operator, push the result.

After processing all tokens, the stack contains exactly one element: the answer.

The key subtlety: the **first** pop gives the *right* operand and the **second**
pop gives the *left* operand. For commutative operators (`+`, `*`) the order
doesn't matter, but for `-` and `/` it does:

    tokens ["5", "3", "-"] means 5 - 3 = 2, not 3 - 5 = -2

So always: `a = stack.pop(); b = stack.pop()` then compute `b OP a`
— or equivalently `int b = stack.pop(); int a = stack.pop()` then `a OP b`.

## Complexity

- **Time:** O(n) — each token is processed exactly once
- **Space:** O(n) — in the worst case (all numbers, no operators) the stack
  holds all n tokens

## Trace with ["2","1","+","3","*"]

    token="2" → push(2)          stack: [2]
    token="1" → push(1)          stack: [2, 1]
    token="+" → b=1, a=2, 2+1=3  stack: [3]
    token="3" → push(3)          stack: [3, 3]
    token="*" → b=3, a=3, 3*3=9  stack: [9]
    return 9 ✅

## Trace with ["4","13","5","/","+"]

    token="4"  → push(4)           stack: [4]
    token="13" → push(13)          stack: [4, 13]
    token="5"  → push(5)           stack: [4, 13, 5]
    token="/"  → b=5, a=13, 13/5=2 stack: [4, 2]   (truncates toward zero)
    token="+"  → b=2, a=4, 4+2=6   stack: [6]
    return 6 ✅

## Pitfalls

- **Wrong operand order.** Always pop `b` first (right), then `a` (left).
  For `-` and `/` swapping them gives a wrong result.
- **Integer division truncates toward zero.** Java's `/` operator on `int`
  truncates toward zero by default, which matches the problem's requirement.
  No special handling needed.
- **Negative number tokens.** `Integer.parseInt("-3")` works correctly —
  no need to special-case negative numbers in the string.
- **`"+-*/".contains(token)` edge case.** This check works for single-char
  operators. Multi-digit numbers like `"12"` won't match any single character
  in `"+-*/"`, so they correctly fall into the `else` branch.

## Notes

RPN evaluation is the canonical demonstration of the stack pattern:
*"process items in order; when you see a trigger (the operator), act on
the most recently seen unprocessed items (the operands)"*.

The same "push values, act on trigger" template appears in:
- **Basic Calculator** (#224) — handles parentheses and precedence.
- **Decode String** (#394) — nested repetitions decoded with a stack.
- **Generate Parentheses** (#22) — builds valid strings using a DFS stack.
