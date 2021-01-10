package com.basic.campus001.class02;

public class Code01_FibonacciProblem {

    // 斐波那契数列递推
    public static int f1(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        return f1(n - 1) + f1(n - 2);
    }

    public static int f2(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int[][] base = {{1, 1}, {1, 0}};
        int[][] res = matrixPower(base, n - 2);
        return res[0][0] + res[1][0];
    }

    private static int[][] matrixPower(int[][] base, int n) {
        int[][] res = new int[base.length][base[0].length];
        for (int i = 0; i < base.length; i++) {
            res[i][i] = 1;
        }
        int[][] tmp = base;
        for (; n != 0; n >>= 1) {
            if ((n & 1) != 0) {
                res = multiplyMatrix(res, tmp);
            }
            tmp = multiplyMatrix(tmp, tmp);
        }
        return res;
    }

    private static int[][] multiplyMatrix(int[][] m1, int[][] m2) {
        int[][] res = new int[m1.length][m2[0].length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m2[0].length; j++) {
                for (int k = 0; k < m1[0].length; k++) {
                    res[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return res;
    }


    // 爬楼梯的方法数 每次爬一步或两步  n阶楼梯几种方式
    public static int s1(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return n;
        }
        return s1(n - 1) + s1(n - 2);
    }

    public static int s2(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return n;
        }
        int[][] base = {{1, 1}, {1, 0}};
        int[][] res = matrixPower(base, n - 2);
        return 2 * res[0][0] + res[1][0];
    }

    // 牧场产牛的问题
    public static int c1(int n){
        if (n < 1){
            return 0;
        }
        if (n == 1 || n ==2 || n == 3){
            return n;
        }
        return c1(n - 1) + c1(n - 3);
    }

    public static int c2(int n){
        if (n < 1){
            return 0;
        }
        if (n == 1 || n == 2 || n == 3){
            return n;
        }
        int[][] base = {{1, 1, 0},{0, 0, 1},{1, 0, 0}};
        int[][] res = matrixPower(base, n - 3);
        return 3 * res[0][0] + 2 * res[1][0] + res[2][0];
    }

    public static void main(String[] args) {

        int n = 17;
        System.out.println(f1(n));
        System.out.println(f2(n));

        System.out.println("=========");
        System.out.println(s1(n));
        System.out.println(s2(n));

        System.out.println("=========");
        System.out.println(c1(n));
        System.out.println(c2(n));
    }
}
