package com.faithfulolaleru.Miscellaneous;

public class TestingComparable implements Comparable<TestingComparable> {

    Integer name;

    public TestingComparable(Integer name) {
        this.name = name;
    }


    public static void main(String[] args) {
        TestingComparable t = new TestingComparable(4);
        TestingComparable y = new TestingComparable(5);
        int result = t.compareTo(y);
        System.out.println("result: " + result);
    }

    @Override
    public int compareTo(TestingComparable t) {
        return this.name.compareTo(t.name);
    }
}
