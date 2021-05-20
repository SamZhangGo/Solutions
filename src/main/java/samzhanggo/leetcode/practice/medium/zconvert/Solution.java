package samzhanggo.leetcode.practice.medium.zconvert;

/**
 * @author: siming.zhang
 * 2020/4/14 15:28
 */
public class Solution {
    public String convert(String s, int numRows) {
        StringBuilder sb = new StringBuilder();
        int firstOrLastLineGap = numRows * 2 - 2;
        if (numRows == 1) {
            return s;
        }
        if (numRows == s.length()) {
            return s;
        }
        for (int i = 0; i < numRows; i++) {
            boolean curLineOdd = true;
            int index = i;

            if (i == 0 || i == numRows - 1) {
                while (index < s.length()) {
                    sb.append(s.charAt(index));
                    index = index + firstOrLastLineGap;
                }
            } else {
                int oddGap = (numRows - i) * 2 - 2;
                int evenGap = i * 2;
                while (index < s.length()) {
                    sb.append(s.charAt(index));
                    if (curLineOdd) {
                        index = index + oddGap;
                        curLineOdd = false;
                    } else {
                        index = index + evenGap;
                        curLineOdd = true;
                    }
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.convert("A", 1));
    }
}
