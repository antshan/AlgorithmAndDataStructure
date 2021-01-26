package com.basic.campus002.class02;

import java.util.HashMap;
import java.util.Map;

public class Code02_LongestSumSubArrayLength {

    // 子数组和为K的最长子数组，整数数组arr(元素可能正数，负数，0)
    public static int maxLength(int[] arr, int K) {
        if (arr == null || arr.length == 0 || K < 0) {
            return 0;
        }
        int len = 0;
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (!map.containsKey(sum - K)) {
                len = Math.max(i - map.get(sum - K), len);
            }
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }
        return len;
    }
}
