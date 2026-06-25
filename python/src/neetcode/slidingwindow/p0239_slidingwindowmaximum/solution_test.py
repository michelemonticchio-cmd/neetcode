from solution import Solution


class TestSolution:
    def setup_method(self):
        self.sol = Solution()

    def test_classic_example(self):
        assert self.sol.maxSlidingWindow(
            [1, 3, -1, -3, 5, 3, 6, 7], 3) == [3, 3, 5, 5, 6, 7]

    def test_k_equals_1(self):
        assert self.sol.maxSlidingWindow([1, 3, -1, -3, 5], 1) == [1, 3, -1, -3, 5]

    def test_k_equals_n(self):
        assert self.sol.maxSlidingWindow([1, 3, -1, -3, 5], 5) == [5]

    def test_single_element(self):
        assert self.sol.maxSlidingWindow([7], 1) == [7]

    def test_all_same(self):
        assert self.sol.maxSlidingWindow([4, 4, 4, 4, 4], 3) == [4, 4, 4]

    def test_decreasing(self):
        assert self.sol.maxSlidingWindow([5, 4, 3, 2, 1], 3) == [5, 4, 3]

    def test_increasing(self):
        assert self.sol.maxSlidingWindow([1, 2, 3, 4, 5], 3) == [3, 4, 5]

    def test_duplicates_at_boundary(self):
        assert self.sol.maxSlidingWindow([3, 1, 3, 1, 3], 3) == [3, 3, 3]
