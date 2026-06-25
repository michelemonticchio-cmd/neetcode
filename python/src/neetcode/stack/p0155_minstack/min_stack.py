class MinStack:
    """
    Stack supporting push, pop, top and getMin in O(1).

    Approach: two parallel lists.
    - stack: holds all values in insertion order.
    - minStack: holds the running minimum at each level.
      minStack[-1] is always the minimum of all current elements.

    Time:  O(1) for all operations
    Space: O(n)
    """

    def __init__(self):
        self.stack = []
        self.minStack = []

    def push(self, val: int) -> None:
        self.stack.append(val)
        # new minimum is the smaller of val and current minimum
        minVal = min(val, self.minStack[-1] if self.minStack else val)
        self.minStack.append(minVal)

    def pop(self) -> None:
        self.stack.pop()
        self.minStack.pop()

    def top(self) -> int:
        return self.stack[-1]

    def getMin(self) -> int:
        return self.minStack[-1]
