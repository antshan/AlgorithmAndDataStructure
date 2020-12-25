package com.basic.campus003.class02;

public class Code06_PrintUniquePairAndTriad {

    public static void printUniquePair(int[] arr, int K) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int l = 0;
        int r = arr.length - 1;
        while (l < r) {
            if (arr[l] + arr[r] < K) {
                l++;
            } else if (arr[l] + arr[r] > K) {
                r--;
            } else {
                if (l == 0 || arr[l - 1] != arr[l]) {
                    System.out.println(arr[l] + "," + arr[r]);
                }
                l++;
                r--;
            }
        }
    }

    public static void printUniqueTriad(int[] arr, int K) {
        if (arr == null || arr.length < 3) {
            return;
        }

        for (int i = 0; i < arr.length - 2; i++) {
            if (i == 0 || arr[i-1] != arr[i]){
                printRest(arr,i,i+1,arr.length-1,K);
            }
        }
    }

    private static void printRest(int[] arr, int f, int start, int end, int k) {
        while (start < end){
            if (arr[start] + arr[end] < k-arr[f]){
                start++;
            }else if (arr[start] + arr[end] > k-arr[f]){
                end--;
            }else {
                if (start == f+1 || arr[start-1] != arr[start]){
                    System.out.println(arr[f]+","+arr[start]+","+arr[end]);
                }
                start++;
                end--;
            }
        }
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int sum = 10;
        int[] arr1 = { 1, 1, 2, 2, 3, 3, 4, 5, 6, 7, 8, 8, 9 };
        printArray(arr1);
        System.out.println("====");
        printUniquePair(arr1, sum);
        System.out.println("====");
        printUniqueTriad(arr1, sum);

    }
}
