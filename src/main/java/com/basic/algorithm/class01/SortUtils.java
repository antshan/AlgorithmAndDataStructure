package com.basic.algorithm.class01;

public class SortUtils {

    public static void swap(int[] arr, int j, int i) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
