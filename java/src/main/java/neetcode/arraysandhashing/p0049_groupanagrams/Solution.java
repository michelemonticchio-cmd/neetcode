package neetcode.arraysandhashing.p0049_groupanagrams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    /**
     * Groups strings that are anagrams of each other.
     *
     * Time:  O(n * k) where n = number of strings, k = average string length
     * Space: O(n * k) for the map storing all strings and their signature keys
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> groups = new HashMap<>();

        for (String s : strs) {
            // 1. Compute the signature: array of 26 letter frequencies
            int[] count = new int[26];
            for (int i = 0; i < s.length(); i++) {
                count[s.charAt(i) - 'a']++;
            }

            // 2. Convert the signature into a String to use as map key
            String key = Arrays.toString(count);

            // 3. Add the string to the corresponding group
            //    (computeIfAbsent creates an empty list if the key is new)
            groups.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
        }

        // 4. Return all the groups (= values of the map)
        return new ArrayList<>(groups.values());
    }
}
