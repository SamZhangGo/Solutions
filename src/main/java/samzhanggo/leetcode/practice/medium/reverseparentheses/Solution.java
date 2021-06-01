package samzhanggo.leetcode.practice.medium.reverseparentheses;

import java.util.HashMap;
import java.util.Stack;

/**
 * @author: siming.zhang
 * 2021/5/26 10:38
 */
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.reverseParentheses(""));
        System.out.println(solution.reverseParentheses("()"));
        System.out.println(solution.reverseParentheses("(abcd)"));
        System.out.println(solution.reverseParentheses("(u(love)i)"));
        System.out.println(solution.reverseParentheses("(ed(et(oc))el)"));
        System.out.println(solution.reverseParentheses("a(bcdefghijkl(mno)p)q"));
        System.out.println(solution.reverseParentheses("ta()usw((((a))))"));
    }

    public String reverseParentheses(String s) {
        if (s == null || s.isEmpty()) {
            return "";
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else if (s.charAt(i) == ')') {
                int left = stack.pop();
                map.put(left, i);
                map.put(i, left);
            }
        }
        StringBuilder sb = new StringBuilder();
        int start = 0;
        int end = s.length() - 1;
        int reverseCount = 0;
        doReverseParentheses(s, sb, start, end, reverseCount, map);
        return sb.toString();
    }

    private StringBuilder doReverseParentheses(String s, StringBuilder sb, int start, int end, int reverseCount, HashMap<Integer, Integer> map) {
        if (start > end) {
            return sb;
        }
        int iteratorStart = start;
        int iteratorEnd = end;
        int step = 1;
        if (reverseCount % 2 == 1) {
            iteratorStart = end;
            iteratorEnd = start;
            step = -1;
        }
        for (int i = iteratorStart; step > 0 ? i <= iteratorEnd : i >= iteratorEnd; i = i + step) {
            char indexChar = s.charAt(i);
            if (indexChar == '(' || indexChar == ')') {
                int matchIndex = map.get(i);
                int subStart = step > 0 ? i + 1 : matchIndex + 1;
                int subEnd = step > 0 ? matchIndex - 1 : i - 1;
                doReverseParentheses(s, sb, subStart, subEnd, ++reverseCount, map);
                --reverseCount;
                i = matchIndex;
            } else {
                sb.append(indexChar);
            }
        }
        return sb;
    }
}
