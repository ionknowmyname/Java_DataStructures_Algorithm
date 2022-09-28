package com.faithfulolaleru.Miscellaneous;

public class TestingCompareTo implements Comparable<TestingCompareTo> {

    String name;

    public TestingCompareTo(String name) {
        this.name = name;
    }


    public static void main(String[] args) {
        TestingCompareTo t = new TestingCompareTo("Faithful");
        TestingCompareTo y = new TestingCompareTo("Faithfulzz");
        int result = t.compareTo(y);
        System.out.println("result: " + result);
    }

    @Override
    public int compareTo(TestingCompareTo t) {
        return this.name.compareTo(t.name);
    }
}
