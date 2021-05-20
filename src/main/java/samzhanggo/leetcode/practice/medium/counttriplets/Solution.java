package samzhanggo.leetcode.practice.medium.counttriplets;

/**
 * @author: siming.zhang
 * 2021/5/18 11:05
 */
public class Solution {
    public static void main(String[] args) {
        int[] arr = {2, 3, 1, 6, 7};
        Solution solution = new Solution();
        System.out.println(solution.countTriplets(arr));
    }

    public int countTriplets(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            int xor = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                xor = xor ^ arr[j];
                if (xor == 0) {
                    count = count + j - i;
                }
            }
        }

        return count;
    }
}
