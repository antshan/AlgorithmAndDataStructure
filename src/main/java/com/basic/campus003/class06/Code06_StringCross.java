package com.basic.campus003.class06;

public class Code06_StringCross {

    public static boolean isCross(String s1, String s2, String aim) {
        if (s1 == null || s2 == null || aim == null) {
            return false;
        }
        if (aim.length() != s1.length() + s2.length()) {
            return false;
        }
        char[] ch1 = s1.toCharArray();
        char[] ch2 = s2.toCharArray();
        char[] ch3 = aim.toCharArray();
        boolean[][] dp = new boolean[ch1.length + 1][ch2.length + 1];
        dp[0][0] = true;
        for (int i = 1; i <= ch1.length; i++) {
            if (ch1[i - 1] != ch3[i - 1]) {
                break;
            }
            dp[i][0] = true;
        }

        for (int j = 1; j <= ch2.length; j++) {
            if (ch2[j - 1] != ch3[j - 1]) {
                break;
            }
            dp[0][j] = true;
        }

        for (int i = 1;i <= ch1.length; i++){
            for (int j = 1;j <= ch2.length;j++){
                if (ch1[i-1] == ch3[i+j-1] && dp[i-1][j] ||
                    ch2[j-1] == ch3[i+j-1] && dp[i][j-1]){
                    dp[i][j] = true;
                }
            }
        }

        return dp[ch1.length][ch2.length];
    }

    public static void main(String[] args) {
        String str1 = "1234";
        String str2 = "abcd";
        String aim = "1a23bcd4";
        System.out.println(isCross(str1, str2, aim));
    }
}
