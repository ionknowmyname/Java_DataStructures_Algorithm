package com.faithfulolaleru.Stack;

import com.faithfulolaleru.LinkedList.LinkedList;

public class StackWithLinkedList {

    //  add the nodes at the beginning of linkedlist would add them to the stack
    // removing nodes from the beginning of linkedlist removes them from stack
    // no need to instantiate size


    LinkedList linkedList;
    int topOfStack;


    public StackWithLinkedList(){

        linkedList = new LinkedList();
        // linkedList.head = new ListNode();  // might be unnecessary
    }


    public static void main(String[] args) {
        StackWithLinkedList sll = new StackWithLinkedList();
        sll.push(1);
        sll.push(2);
        sll.push(3);
    }



    public void push(Integer value){
        // insert to linked list
        linkedList.addValueAtIndexWithoutHead(value, 0);  // index of 0 coz we adding at the beginning of linked list
        System.out.println("Inserted " + value + " in Stack");

        // OR

        // Do a fresh add at start of linked list here

        /*ListNode newNode = new ListNode(value);
        ListNode head = linkedList.head;
        if(head == null){ // no list, create list  // technically this cant happen coz list is instantiated in constructor, bt just in case
            head = newNode;
            return;
        } else {
            newNode.next = head;
            head = newNode;
        }*/
    }

    public boolean isEmpty(){
        if(linkedList.head == null){
            return true;
        } else {
            return false;
        }
    }

    public Integer pop(){
        Integer returnValue;
        if(isEmpty()){
            System.out.println("Linked list is empty");
            returnValue = -1;
        } else{
            returnValue = linkedList.head.data;
            linkedList.head = linkedList.head.next;
        }
        return returnValue;
    }

    public Integer peek(){
        // Integer returnValue;
        if(isEmpty()){
            System.out.println("Linked list is empty");
            return -1;
        } else{
            return linkedList.head.data;
        }
        // return returnValue;
    }

    public void deleteStack(){
        linkedList.head = null;
        linkedList.tail = null;
        System.out.println("Successfully deleted Stack");
    }
}
