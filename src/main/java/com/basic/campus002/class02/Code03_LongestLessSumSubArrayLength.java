package com.basic.campus002.class02;

public class Code03_LongestLessSumSubArrayLength {

    public static int maxLengthLessSum(int[] arr, int K) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[] minSums = new int[arr.length];
        int[] minSumEnds = new int[arr.length];
        minSums[arr.length - 1] = arr[arr.length - 1];
        minSumEnds[arr.length - 1] = arr.length - 1;
        for (int i = arr.length - 2; i >= 0; i--) {
            if (minSums[i + 1] <= 0) {
                minSums[i] = arr[i] + minSums[i + 1];
                minSumEnds[i] = minSumEnds[i+1];
            }else {
                minSums[i] = arr[i];
                minSumEnds[i] = i;
            }
        }

        int sum = 0;
        int len = 0;
        int end = 0;
        for (int i = 0; i < arr.length; i++){
            while (end < arr.length && sum + minSums[end] <= K){
                sum += minSums[end];
                end = minSumEnds[end] + 1;
            }
            len = Math.max(end-i, len);
            if (end > i){
                sum -= arr[i];
            }else {
                end = i + 1;
            }
        }
        return len;
    }
}
