package samzhanggo.leetcode.practice.medium.longestsubstring;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @author: siming.zhang
 * 2020/4/11 14:11
 */
public class Solution {
    Queue<String> queue;
    Map<String, Boolean> charInMap;
    int maxSubStringCount = 0;

    public int lengthOfLongestSubstring(String s) {
        queue = new LinkedList<>();
        charInMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            String charStr = s.substring(i,i+1);
            offer(charStr);
        }
        return maxSubStringCount;
    }

    private void offer(String s) {
        if (charInMap.containsKey(s)) {
            while (true) {
                String e = queue.remove();
                charInMap.remove(e);
                if (e.equals(s)) {
                    break;
                }
            }
        }
        queue.offer(s);
        charInMap.put(s, true);
        if (maxSubStringCount < queue.size()) {
            maxSubStringCount = queue.size();
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String input = "abcabcbb";
        solution.lengthOfLongestSubstring(input);
    }
}
