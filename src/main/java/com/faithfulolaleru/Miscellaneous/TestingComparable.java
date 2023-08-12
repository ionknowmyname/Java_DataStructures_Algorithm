package com.faithfulolaleru.Miscellaneous;

public class TestingComparable implements Comparable<TestingComparable> {

    String name;

    public TestingComparable(String name) {
        this.name = name;
    }


    public static void main(String[] args) {
        TestingComparable t = new TestingComparable("Faithful");
        TestingComparable y = new TestingComparable("Faithfulzz");
        int result = t.compareTo(y);
        System.out.println("result: " + result);
    }

    @Override
    public int compareTo(TestingComparable t) {
        return this.name.compareTo(t.name);
    }
}
