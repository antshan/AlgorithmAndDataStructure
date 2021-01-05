package com.basic.algorithm.class11;

import java.util.ArrayList;
import java.util.List;

public class Code03_PrintAllPermutations {

    public static List<String> permutation(String str){
        List<String> ans = new ArrayList<>();
        if (str == null || str.length() == 0){
            return ans;
        }
        char[] chars = str.toCharArray();
        process(chars, 0, ans);
        return ans;
    }

    private static void process(char[] chars, int index, List<String> ans) {
        if (index == chars.length){
            ans.add(String.valueOf(chars));
            return;
        }
        for (int j = index; j < chars.length; j++){
            swap(chars, index, j);
            process(chars, index + 1, ans);
            swap(chars, index, j);
        }
    }

    private static void swap(char[] chars, int i, int j){
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }

    public static List<String> permutationNoRepeat(String str){
        List<String> ans = new ArrayList<>();
        if (str == null || str.length() == 0){
            return ans;
        }
        char[] chars = str.toCharArray();
        process1(chars, 0, ans);
        return ans;
    }

    private static void process1(char[] chars, int i, List<String> ans) {
        if (i == chars.length){
            ans.add(String.valueOf(chars));
            return;
        }
        boolean[] flag = new boolean[26];
        for (int j = i;j < chars.length;j++){
            if (!flag[chars[j] - 'a']){
                flag[chars[j] - 'a'] = true;
                swap(chars, i, j);
                process1(chars, i + 1, ans);
                swap(chars, i, j);
            }
        }
    }

    public static void main(String[] args) {
        String str = "aba";
        List<String> ans = permutation(str);
        for (String s : ans) {
            System.out.println(s);
        }
        System.out.println("==========");
        ans = permutationNoRepeat(str);
        for (String s : ans) {
            System.out.println(s);
        }


    }
}
