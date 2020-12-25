package com.basic.campus003.class01;

import java.util.Arrays;

public class Code01_CordCoverMaxPoint {

    public static int maxPoint1(int[] arr, int L) {
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            int nearest = nearestRightIndex(arr, i, arr[i] - L);
            max = Math.max(max, i - nearest + 1);
        }
        return max;
    }

    private static int nearestRightIndex(int[] arr, int R, int value) {
        int L = 0;
        int index = R;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (arr[mid] >= value) {
                index = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return index;
    }

    public static int maxPoint3(int[] arr, int len) {
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            int nearest = nearestLeftIndex(arr, i, arr[i] + len);
            max = Math.max(max, nearest - i + 1);
        }
        return max;
    }

    private static int nearestLeftIndex(int[] arr, int L, int value) {
        int index = L;
        int R = arr.length - 1;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (arr[mid] > value) {
                R = mid - 1;
            } else {
                index = mid;
                L = mid + 1;
            }
        }
        return index;
    }

    public static int maxPoint2(int[] arr, int len) {
        int L = 0;
        int R = 0;
        int N = arr.length;
        int max = 0;
        while (L < N) {
            while (R < N && arr[R] - arr[L] <= len) {
                R++;
            }
            max = Math.max(max, (R - L++));
        }
        return max;
    }

    // for test
    public static int test(int[] arr, int L) {
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            int pre = i - 1;
            while (pre >= 0 && arr[i] - arr[pre] <= L) {
                pre--;
            }
            max = Math.max(max, i - pre);
        }
        return max;
    }

    // for test
    public static int[] generateArray(int len, int max) {
        int[] ans = new int[(int) (Math.random() * len) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (int) (Math.random() * max);
        }
        Arrays.sort(ans);
        return ans;
    }

    public static void main(String[] args) {
        int len = 100;
        int max = 1000;
        int testTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int L = (int) (Math.random() * max);
            int[] arr = generateArray(len, max);
            int ans1 = maxPoint1(arr, L);
            int ans2 = maxPoint2(arr, L);
            int ans3 = maxPoint3(arr, L);
            int ans4 = test(arr, L);
            if (ans1 != ans2 || ans2 != ans3 || ans3 != ans4) {
                System.out.println("oops!");
                break;
            }
        }

    }
}
