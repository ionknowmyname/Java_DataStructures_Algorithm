package com.faithfulolaleru.HACKERRANK;

import com.faithfulolaleru.base.ListNode;

import java.util.HashSet;

public class LinkedList {


    public static void main(String[] args) {
        LinkedList ll = new LinkedList();

        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        ListNode d = new ListNode(4);
        ListNode e = new ListNode(5);

        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;


        System.out.println(ll.addValueAtEnd(a, 6).data);
        System.out.println(ll.addValueAtIndex(a, 3, 2).data);
        System.out.println(ll.deleteNodeAtStart(a).data);
    }



    // WORKING
    public ListNode addValueAtEnd(ListNode head, Integer value){
        ListNode newNode = new ListNode(value);
        ListNode current = head;

        if(head == null) {
            return newNode;
        }

        while(current.next != null){
            current = current.next;
        }

        current.next = newNode;
        newNode.next = null;

        return head;
    }

    // WORKING
    public ListNode addValueAtIndex(ListNode head, Integer value, Integer index){
        ListNode newNode = new ListNode(value);
        ListNode current = head;
        int counter = 1;

        while(current != null){
            if(counter == index){
                ListNode temp = current.next;
                current.next = newNode;
                newNode.next = temp;
            }

            current = current.next;
            counter++;
        }

        return head;
    }

    // WORKING
    public ListNode deleteNodeAtStart(ListNode head){
        head = head.next;

        return head;
    }

    // WORKING
    public ListNode deleteNodeAtIndex(ListNode head, int index){

        ListNode current = head;
        int counter = 1;

        if(index == 0) {
            head = head.next;
            return head;
        }

        while(current != null){
            if(counter == index && current.next != null && current.next.next != null){
                current.next = current.next.next;
            }

            current = current.next;
            counter++;
        }

        return head;
    }

    // only works for sorted Linked lists   // WORKING
    public ListNode removeDuplicates(ListNode head){
        ListNode temp = head.next;
        ListNode prev = head;

        if(head == null) {
            return null;
        }

        while(temp != null) {

            if(prev.data == head.data) {   // there's duplicate
                prev.next = temp.next;  // deletes temp node by linking prev node to node after temp node
                temp.next=null;
                temp = prev.next;
            } else {
                prev = temp;  // set prev to the temp value before temp value is moved to next
                temp = temp.next;
            }
        }

        return head;
    }

    // also works for unsorted linked list   // WORKING
    public ListNode removeDuplicates2(ListNode head){
        HashSet<Integer> hs = new HashSet<>();
        ListNode current = head;
        ListNode prev = null;  // track current, its 1 step behind current

        if(head == null) {
            return null;
        }

        while(current != null) {
            int value = current.data;
            if(hs.contains(value)){
                prev.next = current.next;  // deletes current node by linking prev node to node after current node
            } else {
                hs.add(value);
                prev = current;  // set prev to the current value before current value is moved to next
            }
            current = current.next;
        }

        return head;
    }

    public int hasCycle(ListNode head) {
        // Linked list has cycle if a node is visisted more than once
        // return 1 if it has cycle and 0 if it doesn't



        return 1;
    }

    // WORKING
    public int findMergeNode(ListNode head1, ListNode head2) {
        ListNode current1 = head1;

        while (current1 != null) {
            ListNode current2 = head2;

            while(current2 != null) {
                if(current1 == current2) {
                    return current1.data;
                }
                current2 = current2.next;
            }
            current1 = current1.next;
        }
        return 0;
    }
}
