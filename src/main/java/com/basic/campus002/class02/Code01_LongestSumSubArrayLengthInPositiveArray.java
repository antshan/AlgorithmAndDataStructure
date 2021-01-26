package com.basic.campus002.class02;

public class Code01_LongestSumSubArrayLengthInPositiveArray {

    // 子数组和为K的最长子数组，正整数数组arr
    public static int getMaxLength(int[] arr, int K) {
        if (arr == null || arr.length == 0 || K < 0) {
            return 0;
        }
        int left = 0;
        int right = 0;
        int sum = arr[0];
        int len = 0;
        while (right < arr.length) {
            if (sum == K) {
                len = Math.max(len, right - left + 1);
                sum -= arr[left++];
            } else if (sum < K) {
                right++;
                if (right == arr.length) {
                    break;
                }
                sum += arr[right];
            }else {
                sum -= arr[left++];
            }
        }
        return len;
    }
}
