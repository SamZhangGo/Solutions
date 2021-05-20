package samzhanggo.leetcode.practice.easy.reverse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: siming.zhang
 * 2020/4/16 16:40
 */
public class Solution {
    public int reverse2(int x) {
        StringBuilder stringBuilder = new StringBuilder();
        boolean isPositive = x >= 0;
        String reverseStr = stringBuilder.append(Math.abs(x)).reverse().toString();
        try {
            int xReverse = Integer.valueOf(reverseStr);
            return isPositive ? xReverse : -xReverse;
        } catch (Exception e) {
            return 0;
        }
    }

    public int reverse(int x) {
        boolean isPositive = x >= 0;
        if (x == 0 || x == Integer.MIN_VALUE) {
            return 0;
        }
        int absX = Math.abs(x);
        int remind = absX;
        int result = 0;
        double temp = 0;
        int i = 0;
        while (true) {
            int mod = remind % 10;
            remind = remind / 10;
            try {
                temp = (result * 10.0f + mod);
                if (temp > Integer.MAX_VALUE) {
                    return 0;
                }
                result = (int) temp;
            } catch (Exception e) {
                return 0;
            }
            i++;
            if (remind == 0) {
                break;
            }
        }
        return isPositive ? result : -result;
    }

    public static void main(String[] args) {
//        Solution solution = new Solution();
//        System.out.println(solution.reverse(1534236469));
//        System.out.println(solution.reverse(-2100));
//        System.out.println(new Date().getTime());
    }
}
