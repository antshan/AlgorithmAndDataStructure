package com.basic.campus001.class01;

import java.util.LinkedList;

public class Code02_AllLessNumSubArray {

    public static int getNum(int[] arr, int num){
        if (arr == null || arr.length == 0){
            return 0;
        }
        int res = 0;
        LinkedList<Integer> max = new LinkedList<>();
        LinkedList<Integer> min = new LinkedList<>();
        int L = 0;
        int R = 0;
        while (L < arr.length){

            while (R < arr.length){
                while (!max.isEmpty() && arr[max.peekLast()] < arr[R]){
                    max.pollLast();
                }
                max.addLast(R);

                while (!min.isEmpty() && arr[min.peekLast()] > arr[R]){
                    min.pollLast();
                }
                min.addLast(R);
                if (max.getFirst() - min.getFirst() > num){
                    break;
                }

                R++;
            }

            res += R - L;
            if (max.peekFirst() == L){
                max.pollFirst();
            }
            if (min.peekFirst() == L){
                min.pollFirst();
            }
            L++;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {5, 3, 2, 6, 7, 4, 1, 9, 8};
        int num = 5;
        System.out.println(getNum(arr, num));

    }
}
