package com.basic.algorithm.class01;

public class Code01_Sort {

    public static void selectionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            SortUtils.swap(arr, i, minIndex);
        }
    }

    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int end = arr.length - 1; end >= 0; end--) {
            for (int start = 0; start < end; start++) {
                if (arr[start] > arr[start + 1]) {
                    SortUtils.swap(arr, start, start + 1);
                }
            }
        }
    }

    public static void insertSort(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }
        for (int i =1;i<arr.length;i++){
            for (int j=i-1;j>=0 && arr[j] > arr[j+1];j--){
                swap(arr,j+1,j);
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {2, 1, 4, 5, 7, 3, 9, 0, 8, 6};
//        selectionSort(arr);
//        bubbleSort(arr);
        insertSort(arr);
        printArray(arr);

    }

    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // 不使用中间变量交换两个变量的值
    public static void swap(int[] arr,int i,int j){
        arr[i] = arr[i]^arr[j];
        arr[j] = arr[i]^arr[j];
        arr[i] = arr[i]^arr[j];
    }
}
