package com.faithfulolaleru.Miscellaneous;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TestingComparator implements Comparator<TestingComparator> {

    String name;

    public TestingComparator() {}

    public TestingComparator(String name) {
        this.name = name;
    }


    public static void main(String[] args) {
        TestingComparator x = new TestingComparator();
        TestingComparator t = new TestingComparator("abcdef");
        TestingComparator u = new TestingComparator("xdsfgdf");
        TestingComparator v = new TestingComparator("cdfgtht");
        TestingComparator w = new TestingComparator("zzzzzz");

        List<TestingComparator> toSort = Arrays.asList(t, u, v, w);


        List<TestingComparator> sorted = sortedList(toSort);
        List<String> sortedNames = sorted.stream().map(h -> h.name).collect(Collectors.toList());


        System.out.println(sortedNames);

//        int result = x.compare(t, y);
//        System.out.println("result: " + result);
    }


    @Override
    public int compare(TestingComparator o1, TestingComparator o2) {
        return o1.name.compareTo(o2.name);
    }

    public static List<TestingComparator> sortedList(List<TestingComparator> input) {
        Collections.sort(input, new TestingComparator());

        return input;
    }



    /*

    // when you are to compare with two things,  e.g sort player descending by score, if two players with the same
    // score, then sort ascending by name;

    // keep in mind default compareTo between a & b is ascending

    class Checker implements Comparator<Player> {
        // complete this method
        public int compare(Player a, Player b) {
            int comparing = Integer.compare(a.score, b.score);   // ascending
            if(comparing != 0)
                return comparing * -1;   // makes it descending
            return a.name.compareTo(b.name);
        }
    }

    */



    /*

    // MY SOLUTION

    public int compare(Player a, Player b) {
        Integer first = Integer.valueOf(b.score).compareTo(Integer.valueOf(a.score));

        if(first == 0) {  // same score
            return a.name.compareTo(b.name); // return sorted by name ascending
        }

        return first;
    }
    */
}
