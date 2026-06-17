from collections import Counter
from typing import List


class SolutionCounter:
    """
    One-liner using Counter.most_common(k).
    Time:  O(n log k) — most_common uses a heap internally
    Space: O(n)
    """
    def topKFrequent(self, nums: List[int], k: int) -> List[int]:
        count = Counter(nums)
        return [n for n, freq in count.most_common(k)]


class SolutionBucketSort:
    """
    Bucket sort approach — O(n) time.
    Max possible frequency is n (all elements equal), so we create
    n+1 buckets where bucket[freq] holds all elements with that frequency.
    Then scan buckets from right (highest freq) and collect k elements.
    Time:  O(n)
    Space: O(n)
    """
    def topKFrequent(self, nums: List[int], k: int) -> List[int]:
        count = Counter(nums)

        bucket = [[] for _ in range(len(nums) + 1)]
        for n, freq in count.items():
            bucket[freq].append(n)

        result = []
        for i in range(len(bucket) - 1, 0, -1):   # high freq → low freq
            for n in bucket[i]:
                result.append(n)
                if len(result) == k:
                    return result

        return result
