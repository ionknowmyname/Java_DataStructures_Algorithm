package com.faithfulolaleru.Queues;

public class CircularQueueArray {

    int[] arr;
    int topOfQueue;
    int beginningOfQueue;
    int size;


    public CircularQueueArray(int size){
        this.arr = new int[size];
        this.topOfQueue = -1; // empty queue
        this.beginningOfQueue = -1;
        this.size = size;
        System.out.println("Circular Queue is created with size: " + size);
    }


    public static void main(String[] args) {
        CircularQueueArray newQueue = new CircularQueueArray(4);

    }




    public boolean isEmpty(){
        if(topOfQueue == -1) {
            return true;
        }else {
            return false;
        }
    }

    public boolean isFull(){
        if(topOfQueue +1 == beginningOfQueue) {  // check this out again
            return true;
        } else if (beginningOfQueue == 0 && topOfQueue +1 == size) {
            // if queue starts from 0 and last array slot is taken
            return true;
        } else {
            return false;
        }
    }

    public void enQueue(int value){
        if (isFull()) {
            System.out.println("Queue is full");
        } else if (isEmpty()){  // queue is empty
            beginningOfQueue = 0;
            topOfQueue++;
            arr[topOfQueue] = value;
            System.out.println("Successfully added " + value + " to Queue");
        } else {  // stuff already in queue
            if(topOfQueue +1 == size){  // if top of queue is at the last array slot, i.e last space is taken
                topOfQueue = 0;   // set top of queue to the beginning to check if there's empty slots or not

                // this is what makes it cirular
            } else{
                topOfQueue++;
            }

            arr[topOfQueue] = value;
            System.out.println("Successfully added " + value + " to Queue");
        }
    }

    public int deQueue(){
        if(isEmpty()){
            System.out.println("Queue is empty");
            return -1;
        } else {
            int returnValue = arr[beginningOfQueue];
            arr[beginningOfQueue] = 0;  // set that spot to empty, 0 is our placeholder for empty

            if(beginningOfQueue == topOfQueue){  // means only 1 element in queue, if its removed, queue is empty
                beginningOfQueue = topOfQueue = -1;
            } else if (beginningOfQueue +1 == size) {  // has reached the end of array, so go back to beginning to remove next
                beginningOfQueue = 0;
            } else {
                beginningOfQueue++;
            }

            return returnValue;
        }
    }

    public int peek(){
        if(isEmpty()){
            System.out.println("Queue is empty");
            return -1;
        } else {
            return arr[beginningOfQueue];
        }
    }

    public void deleteQueue(){
        arr = null;
        System.out.println("Successfully deleted queue");
    }

}
