package com.basic.algorithm.class05;

import java.util.Arrays;

/**
 * 基数排序，依次按照每个位置上的数进行排序，例如按照个位 十位 百位 知道最高位实现排序
 * 只能对非负数进行排序
 */
public class Code03_RadixSort {

    public static void radixSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        radixSort(arr, 0, arr.length - 1, maxbits(arr));
    }

    public static void radixSort(int[] arr, int L, int R, int maxbits) {
        //获取每一位上的数字，1代表个位数，往上以此类推
        for (int d = 1; d <= maxbits; d++) {

            int[] bucket = new int[10];
            int[] helper = new int[R - L + 1];

            for (int i = L; i <= R; i++) {
                int num = getDigitFromBit(arr[i], d);
                bucket[num]++;
            }

            for (int i = 1; i < bucket.length; i++) {
                bucket[i] = bucket[i] + bucket[i - 1];
            }

            for (int i = R; i >= L; i--) {
                int num = getDigitFromBit(arr[i], d);
                helper[bucket[num] - 1] = arr[i];
                bucket[num]--;
            }

            for (int i = 0;i<helper.length;i++){
                arr[i+L] = helper[i];
            }
        }
    }

    public static int getDigitFromBit(int value, int d) {
        return (int) (value / Math.pow(10, (d - 1))) % 10;
    }

    private static int maxbits(int[] arr) {
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }

        int res = 0;
        while (max != 0) {
            res++;
            max /= 10;
        }
        return res;
    }


    public static void main(String[] args) {
        int[] arr = {1, 5, 9, 3, 2, 4, 96, 8, 7};
        radixSort(arr);
        Arrays.stream(arr).forEach(System.out::println);
    }
}
