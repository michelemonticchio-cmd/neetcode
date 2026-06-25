class Solution:
    def isValid(self, s: str) -> bool:
        """
        Returns True if the bracket string is valid.

        Approach: LIFO stack.
        Push opening brackets. For each closing bracket, check that the
        top of the stack is the matching opener. If not (or stack empty),
        invalid. At the end, the stack must be empty.

        Time:  O(n)
        Space: O(n) — worst case all openers e.g. "((((("
        """
        stack = []
        pairs = {')': '(', ']': '[', '}': '{'}

        for c in s:
            if c in '([{':
                stack.append(c)
            elif not stack or stack[-1] != pairs[c]:
                return False
            else:
                stack.pop()

        return len(stack) == 0
