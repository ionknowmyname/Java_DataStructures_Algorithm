package com.faithfulolaleru.LinkedList;

import com.faithfulolaleru.base.DoubleListNode;

import java.util.HashSet;

public class DoublyLinkedList {

    public DoubleListNode head;
    public DoubleListNode tail;
    public int size;

    public static void main(String[] args) {
        CircularLinkedList circularLinkedList = new CircularLinkedList();

    }



    public DoubleListNode CreateDLLWithNode(Integer newValue){
        head = new DoubleListNode();
        DoubleListNode newNode = new DoubleListNode(newValue);
        newNode.next = null;
        newNode.prev = null;
        head = newNode;
        tail = newNode;
        size = 1;
        return head;
    }


    public void addValueAtIndexWithoutHead(Integer value, Integer index){
        DoubleListNode newNode = new DoubleListNode(value);
        if(head == null){ // no list, create list
            head = newNode;
            size = 1;
            return;
        } else if (index == 0) {  // add at the beginning if list already exist
            newNode.next = head;
            newNode.prev = null;  // newNode is now the 1st node, and prev for first node is always null
            head.prev = newNode;   // sets the former first node's prev to this new first node, making it doubly linked
            head = newNode;
            size++;
        } else if (index >= size) { // add at the end
            newNode.next = null; // after the last node is null, bt this is redundant coz of ListNode constructor
            // tail is last node, tail.next is supposed to be null, bt we add our new node,
            // and move the tail to the new node we just added
            tail.next = newNode;    // connect forward
            newNode.prev = tail;  // connect backwards
            tail = newNode;
            size++;
        } else { // add at the index
            DoubleListNode current = head;
            int counter = 0;
            while(counter < index -1){
                current = current.next;
                counter++;
            }
            DoubleListNode nextnode = current.next;
            current.next = newNode;   // connect forward
            newNode.prev = current;   // conect backward
            newNode.next = nextnode;   // connect forward  // OR newNode.next = current.next
            nextnode.prev = newNode;   // connect backward // OR newNode.next.prev = newNode
            size++;
        }

    }


