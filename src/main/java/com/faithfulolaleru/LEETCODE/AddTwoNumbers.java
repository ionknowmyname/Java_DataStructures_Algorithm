package com.faithfulolaleru.LEETCODE;

import com.faithfulolaleru.base.ListNode;

public class AddTwoNumbers {



    // 2. Add Two Numbers    // Linked List
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode dummy = new ListNode(0); // creating a dummy list
        ListNode curr = dummy;
        int carry = 0;
        // while loop will run, until l1 OR l2 not reaches null OR if they both reaches null. But our carry has some value in it.
        // We will add that as well into our list
        while(l1 != null || l2 != null || carry == 1) {   // carry != 0 can also fly too
            int sum = 0;

            if(l1 != null) { // adding l1 to our sum & moving l1
                sum += l1.data;
                l1 = l1.next;
            }

            if(l2 != null) { // adding l2 to our sum & moving l2
                sum += l2.data;
                l2 = l2.next;
            }

            sum += carry;  // add carry to our sum
            carry = sum / 10;  // calc new carry
            ListNode newNode = new ListNode(sum % 10);
            curr.next = newNode;   // curr will point to that new node if we get
            curr = curr.next;  // update the current every time
        }
        return dummy.next; // return dummy.next bcz, we don't want the value we have consider in it intially!!
    }
}
