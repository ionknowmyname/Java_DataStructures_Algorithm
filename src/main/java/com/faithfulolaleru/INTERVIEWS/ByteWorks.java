package com.faithfulolaleru.INTERVIEWS;

import java.util.ArrayList;
import java.util.List;

public class ByteWorks {

    public static void main(String[] args) {

    }

    public static List<Integer> rotLeft(int d, List<Integer> a) {

        int counter = 0;
        List<Integer> toReturn = new ArrayList<>();

        if(d == 0) return a;

        for (int i = d; i < a.size(); i++) {
            toReturn.add(a.get(i));
        }

        while(counter < d) {
            toReturn.add(a.get(counter));
            counter++;
        }

        return toReturn;
    }
}
