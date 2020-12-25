package com.basic.campus003.class03;

public class Code04_LCSubsequence {

    public static int lcs1(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
            return 0;
        }
        char[] ch1 = s1.toCharArray();
        char[] ch2 = s2.toCharArray();
        int N = ch1.length;
        int M = ch2.length;
        return process(ch1, ch2, N - 1, M - 1);
    }

    private static int process(char[] ch1, char[] ch2, int N, int M) {
        if (N == 0 && M == 0) {
            return ch1[0] == ch2[0] ? 1 : 0;
        }
        if (N == 0) {
            return (ch1[N] == ch2[M] || process(ch1, ch2, N, M - 1) == 1) ? 1 : 0;
        }
        if (M == 0) {
            return (ch1[N] == ch2[M] || process(ch1, ch2, N - 1, M) == 1) ? 1 : 0;
        }
        int p1 = process(ch1, ch2, N - 1, M);
        int p2 = process(ch1, ch2, N, M - 1);
        int p3 = process(ch1, ch2, N - 1, M - 1);
        int p4 = 0;
        if (ch1[N] == ch2[M]) {
            p4 = p3 + 1;
        }

        return Math.max(Math.max(p1, p2), Math.max(p3, p4));
    }

    public static int lcs2(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
            return 0;
        }
        char[] ch1 = s1.toCharArray();
        char[] ch2 = s2.toCharArray();
        int N = ch1.length;
        int M = ch2.length;

        int[][] dp = new int[N][M];
        dp[0][0] = ch1[0] == ch2[0] ? 1 : 0;
        for (int i = 1; i < M; i++) {
            dp[0][i] = ch1[0] == ch2[i] ? 1 : dp[0][i - 1];
        }
        for (int j = 1; j < N; j++) {
            dp[j][0] = ch1[j] == ch2[0] ? 1 : dp[j - 1][0];
        }
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                if (ch1[i] == ch2[j]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                }
            }
        }
        return dp[N-1][M-1];
    }

    public static void main(String[] args) {
        System.out.println(lcs1("ab12cd345e6f7", "gh1i2j3k4l5m67"));
        System.out.println(lcs2("ab12cd345e6f7", "gh1i2j3k4l5m67"));
    }
}
