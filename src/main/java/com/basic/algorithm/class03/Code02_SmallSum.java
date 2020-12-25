package com.basic.algorithm.class03;

public class Code02_SmallSum {

    public static int smallSum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    private static int process(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        int mid = L + ((R - L) >> 1);
        return process(arr, L, mid)+process(arr, mid + 1, R)+merge(arr,L,mid,R);
    }

    private static int merge(int[] arr, int l, int mid, int r) {
        int p1 = l;
        int p2 = mid +1;
        int[] helper = new int[r-l+1];
        int i =0;
        int res =0;
        while (p1<=mid && p2<=r){
            res += arr[p1] < arr[p2] ? (r-p2+1)*arr[p1] : 0;
            helper[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1<=mid) {
            helper[i++] = arr[p1++];
        }
        while (p2<=r){
            helper[i++] = arr[p2++];
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {1,1,3,4,2,5};
        System.out.println(smallSum(arr));
    }
}
