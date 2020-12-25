package com.basic.algorithm.class04;

import java.util.PriorityQueue;

public class Code03_SortArrayDistanceLessK {

    /**
     * 排完序的数组中每个元素所在的位置移动距离小于K
     */
    public static void sortedArrayDistanceLessK(int[] arr, int k) {

        PriorityQueue<Integer> heap = new PriorityQueue<>();

        int index = 0;

        for (; index <= Math.min(arr.length - 1, k - 1); index++) {
            heap.add(arr[index]);
        }

        int i = 0;
        for (; index < arr.length; index++, i++) {
            heap.add(arr[index]);
            arr[i] = heap.poll();
        }

        while (!heap.isEmpty()) {
            arr[i++] = heap.poll();
        }
    }
}
