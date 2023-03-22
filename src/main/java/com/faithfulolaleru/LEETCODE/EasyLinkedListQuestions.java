package com.faithfulolaleru.LEETCODE;

import com.faithfulolaleru.base.ListNode;

import java.util.HashSet;
import java.util.Set;

public class EasyLinkedListQuestions {

    // 141. Linked List Cycle
    public boolean hasCycle(ListNode head) {
        if(head == null) return false;

        ListNode current = head;
        Set<ListNode> set = new HashSet<>();

        while(current != null) {
            if(set.contains(current)) return true;

            set.add(current);
            current = current.next;
        }

        return false;
    }
}
