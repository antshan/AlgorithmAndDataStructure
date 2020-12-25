package com.basic.campus003.class02;

public class Code02_PackingMachine {

    public static int minOps(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int sum = 0;
        int size = arr.length;
        for (int i = 0; i < size; i++) {
            sum += arr[i];
        }
        if (sum % size != 0) {
            return -1;
        }
        int avg = sum / size;
        int leftSum = 0;
        int ans = 0;
        for (int j = 0; j < size; j++) {
            int leftRest = leftSum - j * avg;
            int rightRest = sum - leftSum - arr[j] - (size - j - 1) * avg;
            if (leftRest < 0 && rightRest < 0) {
                ans = Math.max(ans, Math.abs(leftRest) + Math.abs(rightRest));
            }else {
                ans = Math.max(ans, Math.max(Math.abs(leftRest),Math.abs(rightRest)));
            }
            leftSum += arr[j];
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {0,0,0,10,5};
        System.out.println(minOps(arr));
    }
}
