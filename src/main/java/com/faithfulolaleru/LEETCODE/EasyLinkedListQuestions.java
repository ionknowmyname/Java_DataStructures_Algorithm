package com.faithfulolaleru.LEETCODE;

import com.faithfulolaleru.base.ListNode;

import java.util.HashSet;
import java.util.Set;
import java.util.WeakHashMap;

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

    // 2095. Delete the Middle Node of a Linked List
    public ListNode deleteMiddle(ListNode head) {
        if(head == null || head.next == null) return null;
        if(head.next.next == null) { // list has only 2 nodes, delete 2nd node
            head.next = null;  // deletes 2nd node
            return head;
        }

        ListNode current = head;
        ListNode slow = head;
        ListNode fast = head;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // at this point, when fast is at the end, slow is the middle

        while(current != null) {
            if(current.next == slow) {
                current.next = current.next.next;  // delete node by skiping to next of next
            }
            current = current.next;  // continue regular iteration
        }

        return head;
    }

    // using counter/size to get middle
    public ListNode deleteMiddle2(ListNode head) {
        int size = 0;
        ListNode current = head;
        while(current != null) {    // Calculating size
            current= current.next;
            size++;
        }

        if (size == 1) {        //if the linked list contains only one node
            head = head.next;
            return head;
        }

        int i = 0;
        ListNode prev = head;  // prev node to find the node which is just before the middle node (pointing to the middle node)

        while(i < (size / 2) - 1 ) {   // "(size / 2) - 1"   // this like this is middle, so < is before middle
            // loop runs from head node(i=0) to (middle-2)th node and prev becomes equal to the (middle-1)th node
            prev = prev.next;
            i++;
        }

        prev.next = prev.next.next;  // deletes the middle node

        return head;
    }

    public ListNode deleteMiddle3(ListNode head) {

        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null;

        while(fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        // at this point, when fast is at the end, slow is the middle, prev the one before middle

        if(prev == null) {  //
            head = null;
        } else{
            prev.next = slow.next;  // prev.next is middle, so setting it to middle/slow.next deletes middle
            slow.next = null;  // coz slow should end in middle, so after middle, end it
        }

        return head;
    }
}
