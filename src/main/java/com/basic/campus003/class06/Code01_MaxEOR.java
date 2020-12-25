package com.basic.campus003.class06;


public class Code01_MaxEOR {

    public static int maxXorSubArray1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int[] eor = new int[N];
        eor[0] = arr[0];
        for (int i = 1; i < N; i++) {
            eor[i] = eor[i - 1] ^ arr[i];
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                max = Math.max(max, i == 0 ? eor[j] : eor[j] ^ eor[i - 1]);
            }
        }
        return max;
    }

    public static int maxXorSubArray2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int sum = 0;
        NumTrie numTrie = new NumTrie();
        numTrie.add(0);
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            sum ^= arr[i];
            res = Math.max(res, numTrie.maxXor(sum));
            numTrie.add(sum);
        }
        return res;
    }

    public static class Node {
        public Node[] nexts = new Node[2];
    }

    public static class NumTrie {
        public Node head = new Node();

        public void add(int newNum) {
            Node cur = head;
            for (int move = 31; move >= 0; move--) {
                int curNum = ((newNum >> move) & 1);
                cur.nexts[curNum] = cur.nexts[curNum] == null ? new Node() : cur.nexts[curNum];
                cur = cur.nexts[curNum];
            }
        }

        public int maxXor(int eori) {
            Node cur = head;
            int res = 0;
            for (int move = 31; move >= 0; move--) {
                int curNum = ((eori >> move) & 1);
                int best = move == 31 ? curNum : (curNum ^ 1);
                best = cur.nexts[best] == null ? (best ^ 1) : best;

                res |= ((curNum ^ best) << move);

                cur = cur.nexts[best];
            }
            return res;
        }
    }

    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random())
                    - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 30;
        int maxValue = 50;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int comp = maxXorSubArray1(arr);
            int res = maxXorSubArray2(arr);
            if (res != comp) {
                succeed = false;
                printArray(arr);
                System.out.println(res);
                System.out.println(comp);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
}
