package com.faithfulolaleru.INTERVIEWS;

import java.util.HashMap;
import java.util.Map;

public class MusalaSoft {


    public static int getMin(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            Character current = s.charAt(i);
            map.put(current, map.getOrDefault(current, 0) + 1);
        }

        count = Math.abs(map.getOrDefault('(', 0) - map.getOrDefault(')', 0));

        return count;
    }
}
