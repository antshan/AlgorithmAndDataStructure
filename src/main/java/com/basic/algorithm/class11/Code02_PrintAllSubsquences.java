package com.basic.algorithm.class11;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Code02_PrintAllSubsquences {

    public static List<String> subs(String str) {
        char[] chars = str.toCharArray();
        List<String> ans = new ArrayList<>();
        process(chars, 0, "", ans);
        return ans;
    }

    private static void process(char[] chars, int index, String s, List<String> ans) {
        if (index == chars.length) {
            ans.add(s);
            return;
        }
        process(chars, index + 1, s, ans);
        process(chars, index + 1, s + chars[index], ans);
    }

    public static List<String> subsquencesNoRepeat(String str){
        char[] chars = str.toCharArray();
        Set<String> set = new HashSet<>();
        process1(chars, 0, "", set);
        List<String> ans = new ArrayList<>(set);
        return ans;
    }

    private static void process1(char[] chars, int index, String path, Set<String> set) {
        if (index == chars.length){
            set.add(path);
            return;
        }
        process1(chars, index + 1, path, set);
        process1(chars, index + 1, path + chars[index], set);
    }

    public static void main(String[] args) {
        String str = "aba";
        List<String> res = subs(str);
        for (String s : res) {
            System.out.println(s);
        }
        System.out.println("======");
        res = subsquencesNoRepeat(str);
        for (String s : res) {
            System.out.println(s);
        }

    }
}
