package com.basic.campus003.class01;

public class Code03_ParenthesesDeep {

    public static boolean isValid(String s) {
        if (s == null || s.length() < 2) {
            return false;
        }
        int count = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != '(' && chars[i] != ')') {
                return false;
            }
            if (chars[i] == ')' && --count < 0) {
                return false;
            }
            if (chars[i] == '(') {
                count++;
            }
        }
        return count == 0;
    }

    public static int deep(String s) {
        if (!isValid(s)) {
            return 0;
        }
        int max = 0;
        int count = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(') {
                max = Math.max(max, ++count);
            } else {
                count--;
            }
        }
        return max;
    }

    public static int maxLength(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int[] dp = new int[chars.length];
        int max = 0;
        int pre = 0;
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == ')') {
                pre = i - dp[i - 1] - 1;
                if (pre >= 0 && chars[pre] == '(') {
                    dp[i] = dp[i - 1] + 2 + (pre > 0 ? dp[pre - 1] : 0);
                    max = Math.max(max, dp[i]);
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        String test = "((()))";
        System.out.println(deep(test));

    }
}
