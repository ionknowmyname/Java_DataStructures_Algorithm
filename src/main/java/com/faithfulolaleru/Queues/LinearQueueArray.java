package com.faithfulolaleru.Queues;

public class LinearQueueArray {

    int[] arr;  // int arr[]; also correct
    int topOfQueue;
    int beginningOfQueue;


    public LinearQueueArray(int size){
        this.arr = new int[size];
        this.topOfQueue = -1; // empty queue
        this.beginningOfQueue = -1;
        System.out.println("The Queue is created with size: " + size);
    }



    public static void main(String[] args) {
        LinearQueueArray newQueue = new LinearQueueArray(4);

    }




    public boolean isFull(){
        if(topOfQueue == arr.length -1){
            return  true;
        }else{
            return false;
        }
    }

    public boolean isEmpty(){
        if(beginningOfQueue == -1 || beginningOfQueue == arr.length) {
            // coz queues are emptied from front of array and beginning of queue is shifted back each time
            // so if its shifted back till it gets to array length, it means queue is empty
            return true;
        }else {
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
            topOfQueue++;
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
            beginningOfQueue++;

            if(beginningOfQueue > topOfQueue){  // means queue is empty
                // if beginning of queue == top of queue, it means thats the only element left in queue
                beginningOfQueue = topOfQueue = -1;
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
