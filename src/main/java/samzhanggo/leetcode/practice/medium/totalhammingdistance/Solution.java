package samzhanggo.leetcode.practice.medium.totalhammingdistance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: siming.zhang
 * 2021/5/28 10:51
 */
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.totalHammingDistance(new int[]{4, 14, 2}));
    }



    public int totalHammingDistance(int[] nums) {
        if (nums.length <= 1) {
            return 0;
        }
        int count = 0;
        boolean hasValue = true;
        while (hasValue) {
            hasValue = false;
            int zeroCount = 0;
            int oneCount = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == 0) {
                    zeroCount++;
                    continue;
                }
                int mod = nums[i] & 1;
                if (mod == 1) {
                    oneCount++;
                } else {
                    zeroCount++;
                }
                int nextRoundNum = nums[i] >> 1;
                if (nextRoundNum > 0) {
                    hasValue = true;
                }
                nums[i] = nextRoundNum;
            }
            count += zeroCount * oneCount;
        }

        return count;
    }


//    public int totalHammingDistance(int[] nums) {
//        String[] binaryStringArray = new String[nums.length];
//        int maxLength = 0;
//
//        for (int i = 0; i < nums.length; i++) {
//            String binaryString = Integer.toBinaryString(nums[i]);
//            if (binaryString.length() > maxLength) {
//                maxLength = binaryString.length();
//            }
//            binaryStringArray[i] = binaryString;
//        }
//        int totalCount = 0;
//        for (int i = 0; i < maxLength; i++) {
//            int zeroCount = 0;
//            int oneCount = 0;
//            for (int j = 0; j < binaryStringArray.length; j++) {
//                if (binaryStringArray[j].length() < maxLength - i) {
//                    zeroCount++;
//                } else {
//                    if (binaryStringArray[j].charAt(binaryStringArray[j].length() - maxLength + i) == '1') {
//                        oneCount++;
//                    } else {
//                        zeroCount++;
//                    }
//                }
//            }
//            totalCount += zeroCount * oneCount;
//        }
//
//        return totalCount;
//
//    }

}
