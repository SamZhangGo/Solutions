package samzhanggo.leetcode.practice.easy.hammingdistance;

/**
 * @author: siming.zhang
 * 2021/5/27 10:00
 */
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.hammingDistance(1, 4));
        System.out.println(solution.hammingDistance(1, 5));
    }
    public int hammingDistance(int x, int y) {
        int xor = x ^ y;
        int count = 0;
        while(xor != 0) {
            int mod = xor % 2;
            if(mod != 0) {
                count++;
            }
            xor = xor / 2;
        }
        return count;
    }
}
