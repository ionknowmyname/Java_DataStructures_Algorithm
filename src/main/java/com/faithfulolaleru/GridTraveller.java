package com.faithfulolaleru;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GridTraveller {

    /*
    *   How many ways can you travel from the top left to bottom right in a m x n grid
    * */


    public static void main(String[] args) {
//        System.out.println(pureRecursionSolution(1, 1));
//        System.out.println(pureRecursionSolution(2, 3));
//        System.out.println(pureRecursionSolution(3, 2));
//        System.out.println(pureRecursionSolution(3, 3));
        // System.out.println(pureRecursionSolution(18, 18));  // would take too long

//        System.out.println();
//        System.out.println(dynamicProgrammingSolution(1, 1, new HashMap<>()));
//        System.out.println(dynamicProgrammingSolution(2, 3, new HashMap<>()));
//        System.out.println(dynamicProgrammingSolution(3, 2, new HashMap<>()));
//        System.out.println(dynamicProgrammingSolution(3, 3, new HashMap<>()));
        System.out.println(dynamicProgrammingSolution(18, 18, new HashMap<>()));  // still taking too long
    }


    // pure recursion
    public static int pureRecursionSolution(int m, int n) {
        if(m == 1 && n == 1) return 1;  // only 1 way to travel a 1x1 gird
        if(m == 0 || n == 0) return 0;   // no grid if there's a 0

        return pureRecursionSolution(m - 1, n) + pureRecursionSolution(m, n - 1);

        // coz once you go either right(n-1) or down(m-1), the available options decrease by 1
        // reducing m & n simultaneously would mean you moving diagonally which ain't allowed according to the problem
    }

    // memoization
    // impl is faulty
    public static int dynamicProgrammingSolution(int m, int n, Map<int[], Integer> map) {

        int[] mnArray = {m, n};
        // int[] mnArray2 = {n, m};  // coz 2x3 = 3x2

        if(map.containsKey(mnArray)) return map.get(mnArray);  // solution for m & n already in map, use it
        // || map.containsKey(mnArray2)

        if(m == 1 && n == 1) return 1;  // only 1 way to travel a 1x1 gird
        if(m == 0 || n == 0) return 0;   // no grid if there's a 0

        // not in map yet, add it before continuing
        int ans = dynamicProgrammingSolution(m - 1, n, map) + dynamicProgrammingSolution(m, n - 1, map);
        map.put(mnArray, ans);
        // map.put(mnArray2, ans);

        return map.get(mnArray);
    }
}

