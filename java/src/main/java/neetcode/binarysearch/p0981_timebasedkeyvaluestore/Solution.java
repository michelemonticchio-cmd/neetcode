package neetcode.binarysearch.p0981_timebasedkeyvaluestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    /**
     * Time-based key-value store supporting multiple values per key,
     * each associated with a timestamp. Retrieval returns the value at
     * the largest stored timestamp <= the queried timestamp.
     *
     * set: O(1) amortized
     * get: O(log n) — binary search for rightmost timestamp <= query
     * Space: O(n) total
     */
    private final Map<String, List<Integer>> timestamps;
    private final Map<String, List<String>> values;

    public Solution() {
        timestamps = new HashMap<>();
        values = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        timestamps.putIfAbsent(key, new ArrayList<>());
        values.putIfAbsent(key, new ArrayList<>());
        timestamps.get(key).add(timestamp);
        values.get(key).add(value);
    }

    public String get(String key, int timestamp) {
        if (!timestamps.containsKey(key)) return "";

        List<Integer> times = timestamps.get(key);
        int left = 0, right = times.size() - 1;
        int result = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (times.get(mid) <= timestamp) {
                result = mid;       // valid candidate; look for a later one
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        if (result == -1) return "";
        return values.get(key).get(result);
    }
}
