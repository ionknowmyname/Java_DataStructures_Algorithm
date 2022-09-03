package com.faithfulolaleru.base;

public class DoubleListNode {
    public Integer data;
    public DoubleListNode next;
    public DoubleListNode prev;



    public DoubleListNode(){
        this.data = null;
        this.next = null;
        this.prev = null;
    }
    public DoubleListNode(Integer data){
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}
