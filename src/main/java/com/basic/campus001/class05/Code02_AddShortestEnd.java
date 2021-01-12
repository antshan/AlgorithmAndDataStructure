package com.basic.campus001.class05;

public class Code02_AddShortestEnd {

    public static String shortestEnd(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char[] str = manacherString(s);
        int[] pArr = new int[str.length];
        int C = -1;
        int R = -1;
        int maxContainsEnd = -1;
        for (int i = 0; i < str.length; i++) {
            pArr[i] = i < R ? Math.min(2 * C - i, R - i) : 1;
            while (i + pArr[i] < str.length && i - pArr[i] > -1) {
                if (str[i + pArr[i]] == str[i - pArr[i]]) {
                    pArr[i]++;
                } else {
                    break;
                }
            }
            if (i + pArr[i] > R) {
                R = i + pArr[i];
                C = i;
            }
            if (R == str.length) {
                maxContainsEnd = pArr[i];
                break;
            }
        }
        char[] res = new char[s.length() - maxContainsEnd + 1];
        for (int j = 0; j < res.length; j++) {
            res[res.length - 1 - j] = str[2 * j + 1];
        }
        return String.valueOf(res);
    }

    private static char[] manacherString(String s) {
        char[] chars = s.toCharArray();
        char[] res = new char[2 * s.length() + 1];
        int index = 0;
        for (int i = 0; i < res.length; i++) {
            res[i] = ((i & 1) == 0) ? '#' : chars[index++];
        }
        return res;
    }

    public static void main(String[] args) {
        String s = "ab123321";
        System.out.println(shortestEnd(s));
    }
}
