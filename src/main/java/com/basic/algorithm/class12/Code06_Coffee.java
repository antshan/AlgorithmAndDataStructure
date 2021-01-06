package com.basic.algorithm.class12;

public class Code06_Coffee {

    public static int process(int[] drinks, int a, int b, int index, int washLine){
        if (index == drinks.length - 1){
            return Math.min(drinks[index] + b,
                    Math.max(drinks[index], washLine) + a);
        }
        int wash = Math.max(washLine, drinks[index]) + a;
        int next1 = process(drinks, a, b, index + 1, wash);
        int p1 = Math.max(wash, next1);

        int dry = drinks[index] + b;
        int next2 = process(drinks, a, b, index + 1, washLine);
        int p2 = Math.max(dry, next2);

        return Math.min(p1, p2);
    }

    public static int dp(int[] drinks,int a,int b){
        if (a >= b){
            return drinks[drinks.length - 1] + b;
        }
        int N = drinks.length;
        int limit = 0;
        for (int i = 0; i < N; i++){
            limit = Math.max(limit, drinks[i]) + a;
        }
        int[][] dp = new int[N][limit + 1];
        for (int washLine = 0; washLine <= limit; washLine++){
            dp[N - 1][washLine] = Math.min(Math.max(drinks[N - 1], washLine) + a, drinks[N - 1] + b);
        }

        for (int index = N -2 ;index >= 0; index--){
            for (int washLine = 0;washLine <= limit; washLine++){
                int p1 = Integer.MAX_VALUE;
                int wash = Math.max(washLine, drinks[index]) +a;
                if (wash <= limit){
                    p1 = Math.max(wash, dp[index + 1][wash]);
                }

                int dry = drinks[index] + b;
                int p2 = Math.max(dry, dp[index + 1][washLine]);
                dp[index][washLine] = Math.min(p1, p2);
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        int[] drinks = {1,1,3,5,5,5,5,7,8,10,10,10,11,11,12,12};
        int a = 3;
        int b = 10;
        System.out.println(process(drinks, a, b, 0, 0));
        System.out.println(dp(drinks, a, b));
    }
}
