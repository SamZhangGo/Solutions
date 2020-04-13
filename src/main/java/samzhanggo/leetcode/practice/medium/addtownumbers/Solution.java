package samzhanggo.leetcode.practice.medium.addtownumbers;

/**
 * @author: siming.zhang
 * 2020/4/11 11:13
 */
public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode retListNode = null;
        ListNode curNode = null;
        int next = 0;
        while (l1 != null && l2 != null) {
            int sum = l1.val + l2.val + next;
            next = sum / 10;
            int mod = sum % 10;
            if (retListNode == null) {
                retListNode = new ListNode(mod);
                curNode = retListNode;
            } else {
                ListNode nextNode = new ListNode(mod);
                curNode.next = nextNode;
                curNode = curNode.next;
            }
            l1 = l1.next;
            l2 = l2.next;
        }
        if (l1 == null && l2 != null) {
            acceptRemindNode(curNode, l2, next);

        } else if (l1 != null && l2 == null) {
            acceptRemindNode(curNode, l1, next);
        } else {
            if (next > 0) {
                ListNode endNode = new ListNode(next);
                curNode.next = endNode;
            }
        }

        return retListNode;
    }

    private void acceptRemindNode(ListNode curNode, ListNode reminder, int next) {
        curNode.next = reminder;
        while (next > 0 && reminder != null) {
            int sum = reminder.val + next;
            next = sum / 10;
            int mod = sum % 10;
            reminder.val = mod;
            curNode = reminder;
            reminder = reminder.next;
        }
        if (reminder == null && next > 0) {
            ListNode endNode = new ListNode(next);
            curNode.next = endNode;
        }
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
