package com.basic.algorithm.class12;

public class Code05_LongestCommonSubsequence {

    public static int lcs(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
            return 0;
        }
        int N = s1.length();
        int M = s2.length();
        int[][] dp = new int[N][M];
        dp[0][0] = s1.charAt(0) == s2.charAt(0) ? 1 : 0;
        for (int i = 1; i < N; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], s1.charAt(i) == s2.charAt(0) ? 1 : 0);
        }
        for (int j = 1; j < M; j++) {
            dp[0][j] = Math.max(dp[0][j - 1], s1.charAt(0) == s2.charAt(j) ? 1 : 0);
        }
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                if (s1.charAt(i) == s2.charAt(j)) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                }
            }
        }
        return dp[N - 1][M - 1];
    }

    public static void main(String[] args) {
        String s1 = "ab123cde4fgh56ij7";
        String s2 = "k1l2mn3op45qr6stuvw7";
        System.out.println(lcs(s1, s2));
    }
}
