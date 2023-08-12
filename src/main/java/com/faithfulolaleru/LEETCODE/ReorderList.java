package com.faithfulolaleru.LEETCODE;

import com.faithfulolaleru.base.ListNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReorderList {

    /*
    *   Get to the middle of the linked list and break into two linked list
    *   reverse the 2nd linked list, then for each iteration,
    *   add the values of each linked list to form form the new linked list
    *
    *   e. g 1->2->3->4->5->6->null becomes 1->2->3->null & 4->5->6->null
    *   then reversing the 2nd part becomes 6->5->4->null
    *
    *   1->2->3->null
    *   6->5->4->null
    *
    *   so final list would be 1->6->2->5->3->4->null
    *
    * */


    // 143. Reorder List
    public void reorderList(ListNode head) {

        if(head == null || head.next == null) return;

        ListNode current = head;  // head of 1st list
        ListNode slow = head;   // head of 2nd list
        ListNode fast = head;   // tail of 2nd list
        ListNode prev = null;   // tail of 1st list

        // finding middle of linked list
        while(fast != null && fast.next != null) {
            prev = slow;     // look how tail of 1st list is always 1 behind start of 2nd list
            slow = slow.next;
            fast = fast.next.next;
        }
        // when fast gets to null, slow would be at the middle
        // we use prev as tail of first half of list

        prev.next = null;  // this splits the list into two

        ListNode reversedHead2 = reverse(slow);  // reverse 2nd list by using 2nd list head
        // mind you, slow is now slow.next, so head of new linked list

        merge(current, reversedHead2);  // merge with heads of the two lists
    }

    // textbook reverse linked list
    public ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode current = head;

        while(current != null) {
            ListNode temp = current.next;
            current.next = prev;  // reverse the link
            prev = current;
            current = temp;  // go to original next and run process again
        }

        return prev;   // prev is the new head
    }

    public void merge(ListNode head1, ListNode head2) {

        while(head1 != null) {
            // save next values so we don't loose references to them
            ListNode head1Next = head1.next;
            ListNode head2Next = head2.next;

            head1.next = head2;  // set new head1 next to head2

            // no need to set head2's next value back to the original head1's next coz its null
            // i.e we run out of values in 1st linked list
            if(head1Next == null) return;

            head2.next = head1Next;

            // continue regularly
            head1 = head1Next;
            head2 = head2Next;
        }

    }

    // all in 1, no helper method
    public void reorderList2(ListNode head) {

        if(head == null || head.next == null) return;

        ListNode slow = head;
        ListNode fast = head;   // tail of 2nd list
        ListNode prev = null;   // head of 2nd list

        // finding middle of linked list
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // when fast gets to null, slow would be at the middle
        // we use prev as tail of first half of list

        // Start reversing 2nd list
        ListNode newCurrent = slow.next;  // head of new list
        slow.next = null;  // this splits the list into two

        while(newCurrent != null) {
            ListNode nextNode = newCurrent.next;
            newCurrent.next = prev;
            prev = newCurrent;
            newCurrent = nextNode;
        }

        //Merging two Linked Lists
        ListNode curr1 = head;
        ListNode curr2 = prev;
        while(curr1 != null && curr2 != null) {
            ListNode next1 = curr1.next;
            ListNode next2 = curr2.next;

            curr1.next = curr2;
            curr2.next = next1;

            curr1 = next1;
            curr2 = next2;
        }

    }
}
