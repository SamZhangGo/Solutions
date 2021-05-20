package samzhanggo.leetcode.practice.medium.divide;

/**
 * @author: siming.zhang
 * 2021/5/18 15:31
 */
public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
//        System.out.println("divide result: " + solution.divide(10, 3));
//        System.out.println("divide result: " + solution.divide(7, -3));
//        System.out.println("divide result: " + solution.divide(-1, 1));
//        System.out.println("divide result: " + solution.divide(-2147483648, 2));
////        System.out.println("divide result: " + solution.divide(2147483647, -2147483648));
////        System.out.println("divide result: " + solution.divide(2147483647, 3));
//        System.out.println("divide result: " + solution.divide(-1, 1));
//        System.out.println("divide result: " + solution.divide(-231, 3));
        System.out.println("divide result: " + solution.divide(-2147483648
                , -1109186033));

    }

    public int divide(int dividend, int divisor) {
        if (dividend == 0) {
            return 0;
        }
        if (dividend == divisor) {
            return 1;
        }
        if (dividend + divisor == 0) {
            return -1;
        }
        if (divisor == 1) {
            return dividend;
        }
        if (divisor == -1) {
            if (dividend != Integer.MIN_VALUE) {
                return -dividend;
            } else {
                return Integer.MAX_VALUE;
            }
        }
        if (divisor == Integer.MIN_VALUE) {
            return 0;
        }

        if (dividend != Integer.MIN_VALUE && Math.abs(dividend) < Math.abs(divisor)) {
            return 0;
        }


        int ret = 0;
        if (dividend == Integer.MIN_VALUE) {
            if (divisor > 0) {
                dividend = dividend + divisor;
                ret = -1;
            } else {
                dividend = dividend - divisor;
                ret = 1;
            }
        }
        int posdividend = Math.abs(dividend);
        int posdivisor = Math.abs(divisor);

        String dividenBinary = Integer.toBinaryString(posdividend);
        String divisorBinary = Integer.toBinaryString(posdivisor);

        int num = 0;
        int dividendIndex = dividenBinary.length() - 1;
        int divisorIndex = divisorBinary.length() - 1;
        while (dividenBinary.charAt(dividendIndex) == '0' && divisorBinary.charAt(divisorIndex) == '0') {
            num++;
            dividendIndex--;
            divisorIndex--;
        }
        if (num > 0) {
            posdivisor = posdivisor >> num;
            posdividend = posdividend >> num;
        }

        if (posdivisor == 1) {
            if (ret > 0) {
                ret += posdividend;
            } else if (ret < 0) {
                ret -= posdividend;
            }
            return ret;
        } else {
            int shift = dividendIndex -divisorIndex < 0? 0:dividendIndex-divisorIndex;
            int remind = posdividend - (posdivisor << shift);
            int remindRet = 1 << shift;
            while (remind < 0 || remind >= posdivisor) {
                if (remind < 0) {
                    remindRet--;
                    remind += posdivisor;
                } else {
                    remindRet++;
                    remind -= posdivisor;
                }
            }

            if ((dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0)) {
                return -remindRet + ret;
            }
            return remindRet + ret;
        }


    }
}
