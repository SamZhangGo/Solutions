package samzhanggo.leetcode.practice.medium.longestsubstring;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: siming.zhang
 * 2020/4/11 14:11
 */
public class Solution2 {
    private Map<String, Integer> charInMap;
    private int maxSubStringCount = 0;
    private int lastLength = 0;
    private int firstDuplicateIndex = 0;
    private int lastDuplicatedIndex = 0;

    public int lengthOfLongestSubstring(String s) {
        charInMap = new HashMap<>();
        lastLength = s.length();
        boolean firstDuplicateFetched = false;

        for (int i = 0; i < s.length(); i++) {
            String charStr = s.substring(i, i + 1);
            Integer lastIndex = charInMap.get(charStr);
            if (lastIndex != null) {
                if (!firstDuplicateFetched) {
                    firstDuplicateIndex = i;
                    firstDuplicateFetched = true;
                }
                if (lastIndex > lastDuplicatedIndex) {
                    lastDuplicatedIndex = lastIndex;
                }
            }
            charInMap.put(charStr, i);
            if (firstDuplicateFetched) {
                if (maxSubStringCount < i - lastDuplicatedIndex) {
                    maxSubStringCount = i - lastDuplicatedIndex;
                }
            }

        }
        if (maxSubStringCount == 0) {
            return s.length();
        }
        lastLength = s.length() - 1 - lastDuplicatedIndex;
        if (maxSubStringCount < lastLength || maxSubStringCount < firstDuplicateIndex) {
            return Math.max(firstDuplicateIndex, lastLength);
        }
        return maxSubStringCount;
    }

//    private void offer(String s) {
//        if (charInMap.containsKey(s)) {
//            while (true) {
//                String e = queue.remove();
//                charInMap.remove(e);
//                if (e.equals(s)) {
//                    break;
//                }
//            }
//        }
//        queue.offer(s);
//        charInMap.put(s, true);
//        if (maxSubStringCount < queue.size()) {
//            maxSubStringCount = queue.size();
//        }
//    }

    public static void main(String[] args) {
        Solution2 solution = new Solution2();
//        String input = "dvdf";
//        String input = "abba";
        String input = "ohomm";

        System.out.println(solution.lengthOfLongestSubstring(input));
    }
}
