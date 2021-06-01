package samzhanggo.leetcode.practice.medium.maxuncrossedlines;

/**
 * @author: siming.zhang
 * 2021/5/21 11:13
 */
public class Solution {
    public static void main(String[] args) {
        Solution solution= new Solution();
        int[] nums1 = {1,4,2};
        int[] nums2 = {1,2,4};
        System.out.println(solution.maxUncrossedLines(nums1, nums2));
    }

    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            int num1 = nums1[i - 1];
            for (int j = 1; j <= n; j++) {
                int num2 = nums2[j - 1];
                if (num1 == num2) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }

//    public int maxUncrossedLines(int[] nums1, int[] nums2) {
//        int ret[][] = new int[nums1.length][nums2.length];
//        return doCountMaxUncrossedLines(0, nums1, 0, nums2, ret);
//    }
//
//    public int doCountMaxUncrossedLines(int index1, int[] nums1, int index2, int[] nums2, int[][] ret) {
//        System.out.println(String.format("handle index1 = %d and index2 = %d", index1, index2));
//        if (index1 == nums1.length || index2 == nums2.length) {
//            return 0;
//        }
//        int newIndex2 = index2;
//        int newIndex1 = index1;
//        for (int i = index2; i < nums2.length; i++) {
//            if (nums2[i] == nums1[index1]) {
//                newIndex1++;
//                newIndex2 = i + 1;
//                break;
//            }
//        }
//        if (newIndex2 > index2) {
//            ret[index1][index2] = Math.max(1 + doCountMaxUncrossedLines(newIndex1, nums1, newIndex2, nums2, ret), doCountMaxUncrossedLines(index1 + 1, nums1, index2, nums2, ret));
//        } else {
//            ret[index1][index2] = doCountMaxUncrossedLines(index1 + 1, nums1, index2, nums2, ret);
//        }
//        System.out.println(String.format("ret[%d][%d] = %d", index1, index2, ret[index1][index2]));
//        return ret[index1][index2];
//    }
}
