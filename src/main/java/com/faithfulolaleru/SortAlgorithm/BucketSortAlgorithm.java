package com.faithfulolaleru.SortAlgorithm;

import java.util.ArrayList;
import java.util.Collections;

public class BucketSortAlgorithm {

    int arr[];

    public BucketSortAlgorithm(int arr[]){
        this.arr = arr;
    }


    public static void main(String[] args) {
        int arr[] = {5,4,7,8,3,2,1,9,6};
        BucketSortAlgorithm bs = new BucketSortAlgorithm(arr);
        bs.printArray();
        bs.bucketSort();
        System.out.println();
        bs.printArray();
    }




    public void printArray(){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        // System.out.println();
    }

    public void printBucket(ArrayList<Integer>[] buckets){
        for (int i = 0; i < buckets.length; i++) {
            System.out.println("\nBucket #" + i + ": ");
            for (int j = 0; j < buckets[i].size(); j++) {
                System.out.print(buckets[i].get(j) + " ");
            }
        }
    }

    public void bucketSort(){
        // find number of buckets
        int numOfBuckets = (int) Math.ceil(Math.sqrt(arr.length));

        // find max value
        int maxValue = Integer.MIN_VALUE;
        for (int value : arr){
            if(value > maxValue){
                maxValue = value;
            }
        }

        // create buckets
        ArrayList<Integer>[] buckets = new ArrayList[numOfBuckets];

        // initialize empty buckets
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<Integer>();
        }

        // insert array elements inside buckets
        for(int value : arr){
            int bucketNumber = (int) Math.ceil(((float) value * numOfBuckets) / (float) maxValue);
            buckets[bucketNumber -1].add(value);     // bucketNumber -1  is coz buckets's first index is 0, so bucketNumber of 3 is actually buckets's 2nd index
        }


        System.out.println("\n\nPrinting buckets before sorting...");
        printBucket(buckets);


        // sorting buckets
        for(ArrayList<Integer> bucket : buckets){
            Collections.sort(bucket);
        }


        System.out.println("\n\nPrinting buckets after sorting...");
        printBucket(buckets);


        // Add buckets together
        int index = 0;
        for(ArrayList<Integer> bucket : buckets){
            for(int value : bucket){
                arr[index] = value;
                index++;
            }
        }
    }


}
