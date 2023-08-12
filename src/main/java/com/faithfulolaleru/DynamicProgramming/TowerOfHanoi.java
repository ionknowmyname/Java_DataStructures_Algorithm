package com.faithfulolaleru.DynamicProgramming;

public class TowerOfHanoi {

    /*
    *   shift 3 disks from tower A to tower C, only tower B is intermediate,
    *   can only move 1 disk at a time;
    *   larger disk cannot be on top of smaller disk at any time
    *
    *
    * */

    public static void main(String[] args) {
        int n = 3;  // using 3 disks
        shiftDisk(n, 'A', 'B', 'C');
    }

    public static void shiftDisk(int n, char tA, char tB, char tC) {
        if(n == 1) {  // smallest disk, can only move from A to C
            System.out.println("Disk 1 shifted from " + tA + " to " + tC);
        } else {
            shiftDisk(n - 1, tA, tC, tB); // move disk from A to B, using C as intermediate
            System.out.println("Disk " + n + " from " + tA + " is shifted to " + tC);
            shiftDisk(n - 1, tB, tA, tC); // move disk from B to C, using A as intermediate
        }
    }
}
