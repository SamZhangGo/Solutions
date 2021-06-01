package samzhanggo.leetcode.practice.easy.ispowoffour;

/**
 * @author: siming.zhang
 * 2021/5/31 15:09
 */
public class Solution {
    public boolean isPowerOfFour(int n) {
        if (n == 1) {
            return true;
        }
        if (n <= 0) {
            return false;
        }

        while (n > 1) {
            int mod = n & 3;
            if (mod > 0) {
                return false;
            }
            n = n >> 2;
        }
        return true;
    }
}
