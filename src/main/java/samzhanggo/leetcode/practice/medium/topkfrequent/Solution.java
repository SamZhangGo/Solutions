package samzhanggo.leetcode.practice.medium.topkfrequent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author: siming.zhang
 * 2021/5/20 14:47
 */
public class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        HashMap<String, Integer> wordCountMap = new HashMap<>();
        List<String> wordRank = new ArrayList<>(k);
        int accupiedPos = 0;
        int minAppereanceCount = 0;
        for (String word : words) {
            Integer count = wordCountMap.get(word);
            int wordCount = 0;
            if (count == null) {
                wordCountMap.put(word, 1);
                wordCount = 1;
            } else {
                wordCountMap.put(word, 1 + count);
                wordCount = count + 1;
            }
            if(accupiedPos < k){

            } else {

            }
        }

        return wordRank;
    }
}
