package com.basic.algorithm.class11;

public class Code06_ConvertToLetterString {

    public static int number(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        return process(str.toCharArray(), 0);
    }

    private static int process(char[] chars, int index) {
        if (index == chars.length) {
            return 1;
        }
        if (chars[index] == '0') {
            return 0;
        }
        if (chars[index] == '1') {
            int res = process(chars, index + 1);
            if (index + 1 < chars.length) {
                res += process(chars, index + 2);
            }
            return res;
        }
        if (chars[index] == '2') {
            int res = process(chars, index + 1);
            if (index + 1 < chars.length && chars[index + 1] >= '0' && chars[index + 1] <= '6') {
                res += process(chars, index + 2);
            }
            return res;
        }
        return process(chars, index + 1);
    }

    public static int dpway(String str){
        if (str == null || str.length() == 0){
            return 0;
        }
        char[] chars = str.toCharArray();
        int[] dp = new int[chars.length + 1];
        dp[chars.length] = 1;
        for (int i = dp.length - 2;i >= 0;i--){
            if (chars[i] == '0'){
                dp[i] = 0;
            }else if (chars[i] == '1'){
                dp[i] = dp[i+1];
                if (i + 1 < chars.length){
                    dp[i] += dp[i+2];
                }
            }else if (chars[i] == '2'){
                dp[i] = dp[i+1];
                if (i + 1 < chars.length && chars[i + 1] >= '0' && chars[i + 1] <= '6') {
                    dp[i] += dp[i+2];
                }
            }else {
                dp[i] = dp[i+1];
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {
        String str = "111211121";
        System.out.println(number(str));
        System.out.println(dpway(str));
    }
}