    public void reverseTraverseWithoutHead(){
        if(head != null){
            DoubleListNode current = tail;
            for (int i = 0; i < size; i++) {   // OR while(current != head)
                System.out.println(current.data);
                if(i != size-1){
                    System.out.println(" <-- ");
                }
                current = current.prev;
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

            if(size == 1){ //if there was only 1 node, deleting it deletes the list
                head = null;
                tail = null;
                size--;
                return;
            } else {
                head = head.next;
                head.prev = null;   // sets new head prev to null coz former head is deleted, & prev for 1st node is always null
                size--;
            }
        } else if (index >= size) { // delete from  the end
            DoubleListNode current = tail.prev;
            // when we couldnt get tail.prev as in singly linked list, we had to loop from head till we got to the node just before last node

            if(size == 1) { // means only one node in list
                head = tail = null; // set both head & tail to null
                size--;
                return;
            } else {    // if there was more than one node, just delete last node by below
                current.next = null;
                tail = current;
                size--;
            }

        } else { // delete at the index
            DoubleListNode current = head;
            for (int i = 0; i < index-1; i++) { // for loop gets us to the node before the index node we wanna delete
                current = current.next;
            }
            current.next = current.next.next; // node located after the next one, so it deletes the next node
            current.next.prev = current;  // connect backwards  // current.next's prev = current
            size--;
        }

    }

    public void deleteEntireDLL(){
        DoubleListNode current = head;
        for (int i = 0; i < size; i++) {  // delete all back connections
            current.prev = null;
            current = current.next;
        }
        head = null;
        tail = null;
    }

    public void deleteDuplicateFromList(DoublyLinkedList list){
        HashSet<Integer> hs = new HashSet<>();
        DoubleListNode current = list.head;
        DoubleListNode prev = null;  // created to track current, 1 step behind him

        while(current != null){
            int value = current.data;
            if(hs.contains(value)){
                prev.next = current.next;  // deletes current node by linking prev node to node after current node
                list.size--;
            } else {
                hs.add(value);
                prev = current;  // set prev to the current value before current value is moved to next
            }
            current = current.next;
        }

    }

    public Integer returnNthValueToTheLast(DoublyLinkedList list, int index){
        /*DoubleListNode current = list.tail;
        int counter  = 1;
        while(current != null){
            if(counter == index){
                return current.data;
            }
            current = tail.prev;
            tail = current;  // OR tail = tail.prev
            counter++;
        }
        return -1;*/



        // OR

        // create two pointers, both point to head, then move one pointer forward by index, then we move both pointers simultaneously
        // so when that pointer gets to null a.k.a the end, the back pointer is at the index we want, then we return value

        DoubleListNode current1 = list.head;
        DoubleListNode current2 = list.head;

        for (int i = 0; i < index; i++) { // move current2 forward till index
            if(current2 == null) return null;

            current2 = current2.next;
        }

        while(current2 != null){  // move both simultaneously forward
            current1 = current1.next;
            current2 = current2.next;
        }

        return current1.data;
    }


    public DoublyLinkedList partitionLinkedListAroundAValue(DoublyLinkedList list, int value){
        DoubleListNode current = list.head;
        list.tail = list.head;

        while(current != null){
            DoubleListNode nextNext = current.next;
            if(current.data < value){  // move to the beginning
                current.next = list.head;
                list.head = current;
            } else {  // move to the end
                list.tail.next = current;
                list.tail = current;
            }
            current = nextNext;
        }

        list.tail.next = null;
        return list;
    }


    public DoublyLinkedList sumTwoListsTogether(DoublyLinkedList A, DoublyLinkedList B){
        // Each node in linked list represents a digit of the number in reverse, so add both lists and return
        // the result as a Linked list where each node is also the digits of the sum
        // e.g 2 -> 1 -> 4 = 412; 2 -> 1 -> 3 = 312; sum is 724 = 4 -> 2 -> 7

        DoubleListNode currentA = A.head;
        DoubleListNode currentB = B.head;
        int carry = 0;
        DoublyLinkedList resultList = new DoublyLinkedList();

        while(currentA != null || currentB != null){
            int result = carry;
            if(currentA != null){
                result += currentA.data;
                currentA = currentA.next;
            }
            if(currentB != null){
                result += currentB.data;
                currentB = currentB.next;
            }

            // use last digit of result to create a new node in resultList  // result % 10
            resultList.head = new DoubleListNode(result % 10);

            /*DoubleListNode head = new DoubleListNode();
            DoubleListNode newNode = new DoubleListNode(result % 10);
            newNode.next = head;*/

            carry = result / 10;  // e.g 6+6 = 12, put 2 into the resultList and carry 1
        }

        return resultList;
    }


    public DoubleListNode returnIntersectingNodeBtwTwoLinkedList(DoublyLinkedList list1, DoublyLinkedList list2){
        if(list1.head == null || list2.head == null) return null;

        if(list1.tail != list2.tail){ // the two list dont share same tail, meaning they dont intersect
            return null;
        }
        DoubleListNode shorter = new DoubleListNode();
        DoubleListNode longer = new DoubleListNode();
        if(list1.size > list2.size){
            longer = list1.head;
            shorter = list2.head;
        } else {
            longer = list2.head;
            shorter = list1.head;
        }

        longer = getKthNode(longer, Math.abs(list1.size - list2.size));  // remove beginning excess nodes from longer list

        while(shorter != longer){
            shorter = shorter.next;
            longer = longer.next;
        }

        return longer;  // tutorial claimed you can return either longer or shorter, they should be same
    }

    public DoubleListNode getKthNode(DoubleListNode head, int k){
        DoubleListNode current = head;
        while(k > 0 && current != null){
            current = current.next;
            k--;
        }
        return current;
    }

    public void addNodeAtEndOfTwoLinkedListSimultaneously(DoublyLinkedList A, DoublyLinkedList B, Integer value){
        // this method is just to add nodes to both linked lists simultaneously so they'd truly intersecting

        DoubleListNode newNode = new DoubleListNode(value);
        A.tail.next = newNode; // add new node to end of list A
        A.tail = newNode;
        B.tail.next = newNode; // add new node to end of list B
        B.tail = newNode;
    }

}
