package neetcode.arraysandhashing.p0271_encodeanddecodestrings;

import java.util.ArrayList;
import java.util.List;

class Solution {
      /**
     * Encodes a list of strings into a single string using length-prefix encoding.
     * Format: <length>#<string><length>#<string>...
     *
     * Time:  O(N) where N is the total number of characters across all strings
     * Space: O(N) for the output string
     */
    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for (String s : strs) {
            sb.append(s.length()).append('#').append(s);
        }
        return sb.toString();
    }

    public List<String> decode(String str) {
        List<String> result = new ArrayList<>();
        int i = 0;
        while (i < str.length()) {
            int j = i;
            while (str.charAt(j) != '#') {
                j++;
            }
            int l = Integer.parseInt(str.substring(i, j));
            result.add(str.substring(j + 1, j + 1 + l));
            i = j + 1 + l;
        }
        return result;
    }
}
