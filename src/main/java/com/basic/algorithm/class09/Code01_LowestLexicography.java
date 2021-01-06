package com.basic.algorithm.class09;

import java.util.*;

public class Code01_LowestLexicography {

    public static String lowestString1(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        List<String> list = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        process(strs, 0, set, "", list);
        String lowest = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).compareTo(lowest) < 0) {
                lowest = list.get(i);
            }
        }
        return lowest;
    }

    private static void process(String[] strs, int index, Set<Integer> set, String path, List<String> list) {
        if (index == strs.length) {
            list.add(path);
        } else {
            for (int i = 0; i < strs.length; i++) {
                if (!set.contains(i)) {
                    set.add(i);
                    process(strs, index + 1, set, path + strs[i], list);
                    set.remove(i);
                }
            }
        }
    }

    public static class MyComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return (o1 + o2).compareTo(o2 + o1);
        }
    }

    public static String lowestString2(String[] strings) {
        if (strings == null || strings.length == 0) {
            return "";
        }
        Arrays.sort(strings, new MyComparator());
        String res = "";
        for (int i = 0; i < strings.length; i++) {
            res += strings[i];
        }
        return res;
    }

    public static void main(String[] args) {
        String[] strings = {"b", "ba", "bbc", "bdc"};
        System.out.println(lowestString1(strings));
        System.out.println(lowestString2(strings));
    }
}
