package com.basic.algorithm.class12;

public class Code09_CoinsWay {

    public static int ways(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        return process(arr, 0, aim);
    }

    private static int process(int[] arr, int index, int rest) {
        if (index == arr.length) {
            return rest == 0 ? 1 : 0;
        }
        int res = 0;
        for (int i = 0; i * arr[index] <= rest; i++) {
            res += process(arr, index + 1, rest - i * arr[index]);
        }
        return res;
    }

    public static int dpways(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int[][] dp = new int[arr.length + 1][aim + 1];
        dp[arr.length][0] = 1;
        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = 0; j <= aim; j++) {
                // 方法一：减少很多重复计算
//                dp[i][j] = dp[i+1][j];
//                if (j - arr[i]>= 0){
//                    dp[i][j] += dp[i][j - arr[i]];
//                }

                // 方法二：直接从暴力递归转化而来
                for (int k = 0; k * arr[i] <= j; k++) {
                    dp[i][j] += dp[i + 1][j - k * arr[i]];
                }
            }
        }
        return dp[0][aim];
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 5, 9};
        int aim = 27;
        System.out.println(ways(arr, aim));
        System.out.println(dpways(arr, aim));
    }
}
