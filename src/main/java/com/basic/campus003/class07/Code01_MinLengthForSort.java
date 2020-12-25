package com.basic.campus003.class07;

public class Code01_MinLengthForSort {

    public static int getMinLength(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int noMinIndex = arr.length - 1;
        int noMaxIndex = 0;
        int min = arr[arr.length-1];
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < max) {
                noMaxIndex = i;
            } else {
                max = Math.max(max, arr[i]);
            }
        }
        for (int j = arr.length - 2; j >= 0; j--) {
            if (arr[j] > min) {
                noMinIndex = j;
            } else {
                min = Math.min(min, arr[j]);
            }
        }
        if (noMinIndex == -1) {
            noMinIndex = 0;
        }
        return noMaxIndex - noMinIndex + 1;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19 };
        System.out.println(getMinLength(arr));

    }
}
