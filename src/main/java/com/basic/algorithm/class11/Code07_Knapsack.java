package com.basic.algorithm.class11;

public class Code07_Knapsack {

    public static int getMaxValue(int[] w, int[] v, int bag) {
        return process(w, v, 0, 0, bag);
    }

    private static int process(int[] w, int[] v, int index, int alreadyW, int bag) {
        if (alreadyW > bag) {
            return -1;
        }
        if (index == w.length) {
            return 0;
        }
        int p1 = process(w, v, index + 1, alreadyW, bag);
        int p2 = process(w, v, index + 1, alreadyW + w[index], bag);
        if (p2 != -1) {
            p2 = p2 + v[index];
        }
        return Math.max(p1, p2);
    }

    public static int maxValue(int[] w, int[] v, int bag) {
        return process1(w, v, 0, bag);
    }

    private static int process1(int[] w, int[] v, int index, int rest) {
        if (index == w.length) {
            return 0;
        }
        int p1 = process1(w, v, index + 1, rest);
        int p2 = Integer.MIN_VALUE;
        if (rest - w[index] >= 0) {
            p2 = v[index] + process1(w, v, index + 1, rest - w[index]);
        }
        return Math.max(p1, p2);
    }

    public static int dpway(int[] w, int[] v, int bag) {
        int N = w.length;
        int[][] dp = new int[N + 1][bag + 1];
        for (int i = 0;i <= bag; i++){
            dp[N][i] = 0;
        }
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j <= bag; j++){
                int p1 = dp[i+1][j];
                int p2 = Integer.MIN_VALUE;
                if (j >= w[i]){
                    p2 = v[i] + dp[i+1][j-w[i]];
                }
                dp[i][j] = Math.max(p1, p2);
            }
        }
        return dp[0][bag];
    }

    private static int process2(int[] w, int[] v, int index, int rest) {
        int N = w.length;

        return 0;
    }

    public static void main(String[] args) {
        int[] weights = {3, 2, 4, 7};
        int[] values = {5, 6, 3, 19};
        int bag = 11;
        System.out.println(maxValue(weights, values, bag));
        System.out.println(getMaxValue(weights, values, bag));
        System.out.println(dpway(weights, values, bag));
    }
}
