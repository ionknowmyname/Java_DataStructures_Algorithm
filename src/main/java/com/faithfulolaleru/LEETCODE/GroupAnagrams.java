package com.faithfulolaleru.LEETCODE;

import java.util.*;

public class GroupAnagrams {


    public static void main(String[] args) {
        System.out.println(groupAnagrams2(new String[]{"eat","tea","tan","ate","nat","bat"}));
    }


    // 49. Group Anagrams
    public static List<List<String>> groupAnagrams(String[] strs) {

        /*
        *   EXPLANATION: after sorting the strings using char array, if string not yet in hashmap, meaning
        *   we just encountering that string for 1st time, we put it & initialize its list values to new arraylist.
        *   if the map already contains the key, meaning this new string is an anagram of the already contained key,
        *   so we add it to list of values for that key
        *   PS: so long as its sorted, it'll always catch the anagrams e.g ate & tea when sorted both = aet
        *
        * */

        if (strs == null) return null;
        if (strs.length == 0) return new ArrayList<>();

        Map<String, List<String>> hashMap = new HashMap<>();

        for(int i = 0; i < strs.length; i++){
            char[] chArr = strs[i].toCharArray();
            Arrays.sort(chArr);
            String keyStr = String.valueOf(chArr);

            if (!hashMap.containsKey(keyStr)) {
                hashMap.put(keyStr, new ArrayList<>());
            }

            hashMap.get(keyStr).add(strs[i]);
        }

        return new ArrayList<>(hashMap.values());
    }

    public static List<List<String>> groupAnagrams2(String[] strs) {

        if (strs == null) return null;
        if (strs.length == 0) return new ArrayList<>();

        Map<String, List<String>> hashMap = new HashMap<>();

        for(String s : strs) {

            char[] valArr = s.toCharArray();
            Arrays.sort(valArr);
            String key = new String(valArr);

            List<String> ll = hashMap.getOrDefault(key, new ArrayList<>());
            ll.add(s);
            hashMap.put(key, ll);

        }

        List<List<String>> ans = new ArrayList<>();
        for(Map.Entry<String, List<String>> entry : hashMap.entrySet()) {
            ans.add(entry.getValue());
        }

        return ans;
    }

}
