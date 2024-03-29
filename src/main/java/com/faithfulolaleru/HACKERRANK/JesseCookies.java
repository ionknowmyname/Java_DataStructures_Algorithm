package com.faithfulolaleru.HACKERRANK;

import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class JesseCookies {


    // https://www.hackerrank.com/challenges/one-week-preparation-kit-jesse-and-cookies/


    // my solution, passed only 11/27 tests
    public static int cookies(int k, List<Integer> A) {

        if (A.size() < 1) return -1;   // empty list

        int sum = A.stream().mapToInt(Integer::intValue).sum();
        if (sum < k) return -1;   // all the values added together can never reach k

        int counter = 0;
        Collections.sort(A);


        while (A.get(0) < k && A.size() > 1) {
            int first = A.get(0);
            int second = A.get(1);

            A.remove(0);
            A.remove(1);

            int compute = first + (2 * second);
            A.add(compute);

            // Collections.sort(A);

            counter++;
        }


        return A.get(0) < k ? -1 : counter;
    }


    // using priority queue like everyone else
    public static int cookies2(int k, List<Integer> A) {

        Queue<Integer> q = new PriorityQueue<>(A);
        int counter = 0;

        while (q.size() > 0) {
            if (q.peek() >= k) {  // smallest value already bigger thank k, return counter as is
                return counter;
            }

            if (q.size() == 1) {
                if (q.peek() < k) {
                    break;
                } else {
                    return counter;
                }
            }

            q.add(q.poll() + (q.poll() * 2));
            counter++;
        }

        return -1;
    }
}
