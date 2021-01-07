package com.basic.campus001.class01;

import java.util.Stack;

public class Code04_AllTimesMinToMax {

    public static int max(int[] arr){
        int max = 0;
        int[] sum = new int[arr.length];
        sum[0] = arr[0];
        for (int i = 1; i < arr.length; i++){
            sum[i] = sum[i - 1] + arr[i];
        }
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++){
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]){
                int j = stack.pop();
                max = Math.max(max, (stack.isEmpty() ? sum[i - 1] : (sum[i - 1] - sum[stack.peek()])) * arr[j]);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()){
            int j = stack.pop();
            max = Math.max(max, (stack.isEmpty() ? sum[arr.length - 1] : (sum[arr.length - 1] - sum[stack.peek()])) * arr[j]);
        }
        return max;

    }
}
