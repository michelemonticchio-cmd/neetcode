from min_stack import MinStack


class TestMinStack:

    def test_basic_sequence(self):
        ms = MinStack()
        ms.push(5)
        ms.push(3)
        ms.push(7)
        ms.push(2)
        assert ms.getMin() == 2
        assert ms.top() == 2
        ms.pop()
        assert ms.getMin() == 3
        assert ms.top() == 7

    def test_min_after_pop(self):
        ms = MinStack()
        ms.push(1)
        ms.push(2)
        assert ms.getMin() == 1
        ms.pop()
        assert ms.getMin() == 1

    def test_duplicate_minimums(self):
        ms = MinStack()
        ms.push(3)
        ms.push(3)
        assert ms.getMin() == 3
        ms.pop()
        assert ms.getMin() == 3

    def test_single_element(self):
        ms = MinStack()
        ms.push(42)
        assert ms.top() == 42
        assert ms.getMin() == 42

    def test_negative_numbers(self):
        ms = MinStack()
        ms.push(0)
        ms.push(-1)
        ms.push(-3)
        assert ms.getMin() == -3
        ms.pop()
        assert ms.getMin() == -1

    def test_min_not_at_top(self):
        ms = MinStack()
        ms.push(5)
        ms.push(1)
        ms.push(4)
        assert ms.getMin() == 1
        assert ms.top() == 4
