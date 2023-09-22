package com.faithfulolaleru.INTERVIEWS;

import java.util.List;

public class MoniePoint {

    // Balanced Array Hackerrank

    public static void main(String[] args) {

        System.out.println(balancedArray(List.of(1, 2, 3, 3)));
    }

    static int balancedArray(List<Integer> arr)
    {
//        List<Integer> list
//                = Arrays.stream(arr).boxed().collect(
//                Collectors.toList());
        for (int i = 1; i <= arr.size(); i++) {
            int leftSum = arr.subList(0, i)
                    .stream()
                    .mapToInt(x -> x)
                    .sum();

            int rightSum = arr.subList(i + 1, arr.size())
                    .stream()
                    .mapToInt(x -> x)
                    .sum();

            if (leftSum == rightSum)
                return i;
        }
        return -1;
    }
}
