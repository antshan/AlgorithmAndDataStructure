package com.basic.campus003.class06;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Code04_TopKSumCrossTwoArrays {

    public static class Node {
        private int index1;
        private int index2;

        private int sum;

        public Node(int index1, int index2, int sum) {
            this.index1 = index1;
            this.index2 = index2;
            this.sum = sum;
        }
    }

    public static class MaxHeapComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o2.sum - o1.sum;
        }
    }

    public static int[] topKSum(int[] arr1, int[] arr2, int topK) {
        if (arr1 == null || arr2 == null || topK < 1) {
            return null;
        }
        int M = arr1.length;
        int N = arr2.length;
        topK = Math.min(topK, M + N);

        int[] res = new int[topK];
        int resIndex = 0;

        PriorityQueue<Node> heap = new PriorityQueue<>(new MaxHeapComparator());
        Node node = new Node(M - 1, N - 1, arr1[M - 1] + arr2[N - 1]);
        heap.add(node);

        boolean[][] set = new boolean[M][N];
        set[M - 1][N - 1] = true;
        while (resIndex != topK) {
            Node cur = heap.poll();
            res[resIndex++] = cur.sum;
            int i1 = cur.index1;
            int i2 = cur.index2;
            if (i1 - 1 >= 0 && !set[i1 - 1][i2]) {
                heap.add(new Node(i1 - 1, i2, arr1[i1 - 1] + arr2[i2]));
                set[i1 - 1][i2] = true;
            }
            if (i2 - 1 >= 0 && !set[i1][i2 - 1]) {
                heap.add(new Node(i1, i2 - 1, arr1[i1] + arr2[i2 - 1]));
                set[i1][i2 - 1] = true;
            }
        }
        return res;
    }

    // For test, this method is inefficient but absolutely right
    public static int[] topKSumTest(int[] arr1, int[] arr2, int topK) {
        int[] all = new int[arr1.length * arr2.length];
        int index = 0;
        for (int i = 0; i != arr1.length; i++) {
            for (int j = 0; j != arr2.length; j++) {
                all[index++] = arr1[i] + arr2[j];
            }
        }
        Arrays.sort(all);
        int[] res = new int[Math.min(topK, all.length)];
        index = all.length - 1;
        for (int i = 0; i != res.length; i++) {
            res[i] = all[index--];
        }
        return res;
    }

    public static int[] generateRandomSortArray(int len) {
        int[] res = new int[len];
        for (int i = 0; i != res.length; i++) {
            res[i] = (int) (Math.random() * 50000) + 1;
        }
        Arrays.sort(res);
        return res;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static boolean isEqual(int[] arr1, int[] arr2) {
        if (arr1 == null || arr2 == null || arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i != arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int a1Len = 5000;
        int a2Len = 4000;
        int k = 20000000;
        int[] arr1 = generateRandomSortArray(a1Len);
        int[] arr2 = generateRandomSortArray(a2Len);
        long start = System.currentTimeMillis();
        int[] res = topKSum(arr1, arr2, k);
        long end = System.currentTimeMillis();
        System.out.println(end - start + " ms");

        start = System.currentTimeMillis();
        int[] absolutelyRight = topKSumTest(arr1, arr2, k);
        end = System.currentTimeMillis();
        System.out.println(end - start + " ms");

        System.out.println(isEqual(res, absolutelyRight));

    }
}
