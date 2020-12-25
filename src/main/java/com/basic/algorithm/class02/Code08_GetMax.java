package com.basic.algorithm.class02;

public class Code08_GetMax {

    //递归求数组的最大值
    public int getMax(int[] arr) {
        return process(arr, 0, arr.length - 1);
    }

    private int process(int[] arr, int L, int R) {
        if (L == R) {
            return arr[L];
        }
        int mid = L + ((R - L) >> 1);
        int leftMax = process(arr, L, mid - 1);
        int rightMax = process(arr, mid, R);
        return Math.max(leftMax,rightMax);
    }
}
