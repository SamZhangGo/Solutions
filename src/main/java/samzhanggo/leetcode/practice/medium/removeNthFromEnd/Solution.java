package samzhanggo.leetcode.practice.medium.removeNthFromEnd;

import java.util.HashMap;

/**
 * @author: siming.zhang
 * 2020/4/22 15:16
 */
public class Solution {
    private HashMap<Integer, ListNode> posNodeMap;

    public ListNode removeNthFromEnd(ListNode head, int n) {
        posNodeMap = new HashMap<>();
        ListNode curNode = head;
        int count = 0;
        while (true) {
            posNodeMap.put(count, curNode);
            count++;
            curNode = curNode.next;
            if (curNode == null) {
                break;
            }
        }
        ListNode preNode = posNodeMap.get(count - n - 1);
        ListNode nextNode = posNodeMap.get(count + 1 - n);
        if (preNode != null) {
            preNode.next = nextNode;
        } else {
            head = nextNode;
        }

        return head;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            String str = ", " + val;
            if (next != null) {
                return str + next.toString();
            }
            return str;
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        Solution solution = new Solution();
        System.out.println(solution.removeNthFromEnd(head, 2));
    }
}
