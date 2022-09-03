package com.faithfulolaleru;

import com.faithfulolaleru.base.ListNode;

public class QueueLinkedList {

    //  add the nodes at the end of linkedlist would add them to the queue
    // remove nodes from the beginning to remove them from queue
    // no need to instantiate size
    // no need for circular queue using linked list coz we dont have space/size issues


    LinkedList linkedList;



    public QueueLinkedList(){

        linkedList = new LinkedList();
        // linkedList.head = new ListNode();  // might be unnecessary

        System.out.println("Queue successfully created");
    }


    public static void main(String[] args) {
        QueueLinkedList qll = new QueueLinkedList();

    }




    public boolean isEmpty(){
        if(linkedList.head == null){
            return true;
        } else {
            return false;
        }
    }

    public void enqueue(Integer value){
        // insert to linked list
        linkedList.addValueAtIndexWithoutHead(value, linkedList.size);  //insert at the end
        System.out.println("Inserted " + value + " in Stack");

        // OR

        // Do a fresh add at end of linked list here

        /*ListNode newNode = new ListNode(value);
        ListNode head = linkedList.head;
        if(head == null){ // no list, create list  // technically this cant happen coz list is instantiated in constructor, bt just in case
            head = newNode;
            head.next = null;
            return;
        } else {
            newNode.next = null;
            head = newNode;
        }*/
    }

    public Integer dequeue(){
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

    public void deleteQueue(){
        linkedList.head = null;
        linkedList.tail = null;
        System.out.println("Successfully deleted Queue");
    }
}
