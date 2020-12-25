package com.basic.campus003.class07;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Code02_SmallestUnFormedSum {

    public static int unformedSum1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 1;
        }
        Set<Integer> set = new HashSet<>();
        process(arr, 0, 0, set);
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            min = Math.min(min, arr[i]);
        }
        for (int j = min + 1; j < Integer.MAX_VALUE; j++) {
            if (!set.contains(j)) {
                return j;
            }
        }
        return 0;
    }

    private static void process(int[] arr, int index, int sum, Set<Integer> set) {
        if (index == arr.length) {
            set.add(sum);
            return;
        }
        process(arr, index + 1, sum, set);
        process(arr, index + 1, sum + arr[index], set);
    }

    public static int unformedSum3(int[] arr) {
        // 默认数组中有1的做法
        if (arr == null || arr.length == 0) {
            return 1;
        }
        Arrays.sort(arr);
        int range = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > range + 1) {
                return range + 1;
            } else {
                range += arr[i];
            }
        }
        return range + 1;
    }

    public static int[] generateArray(int len, int maxValue) {
        int[] res = new int[len];
        for (int i = 0; i != res.length; i++) {
            res[i] = (int) (Math.random() * maxValue) + 1;
        }
        return res;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int len = 27;
        int max = 30;
        int[] arr = generateArray(len, max);

//        long start = System.currentTimeMillis();
//        System.out.println(unformedSum1(arr));
//        long end = System.currentTimeMillis();
//        System.out.println("cost time: " + (end - start) + " ms");
//        System.out.println("======================================");
//
//        start = System.currentTimeMillis();
//        System.out.println(unformedSum2(arr));
//        end = System.currentTimeMillis();
//        System.out.println("cost time: " + (end - start) + " ms");
//        System.out.println("======================================");

        System.out.println("set arr[0] to 1");
        arr[0] = 1;
        printArray(arr);
        long start = System.currentTimeMillis();
        System.out.println(unformedSum3(arr));
        long end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + " ms");

    }
}
