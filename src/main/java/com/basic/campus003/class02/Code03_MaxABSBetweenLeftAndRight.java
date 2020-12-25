package com.basic.campus003.class02;

public class Code03_MaxABSBetweenLeftAndRight {

    public static int maxAbs(int[] arr) {
        long start = System.currentTimeMillis();
        int max = Integer.MIN_VALUE;
        int N = arr.length;
        for (int i = 0; i < N; i++) {
            max = Math.max(max, arr[i]);
        }
        System.out.println(System.currentTimeMillis()-start);
        return max - Math.min(arr[0],arr[N-1]);
    }

    public static int[] generateRandomArray(int length) {
        int[] arr = new int[length];
        for (int i = 0; i != arr.length; i++) {
            arr[i] = (int) (Math.random() * 1000) - 499;
        }
        return arr;
    }

    public static int maxABS1(int[] arr) {
        long start = System.currentTimeMillis();
        int res = Integer.MIN_VALUE;
        int maxLeft = 0;
        int maxRight = 0;
        for (int i = 0; i != arr.length - 1; i++) {
            maxLeft = Integer.MIN_VALUE;
            for (int j = 0; j != i + 1; j++) {
                maxLeft = Math.max(arr[j], maxLeft);
            }
            maxRight = Integer.MIN_VALUE;
            for (int j = i + 1; j != arr.length; j++) {
                maxRight = Math.max(arr[j], maxRight);
            }
            res = Math.max(Math.abs(maxLeft - maxRight), res);
        }
        System.out.println(System.currentTimeMillis()-start);
        return res;
    }

    public static void main(String[] args) {
        int[] arr = generateRandomArray(2000000);
        System.out.println(maxAbs(arr));
        System.out.println(maxABS1(arr));
//        System.out.println(maxABS3(arr));
    }
}
