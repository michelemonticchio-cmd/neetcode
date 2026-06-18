from solution import Solution


class TestSolution:
    def setup_method(self):
        self.sol = Solution()

    def test_classic_example(self):
        assert self.sol.maxProfit([10, 1, 5, 6, 7, 1]) == 6

    def test_leetcode_example(self):
        assert self.sol.maxProfit([7, 1, 5, 3, 6, 4]) == 5

    def test_no_profit(self):
        assert self.sol.maxProfit([7, 6, 4, 3, 1]) == 0

    def test_single_element(self):
        assert self.sol.maxProfit([5]) == 0

    def test_two_elements_profit(self):
        assert self.sol.maxProfit([1, 5]) == 4

    def test_two_elements_no_profit(self):
        assert self.sol.maxProfit([5, 1]) == 0

    def test_all_same(self):
        assert self.sol.maxProfit([3, 3, 3, 3]) == 0

    def test_increasing(self):
        assert self.sol.maxProfit([1, 2, 3, 4, 5]) == 4

    def test_min_at_end(self):
        assert self.sol.maxProfit([3, 2, 1]) == 0
