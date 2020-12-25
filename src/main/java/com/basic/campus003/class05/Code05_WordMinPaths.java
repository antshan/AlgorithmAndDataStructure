package com.basic.campus003.class05;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Code05_WordMinPaths {

    public static List<List<String>> findMinPaths(String start, String to, List<String> list) {
        list.add(start);
        Map<String, List<String>> nexts = getNexts(list);
        Map<String, Integer> distance = getDistances(start, nexts);
        LinkedList<String> pathList = new LinkedList<>();
        List<List<String>> res = new LinkedList<>();
        getShortestPaths(start,to,nexts,distance,pathList,res);
        return res;
    }

    private static void getShortestPaths(String start, String to, Map<String, List<String>> nexts, Map<String, Integer> distance, LinkedList<String> pathList, List<List<String>> res) {
        pathList.add(start);
        if (start.equals(to)){
            res.add(new LinkedList<>(pathList));
        }else {
            for (String next : nexts.get(start)){
                if (distance.get(next) == distance.get(start) + 1){
                    getShortestPaths(next,to,nexts,distance,pathList,res);
                }
            }
        }
        pathList.pollLast();
    }

    private static Map<String, Integer> getDistances(String start, Map<String, List<String>> nexts) {
        Map<String, Integer> distances = new HashMap<>();
        distances.put(start, 0);
        Queue<String> queue = new LinkedList<>();
        queue.add(start);
        Set<String> set = new HashSet<>();
        set.add(start);
        while (!queue.isEmpty()){
            String cur = queue.poll();
            for (String next : nexts.get(cur)){
                if (!set.contains(next)){
                    distances.put(next, distances.get(cur) + 1);
                    set.add(next);
                    queue.add(next);
                }
            }
        }
        return distances;
    }

    private static Map<String, List<String>> getNexts(List<String> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        Set<String> dict = new HashSet<>(list);
        Map<String, List<String>> res = new HashMap<>();
        for (String str : list) {
            res.put(str, getNext(str, dict));
        }
        return res;
    }

    private static List<String> getNext(String str, Set<String> dict) {
        List<String> list = new ArrayList<>();
        char[] chars = str.toCharArray();
        for (char i = 'a'; i <= 'z'; i++) {
            for (int j = 0; j < chars.length; j++) {
                if (chars[j] != i){
                    char tmp = chars[j];
                    chars[j] = i;
                    if (dict.contains(String.valueOf(chars))){
                        list.add(String.valueOf(chars));
                    }
                    chars[j] = tmp;
                }
            }
        }
        return list;
    }

    public static void main(String[] args) {
        String start = "abc";
        String end = "cab";
        String[] test = { "abc", "cab", "acc", "cbc", "ccc", "cac", "cbb",
                "aab", "abb" };
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < test.length; i++) {
            list.add(test[i]);
        }
        List<List<String>> res = findMinPaths(start, end, list);
        for (List<String> obj : res) {
            for (String str : obj) {
                System.out.print(str + " -> ");
            }
            System.out.println();
        }

    }
}
