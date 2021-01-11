package com.basic.campus001.class04;

import java.util.Comparator;
import java.util.PriorityQueue;

import static com.basic.algorithm.class01.SortUtils.swap;
import static com.basic.algorithm.class03.Code04_PartitionAndQuickSort.copyArray;

public class Code01_FindMinKth {

    public static class MaxHeapComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    }

    public static int minKth1(int[] arr, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>(new MaxHeapComparator());
        for (int i = 0; i < k; i++) {
            heap.add(arr[i]);
        }
        for (int i = k; i < arr.length; i++) {
            if (arr[i] < heap.peek()) {
                heap.poll();
                heap.add(arr[i]);
            }
        }
        return heap.peek();
    }

    public static int minKth2(int[] arr, int k) {
        int[] array = copyArray(arr);
        return process2(array, 0, arr.length - 1, k - 1);
    }

    private static int process2(int[] arr, int L, int R, int index) {
        if (L == R) {
            return arr[L];
        }
        int pivot = arr[L + (int) Math.random() * (R - L + 1)];
        int[] range = partition(arr, L, R, pivot);
        if (index >= range[0] && index <= range[1]) {
            return arr[index];
        } else if (index < range[0]) {
            return process2(arr, L, range[0] - 1, index);
        } else {
            return process2(arr, range[1] + 1, R, index);
        }
    }

    private static int[] partition(int[] arr, int L, int R, int pivot) {
        int less = L - 1;
        int more = R + 1;
        int cur = L;
        while (cur < more) {
            if (arr[cur] < pivot) {
                swap(arr, ++less, cur++);
            } else if (arr[cur] > pivot) {
                swap(arr, cur, --more);
            } else {
                cur++;
            }
        }
        return new int[]{less + 1, more - 1};
    }

    public static int minKth3(int[] arr, int k) {
        int[] array = copyArray(arr);
        return bfprt(array, 0, arr.length - 1, k - 1);
    }

    private static int bfprt(int[] arr, int L, int R, int index) {
        if (L == R) {
            return arr[L];
        }
        int pivot = getMedianOfMedians(arr, L, R);
        int[] range = partition(arr, L, R, pivot);
        if (index >= range[0] && index <= range[1]) {
            return arr[index];
        } else if (index < range[0]) {
            return bfprt(arr, L, range[0] - 1, index);
        } else {
            return bfprt(arr, range[1] + 1, R, index);
        }
    }

    private static int getMedianOfMedians(int[] arr, int L, int R) {
        int size = R - L + 1;
        int offset = size % 5 == 0 ? 0 : 1;
        int medianArrLen = size / 5 + offset;
        int[] res = new int[medianArrLen];
        for (int i = 0; i < res.length; i++) {
            int start = L + i * 5;
            res[i] = getMedian(arr, start, Math.min(R, start + 5));
        }
        return bfprt(res, 0, res.length - 1, res.length / 2);
    }

    private static int getMedian(int[] arr, int start, int end) {
        insertSort(arr, start, end);
        return arr[(start + end) / 2];
    }

    private static void insertSort(int[] arr, int start, int end) {
        for (int i = start + 1; i < arr.length; i++) {
            for (int j = i - 1; j >= start && arr[j + 1] < arr[j]; j--) {
                swap(arr, j, j + 1);
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {3, 2, 5, 6, 7, 4, 1, 9, 10, 18, 17, 21, 15, 22, 4, 7, 8};
        int k = 13;
        System.out.println(minKth1(arr, k));
        System.out.println(minKth2(arr, k));
        System.out.println(minKth3(arr, k));
    }
}
