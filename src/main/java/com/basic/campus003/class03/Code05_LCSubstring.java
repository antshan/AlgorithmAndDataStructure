package com.basic.campus003.class03;

public class Code05_LCSubstring {

    public static String lcst1(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
            return "";
        }
        char[] ch1 = s1.toCharArray();
        char[] ch2 = s2.toCharArray();
        int N = ch1.length;
        int M = ch2.length;
        int[][] dp = getdp(ch1, ch2);
        int end = 0;
        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (dp[i][j] > max) {
                    end = i;
                    max = dp[i][j];
                }
            }
        }
        return s1.substring(end - max + 1, end + 1);
    }

    private static int[][] getdp(char[] ch1, char[] ch2) {
        int N = ch1.length;
        int M = ch2.length;
        int[][] dp = new int[N][M];
        for (int i = 0; i < M; i++) {
            dp[0][i] = ch1[0] == ch2[i] ? 1 : 0;
        }
        for (int j = 0; j < N; j++) {
            dp[j][0] = ch1[j] == ch2[0] ? 1 : 0;
        }
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                if (ch1[i] == ch2[j]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
            }
        }
        return dp;
    }

    public static String lcst2(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
            return "";
        }
        char[] ch1 = s1.toCharArray();
        char[] ch2 = s2.toCharArray();
        int row = 0;
        int col = s2.length() - 1;
        int end = 0;
        int max = 0;
        while (row < s1.length()) {
            int i = row;
            int j = col;
            int len = 0;
            while (i < s1.length() && j < s2.length()) {
                if (ch1[i] != ch2[j]) {
                    len = 0;
                } else {
                    len++;
                }
                if (len > max) {
                    max = len;
                    end = i;
                }
                i++;
                j++;
            }
            if (col > 0) {
                col--;
            } else {
                row++;
            }
        }
        return s1.substring(end-max+1,end+1);
    }

    public static void main(String[] args) {
        String str1 = "ABC1234567DEFG";
        String str2 = "HIJKL1234567MNOP";
        System.out.println(lcst1(str1, str2));
        System.out.println(lcst2(str1, str2));

    }

}
