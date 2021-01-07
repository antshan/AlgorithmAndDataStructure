package com.basic.algorithm.class12;

public class Code07_HorseJump {

    public static int ways(int a, int b, int step) {
        if (a < 0 || a > 9 || b < 0 || b > 8) {
            return 0;
        }
        return f(0, 0, a, b, step);
    }

    private static int f(int i, int j, int a, int b, int step) {
        if (i < 0 || i > 9 || j < 0 || j > 8) {
            return 0;
        }
        if (step == 0) {
            return i == a && j == b ? 1 : 0;
        }
        return f(i - 2, j - 1, a, b, step - 1)
                + f(i - 2, j + 1, a, b, step - 1)
                + f(i - 1, j + 2, a, b, step - 1)
                + f(i + 1, j + 2, a, b, step - 1)
                + f(i + 2, j + 1, a, b, step - 1)
                + f(i + 2, j - 1, a, b, step - 1)
                + f(i + 1, j - 2, a, b, step - 1)
                + f(i - 1, j - 2, a, b, step - 1);
    }

    public static int dpways(int a, int b, int step){
        if (a < 0 || a > 9 || b < 0 || b > 8) {
            return 0;
        }
        int[][][] dp = new int[10][9][step + 1];
        dp[a][b][0] = 1;
        for (int k = 1; k <= step; k++){
            for (int i = 0; i < 10; i++){
                for (int j = 0; j < 9; j++){
                    dp[i][j][k] = f(dp,i - 2, j - 1, k - 1)
                            + f(dp, i - 2, j + 1, k - 1)
                            + f(dp, i - 1, j + 2, k - 1)
                            + f(dp, i + 1, j + 2, k - 1)
                            + f(dp, i + 2, j + 1, k - 1)
                            + f(dp, i + 2, j - 1, k - 1)
                            + f(dp, i + 1, j - 2, k - 1)
                            + f(dp, i - 1, j - 2, k - 1);
                }
            }
        }
        return dp[0][0][step];
    }

    private static int f(int[][][] dp,int i, int j, int k){
        if (i < 0 || i > 9 || j < 0 || j > 8){
            return 0;
        }
        return dp[i][j][k];
    }

    public static void main(String[] args) {
        int x = 7;
        int y = 7;
        int step = 10;
        System.out.println(ways(x, y, step));
        System.out.println(dpways(x, y, step));
    }
}
