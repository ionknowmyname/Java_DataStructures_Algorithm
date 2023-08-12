package com.faithfulolaleru.HACKERRANK;

import com.faithfulolaleru.base.ListNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MergeSortedLinkedList {

    /*
        Merge two sorted singly linked list into 1
     */

    public static ListNode mergeLists(ListNode head1, ListNode head2) {

        // gather all nodes from both linked list, add to an array list, sort,
        // and convert back to linked list by creating nodes and linking them


        List<Integer> mergedList = new ArrayList<>();
        while (head1 != null) {
            mergedList.add(head1.data);
            head1 = head1.next;
        }
        while (head2 != null) {
            mergedList.add(head2.data);
            head2 = head2.next;
        }

        Collections.sort(mergedList);

        // use 1st item of list to create a node to start of linked list
        ListNode head = null;
        if(mergedList.size() > 0) {
            head = new ListNode(mergedList.get(0));
            ListNode current = head;

            for (int i = 1; i < mergedList.size(); i++) {
                current.next = new ListNode(mergedList.get(i));
                current = current.next;
            }
            current.next = null;
        }

        return head;
    }

    // Recursive solution
    public static ListNode mergeLists2(ListNode head1, ListNode head2) {

        if(head1 == null) {
            return head2;
        }
        if(head2 == null) {
            return head1;
        }

        if(head1.data < head2.data) {
            head1.next = mergeLists2(head1.next, head2); // move to new head before returning node
            return head1;
        } else {
            head2.next = mergeLists2(head1, head2.next);  // same here
            return head2;
        }
    }
}
