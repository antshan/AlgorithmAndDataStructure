package com.basic.algorithm.class03;

public class Code03_ReversePair {

    // 求数组中的逆序对
    public static int reversePairNumber(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    private static int process(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        int mid = l + ((r - l) >> 1);
        return process(arr, l, mid) +
                process(arr, mid + 1, r) +
                merge(arr, l, mid, r);

    }

    private static int merge(int[] arr, int l, int mid, int r) {
        int p1 = mid;
        int p2 = r;
        int res = 0;
        int[] helper = new int[r - l + 1];
        int i = helper.length - 1;
        while (p1 >= l && p2 > mid) {
            res += arr[p1] > arr[p2] ? (p2 - mid) : 0;
            helper[i--] = arr[p1] > arr[p2] ? arr[p1--] : arr[p2--];
        }
        while (p1 >= l) {
            helper[i--] = arr[p1--];
        }
        while (p2 > mid) {
            helper[i--] = arr[p2--];
        }
        for (i = 0; i < helper.length; i++) {
            arr[i + l] = helper[i];
        }
        return res;
    }
}
