package com.faithfulolaleru.LinkedList;

import com.faithfulolaleru.base.ListNode;

public class CircularLinkedList {

    public ListNode head;
    public ListNode tail;
    public int size;

    public static void main(String[] args) {
        CircularLinkedList circularLinkedList = new CircularLinkedList();

    }

    public ListNode CreateCLLWithNode(Integer newValue){
        head = new ListNode();
        ListNode newNode = new ListNode(newValue);
        newNode.next = newNode;  // since its circular, last node points to first, since only 1 node, it points to itself
        head = newNode;
        tail = newNode;
        size = 1;
        return head;
    }

    public void addValueAtIndexWithoutHead(Integer value, Integer index){
        ListNode newNode = new ListNode(value);
        if(head == null){ // no list, create list
            head = newNode;
            size = 1;
        } else if (index == 0) {  // add at the beginning if list already exist
            newNode.next = head;
            head = newNode;
            tail.next = head; // points last node back to first node
            size++;
        } else if (index >= size) { // add at the end
            tail.next = newNode;
            tail = newNode;
            tail.next = head;  // newly added node at end point to first node
            size++;
        } else { // add at the index
            ListNode current = head;
            int counter = 0;
            while(counter < index -1){
                current = current.next;
                counter++;
            }
            ListNode nextnode = current.next;
            current.next = newNode;
            newNode.next = nextnode;
            size++;

            // OR

            /*newNode.next = current.next;
            current.next = newNode;
            size++;*/
        }

    }

    public void traverseWithoutHead(){
        if(head != null){
            ListNode current = head;
            for (int i = 0; i < size; i++) {
                System.out.println(current.data);
                current = current.next;
                if(i != size-1){
                    System.out.println(" --> ");
                }
            }
        } else {
            System.out.println("Linked List does not exist");
        }
    }



    public void deleteValueAtIndexWithoutHead(Integer index){

        if(head == null){ // no list
            System.out.println("Linked list does not exist");
            return;
        } else if (index == 0) {  // delete at the beginning of list
            head = head.next;  // if there was only 1 node, head.next would be equal to null
            tail.next = head;  // set tail to the new head
            size--;
            if(size == 0){ //if there was only 1 node, after deleting, it'll be 0 nodes left, so also set tail to null
                tail = null;
                head.next = null;
                head = null;
            }
        } else if (index >= size) { // delete from  the end
            ListNode current = head;
            // while(current.next != null)  // so as to stop before last node
            for (int i = 0; i < size-1; i++) { // for loop gets us to the node before the last node
                current = current.next;
            }
            if(current == head) { // means only one node in list
                head = tail = null; // set both head & tail to null
                size--;
                return;
            }
            // if there was more than one node, just delete last node by below
            current.next = head;   // delete last node by pointing next back to head
            tail = current;
            size--;

        } else { // delete at the index
            ListNode current = head;
            for (int i = 0; i < index-1; i++) { // for loop gets us to the node before the index node we wanna delete
                current = current.next;
            }
            current.next = current.next.next; // node located after the next one, so it deletes the next node
            size--;
        }

    }
}
