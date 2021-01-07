package com.basic.campus001.class01;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Code03_MonotonousStack {

    public static int[][] getNearLessNoRepeat(int[] arr) {
        int[][] res = new int[arr.length][2];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                Integer popIndex = stack.pop();
                int leftLessIndex = stack.isEmpty() ? -1 : stack.peek();
                res[popIndex][0] = leftLessIndex;
                res[popIndex][1] = i;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()){
            Integer popIndex = stack.pop();
            int leftIndex = stack.isEmpty() ? -1 : stack.peek();
            res[popIndex][0] = leftIndex;
            res[popIndex][1] = -1;
        }
        return res;
    }

    public static int[][] getNearLessWithRepeat(int[] arr){
        int[][] res = new int[arr.length][2];
        Stack<List<Integer>> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++){
            while (!stack.isEmpty() && arr[stack.peek().get(stack.peek().size() - 1)] > arr[i]){
                List<Integer> popList = stack.pop();
                int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                for (Integer popIndex : popList) {
                    res[popIndex][0] = leftLessIndex;
                    res[popIndex][1] = i;
                }
            }
            if (!stack.isEmpty() && arr[stack.peek().get(stack.peek().size() - 1)] == arr[i]){
                stack.peek().add(i);
            }else {
                List<Integer> newList = new ArrayList<>();
                newList.add(i);
                stack.push(newList);
            }
        }
        while (!stack.isEmpty()){
            List<Integer> popList = stack.pop();
            int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
            for (Integer popIndex : popList) {
                res[popIndex][0] = leftLessIndex;
                res[popIndex][1] = -1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {3,4,2,1,5,9,7,8};
        int[][] res = getNearLessNoRepeat(arr);
        System.out.println(res);
    }
}
