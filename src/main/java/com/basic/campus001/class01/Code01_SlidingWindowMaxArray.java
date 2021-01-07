package com.basic.campus001.class01;

import java.util.LinkedList;

public class Code01_SlidingWindowMaxArray {

    public static int[] getMaxWindow(int[] arr, int w) {
        if (arr == null || arr.length < w || w < 1) {
            return null;
        }
        int[] res = new int[arr.length - w + 1];
        int index = 0;
        LinkedList<Integer> max = new LinkedList<>();
        for (int i = 0; i < arr.length; i++) {
            while (!max.isEmpty() && arr[max.peekLast()] < arr[i]) {
                max.pollLast();
            }
            max.addLast(i);
            if (max.peekFirst() == i - w) {
                max.pollFirst();
            }
            if (i >= w - 1) {
                res[index++] = arr[max.peekFirst()];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {3, 4, 2, 6, 7, 1, 8, 5};
        int w = 3;
        int[] res = getMaxWindow(arr, w);
        for (int re : res) {
            System.out.print(re + " ");
        }

    }
}
