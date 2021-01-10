package com.basic.campus001.class02;

public class Code02_ZeroLeftOneStringNumber {

    public static int getNum1(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return n;
        }
        return getNum1(n - 1) + getNum1(n - 2);
    }

    public static int getNum2(int n) {
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

    public static void main(String[] args) {
        int n = 10;
        System.out.println(getNum1(n));
        System.out.println(getNum2(n));
    }
}
