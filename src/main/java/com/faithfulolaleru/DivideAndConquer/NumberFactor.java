package com.faithfulolaleru.DivideAndConquer;

public class NumberFactor {

    /*
    *   find number of ways to express N as a sum of a list of numbers
    *   E.g express 5 using (1,3,4);
    *   Ans is 6 ways, [ (1,1,1,1,1), (1,3,1), (3,1,1), (1,1,3), (4,1), (1,4) ]
    *
    * */


    public static void main(String[] args) {
        System.out.println(waysToGetN(4));  // should be 4
        System.out.println(waysToGetN(5));  // should be 6
        System.out.println(waysToGetN(6));  // should be 9
    }


    public static int waysToGetN(int n) {

        // establish base cases for recursive
        if(n == 0 || n == 1 || n == 2) return 1;
        // only 1 way to get 0, only 1 way to get 2 also (1,1)

        if(n == 3) return 2;  // (1,1,1) & (3)

        int sub1 = waysToGetN(n - 1);
        int sub2 = waysToGetN(n - 3);
        int sub3 = waysToGetN(n - 4);
        // coz we using (1,3,4) as numbers to make up n

        return sub1 + sub2 + sub3;
    }
}
