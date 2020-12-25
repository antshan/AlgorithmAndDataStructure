package com.basic.algorithm.class01;

public class Code02_BinarySearch {

    // 查找数组中是否存在某个数
    public static boolean exist(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return false;
        }
        int L = 0;
        int R = arr.length - 1;
        int mid = 0;
        while (L < R) {
            mid = L + ((R - L) >> 1);
            if (arr[mid] > target) {
                R = mid - 1;
            } else if (arr[mid] < target) {
                L = mid + 1;
            } else {
                return true;
            }
        }
        return arr[L] == target;
    }

    // 大于value的最左边的位置
    public static int moreValueNearLeft(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int L = 0;
        int R = arr.length - 1;
        int mid = 0;
        int index = -1;
        while (L <= R) {
            mid = L + ((R - L) >> 1);
            if (arr[mid] >= num) {
                R = mid - 1;
                index = mid;
            } else {
                L = mid + 1;
            }
        }
        return index;
    }

    // 小于value的最右边的位置
    public static int lessValueNearRight(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int L = 0;
        int R = arr.length - 1;
        int index = -1;
        int mid = 0;
        while (L <= R) {
            mid = L + ((R - L) >> 1);
            if (arr[mid] <= num) {
                L = mid + 1;
                index = mid;
            } else {
                R = mid - 1;
            }
        }
        return index;
    }

    //求一个数组中的局部最小位置 即该位置的元素小于左边和右边的元素
    public static int BSAwesome(int[] arr) {
        if (arr == null || arr.length < 2) {
            return -1;
        }
        if (arr.length == 0 || arr[0] < arr[1]) {
            return 0;
        }
        if (arr[arr.length - 1] < arr[arr.length - 2]) {
            return arr.length - 1;
        }

        int L = 1;
        int R = arr.length - 2;
        int mid =0;
        while (L < R){
            mid = L + ((R-L)>>1);
            if (arr[mid] > arr[mid-1]){
                R = mid-1;
            }else if (arr[mid]>arr[mid+1]){
                L = mid+1;
            }else {
                return mid;
            }
        }
        return mid;
    }

    public static void main(String[] args) {
        int[] arr = {0, 1, 3, 5, 6, 9, 10, 13, 18};
        int target = 2;
        System.out.println(exist(arr, 14));
        System.out.println(moreValueNearLeft(arr, 4));
        System.out.println(lessValueNearRight(arr, 12));
    }
}
