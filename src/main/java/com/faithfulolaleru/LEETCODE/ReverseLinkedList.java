package com.faithfulolaleru.LEETCODE;

import com.faithfulolaleru.base.ListNode;

public class ReverseLinkedList {



    // 206. Reverse Linked List
    public static ListNode reverseSinglyLinkedList(ListNode head) {

        if(head == null) return null;
        if(head.next == null) return head;  // only 1 item in list

        ListNode prev = null;
        ListNode current = head;

        while(current != null) {
            ListNode temp = current.next;
            current.next = prev;  // reverse the link
            prev = current;
            current = temp;  // go to original next and run process again
        }

        return prev;

    }
}
