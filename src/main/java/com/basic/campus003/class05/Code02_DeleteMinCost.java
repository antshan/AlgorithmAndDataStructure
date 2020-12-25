package com.basic.campus003.class05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Code02_DeleteMinCost {

    public static int minCost1(String s1, String s2) {
        if (s1 == null || s2 == null) {
            return 0;
        }
        List<String> s2sub = new ArrayList<>();
        process(s2.toCharArray(), 0, "", s2sub);
        s2sub.sort(new LenComparator());
        for (String sub : s2sub) {
            if (s1.indexOf(sub) != -1) {
                return s2.length() - sub.length();
            }
        }
        return s2.length();
    }

    private static void process(char[] toCharArray, int index, String path, List<String> s2sub) {
        if (index == toCharArray.length) {
            s2sub.add(path);
            return;
        }
        process(toCharArray, index + 1, path, s2sub);
        process(toCharArray, index + 1, path + toCharArray[index], s2sub);
    }

    public static class LenComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o2.length() - o1.length();
        }
    }

    public static int minCost2(String s1, String s2) {
        if (s1 == null || s2 == null) {
            return 0;
        }
        if (s1.length() == 0 || s2.length() == 0) {
            return s2.length();
        }
        int ans = Integer.MAX_VALUE;
        for (int start = 0; start < s1.length(); start++) {
            for (int end = start + 1; end <= s1.length(); end++) {
                ans = Math.min(ans, distance(s2.toCharArray(), s1.substring(start, end).toCharArray()));
            }
        }
        return Math.min(ans, s2.length());
    }

    private static int distance(char[] ch2, char[] ch1sub) {
        int row = ch2.length;
        int col = ch1sub.length;
        int[][] dp = new int[row][col];
        dp[0][0] = ch2[0] == ch1sub[0] ? 0 : Integer.MAX_VALUE;
        for (int i = 1; i < row; i++) {
            dp[i][0] = (dp[i - 1][0] != Integer.MAX_VALUE || ch1sub[0] == ch2[i]) ? i : Integer.MAX_VALUE;
        }
        for (int j = 1; j < col; j++) {
            dp[0][j] = Integer.MAX_VALUE;
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                if (dp[i - 1][j] != Integer.MAX_VALUE) {
                    dp[i][j] = dp[i - 1][j] + 1;
                }
                if (ch2[i] == ch1sub[j] && dp[i - 1][j - 1] != Integer.MAX_VALUE) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }
        return dp[row - 1][col - 1];
    }

    public static String generateRandomString(int l, int v) {
        int len = (int) (Math.random() * l);
        char[] str = new char[len];
        for (int i = 0; i < len; i++) {
            str[i] = (char) ('a' + (int) (Math.random() * v));
        }
        return String.valueOf(str);
    }

    public static void main(String[] args) {
        int str1Len = 20;
        int str2Len = 10;
        int v = 5;
        int testTime = 10000;
        boolean pass = true;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            String str1 = generateRandomString(str1Len, v);
            String str2 = generateRandomString(str2Len, v);
            int ans1 = minCost1(str1, str2);
            int ans2 = minCost2(str1, str2);
//            int ans3 = minCost3(str1, str2);
//            int ans4 = minCost4(str1, str2);
            if (ans1 != ans2) {
                pass = false;
                System.out.println(str1);
                System.out.println(str2);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("test pass : " + pass);
    }
}
