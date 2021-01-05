package com.basic.algorithm.class11;

public class Code09_NQueens {

    public static int num1(int n) {
        if (n < 1) {
            return 0;
        }
        int[] record = new int[n];
        return process1(n, 0, record);
    }

    private static int process1(int n, int i, int[] record) {
        if (i == n) {
            return 1;
        }
        int res = 0;
        for (int j = 0; j < n; j++) {
            if (isValid(record, i, j)) {
                record[i] = j;
                res += process1(n, i + 1, record);
            }
        }
        return res;
    }

    private static boolean isValid(int[] record, int i, int j) {
        for (int k = 0; k < i; k++) {
            if (record[k] == j || Math.abs(i - k) == Math.abs(record[k] - j)) {
                return false;
            }
        }
        return true;
    }

    public static int num2(int n) {
        if (n < 1 || n > 32) {
            return 0;
        }
        int limit = n == 32 ? -1 : ((1 << n) - 1);
        return process2(limit, 0, 0, 0);
    }

    private static int process2(int limit, int leftDiaLimit, int rightDiaLimit, int colLimit) {
        if (limit == colLimit) {
            return 1;
        }
        int pos = limit & (~(leftDiaLimit | rightDiaLimit | colLimit));
        int mostRightOne = 0;
        int res = 0;
        while (pos != 0) {
            mostRightOne = pos & (~pos + 1);
            pos = pos - mostRightOne;
            res += process2(limit,
                    (leftDiaLimit | mostRightOne) << 1,
                    (rightDiaLimit | mostRightOne) >>> 1,
                    colLimit | mostRightOne);
        }
        return res;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println(num1(14));
        long mid = System.currentTimeMillis();
        System.out.println((mid - start)+"ms");
        System.out.println(num2(14));
        long end = System.currentTimeMillis();
        System.out.println((end - mid)+"ms");
    }
}
