package samzhanggo.leetcode.practice.medium.atoi;

/**
 * @author: siming.zhang
 * 2020/4/14 16:37
 */
public class Solution {
//    private String strReg = "^\\s?[+|-]{0,1}[0-9]{1,}";

    public int myAtoi(String str) {
        String trimStr = str.trim();
        int ret = Integer.MAX_VALUE;
        if (trimStr.length() == 0) {
            return 0;
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (true) {
            char c = trimStr.charAt(i);
            if (i == 0) {
                if (!isSymbolChar(c) && !isNumChar(c)) {
                    break;
                }
            } else {
                if (!isNumChar(c)) {
                    break;
                }
            }
            if (c != '+') {
                if (c == '-') {
                    ret = Integer.MIN_VALUE;
                }
                sb.append(c);
            }

            if (i == trimStr.length() - 1) {
                break;
            }
            i++;
        }
        if (sb.length() == 0) {
            return 0;
        }
        if(sb.length() == 1 && sb.charAt(0) == '-'){
            return 0;
        }
        try {
            ret = Integer.valueOf(sb.toString());
        } catch (Exception e) {

        }

        return ret;
    }

    private boolean isSymbolChar(char c) {
        return c == '+' || c == '-';
    }

    private boolean isNumChar(char c) {
        if (c >= '0' && c <= '9') {
            return true;
        }
        return false;
    }

    private boolean isValid(String str) {
        if ("".equals(str.trim())) {
            return false;
        }

        return true;
    }
}
