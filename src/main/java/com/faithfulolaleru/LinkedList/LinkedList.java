package com.faithfulolaleru.LinkedList;


import com.faithfulolaleru.base.ListNode;

import java.util.ArrayList;
import java.util.List;

public class LinkedList {

    public ListNode head;
    public ListNode tail;
    public int size;



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


        // ll.traverse(a);
        // ll.traverse();
        // System.out.println("Array from Linked List: " + ll.addLinkedListValuesToArray(a));
        //System.out.println("Sum of Linked List values: " + ll.sumUpLinkedListValues(a));
        // ll.addValueAtStart(a, 6);
         ll.addValueAtIndex(a, 2, 6);
        // ll.addValueAtEnd(a, 7);
        // System.out.println("Value at Node index: "  + ll.returnValueAtNodeIndex(a, 2));
        // ll.deleteNodeAtStart(a);
        // ll.deleteNodeAtEnd(a);
        // ll.deleteNodeAtIndex(a, 2);
    }



    public void traverse(ListNode head){
        // ITERATIVE

        ListNode current = head;
        while(current != null){
            System.out.print(current.data + " --> ");
            current = current.next;
        }
        System.out.println("null");


        // RECURSIVE
//        if(head == null) {
//            System.out.println("null");
//            return; // to end at null and avoid null pointer
//        }
//        System.out.print(head.data + " --> ");
//        traverse(head.next);

    }

    public List<Integer> addLinkedListValuesToArray(ListNode head){
        // ITERATIVE

        /*List<Integer> list = new ArrayList<>();
        ListNode current = head;
        while(current != null){
            list.add(current.data);
            current = current.next;
        }
        return list;*/


        // RECURSIVE
        List<Integer> list = new ArrayList<>();
        fillValues(head, list);
        return list;
    }

    public void fillValues(ListNode head, List<Integer> list){
        if(head == null) return;
        list.add(head.data);
        fillValues(head.next, list);
    }

    public Integer sumUpLinkedListValues(ListNode head){
        // ITERATIVE

        /*Integer sum = 0;
        ListNode current = head;
        while(current != null){
            sum += current.data;
            current = current.next;
        }
        return sum;*/


        // RECURSIVE
        if (head == null) return 0;
        return head.data + sumUpLinkedListValues(head.next);
    }

    //////////////////////////////////////////////////////////

    public void addValueAtIndexWithoutHead(Integer value, Integer index){
        ListNode newNode = new ListNode(value);
        if(head == null){ // no list, create list
            head = newNode;
            size = 1;
        } else if (index == 0) {  // add at the beginning if list already exist
            newNode.next = head;
            head = newNode;
            size++;
        } else if (index >= size) { // add at the end
            newNode.next = null; // after the last node is null, bt this is redundant coz of ListNode constructor
            // tail is last node, tail.next is supposed to be null, bt we add our new node,
            // and move the tail to the new node we just added
            tail.next = newNode;
            tail = newNode;
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
        }

        // size++;
    }

    public void deleteValueAtIndexWithoutHead(Integer index){

        if(head == null){ // no list
            System.out.println("Linked list does not exist");
            return;
        } else if (index == 0) {  // delete at the beginning of list
            head = head.next;  // if there was only 1 node, head.next would be equal to null
           size--;
           if(size == 0){ //if there was only 1 node, after deleting, it'll be 0 nodes left, so also set tail to null
               tail = null;
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
            current.next = null;
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

    ///////////////////////////////////////////////////////////////

    public void addValueAtStart(ListNode head, Integer value){
        ListNode current = new ListNode(value);
        //  System.out.print(current.data + " --> ");
        current.next = head;
        while(current != null){
            System.out.print(current.data + " --> ");
            current = current.next;
        }
        System.out.print("null");
    }

    public void addValueAtIndex(ListNode head, Integer index, Integer value){
        ListNode newNode = new ListNode(value);
        ListNode current = head;
        int counter = 1;
        while(current != null){
            if(counter == index){
                ListNode nextNext = current.next;
                current.next = newNode;
                newNode.next = nextNext;
            }
            System.out.print(current.data + " --> ");
            current = current.next;
            counter++;
        }
        System.out.print("null");


        // OR


        /*ListNode newNode = new ListNode(value);
        ListNode current = head;
        for (int i = 0; i < index-1; i++) { // for loop gets us to the node before the index node we wanna add
            System.out.print(current.data + " --> ");
            current = current.next;
        }

        ListNode nextNext = current.next;
        current.next = newNode;
        newNode.next = nextNext;

        for (int i = index+1; i < 10; i++) { // for loop gets us from after adding till the end     // i can be index or index+1
            // this if statement is coz we don't know end of list & null pointer is throwing when current is null
            // can also use try-catch to catch null pointer exception
            if(current == null) {
                System.out.println("null");
                return;
            }
            System.out.print(current.data + " --> ");
            current = current.next;
        }*/

    }

    public void addValueAtEnd(ListNode head, Integer value){
        ListNode newNode = new ListNode(value);
        ListNode current = head;
        while(current != null){
            System.out.print(current.data + " --> ");
            current = current.next;
        }

        current = newNode;
        System.out.print(current.data + " --> null");
    }

    public int returnValueAtNodeIndex(ListNode head, int index){
        ListNode current = head;
        int counter = 0;
        while(current != null){
            if(counter == index) return current.data;
            counter++;
            current = current.next;
        }
        return -1;
    }

    public void deleteNodeAtStart(ListNode head){
        // if only one node

        // if more than one node
        ListNode current = head;
        current = current.next;
        while(current != null){
            System.out.print(current.data + " --> ");
            current = current.next;
        }
        System.out.print("null");

    }

    public void deleteNodeAtIndex(ListNode head, int index){
        ListNode current = head;
        for (int i = 0; i < index-1; i++) { // for loop gets us to the node before the index node we wanna delete
            System.out.print(current.data + " --> ");
            current = current.next;
        }
        current.next = current.next.next; // node located after the next one, so it deletes the next node

        // try{
            for (int i = index; i < 8; i++) { // for loop gets us from after delete till the end
                // this if statement is coz we don't know end of list & null pointer is throwing when current is null
                // can also use try-catch to catch null pointer exception
                if(current == null) {
                    System.out.println("null");
                    return;
                }
                System.out.print(current.data + " --> ");
                current = current.next;
            }
//        } catch (NullPointerException ex){
//            System.out.println("null");
//            return;
//        }



        // OR

        // Use while loop,
        // check addValueAtIndex();
    }

    public void deleteNodeAtEnd(ListNode head){
        // loop to find node before last node,
        //then beforelastNode.next = null
        // tail = beforelastNode

        ListNode current = head;
        while(current != null){
            if(current.next == null) {
                // current = null;
                System.out.println("null");
                return;
            }
            System.out.print(current.data + " --> ");
            current = current.next;
        }
        // System.out.println("null");
    }




}
