package com.faithfulolaleru.LEETCODE;

import com.faithfulolaleru.base.ListNode;

import java.util.HashSet;
import java.util.Set;

public class EasyLinkedListQuestions {

    // 141. Linked List Cycle
    public boolean hasCycle(ListNode head) {
        if(head == null || head.next == null) return false;

        ListNode current = head;
        Set<ListNode> set = new HashSet<>();

        while(current != null) {
            if(set.contains(current)) return true;

            set.add(current);
            current = current.next;
        }

        return false;
    }

    public boolean hasCycle2(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if(fast == slow) return true;
        }
        return false;
    }

    // 876. Middle of the Linked List
    public ListNode middleNode(ListNode head) {
        if(head == null) return null;

        ListNode slow = head;
        ListNode fast = head;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

        }
        return slow;
    }
}
