package com.basic.algorithm.class03;

public class Code04_PartitionAndQuickSort {

    public static void quickSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process1(arr, 0, arr.length - 1);
    }

    private static void process1(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        int m = partition(arr, L, R);
        process1(arr, L, m - 1);
        process1(arr, m + 1, R);
    }

    private static int partition(int[] arr, int L, int R) {
        int lessEquals = L - 1;
        int index = L;
        while (index < R) {
            if (arr[index] <= arr[R]) {
                swap2(arr, index, ++lessEquals);
            }
            index++;
        }
        swap2(arr, R, ++lessEquals);
        return lessEquals;
    }

    public static void quickSort2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        process2(arr, 0, arr.length - 1);
    }

    private static void process2(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        int[] equals = netherlandFlag(arr, L, R);
        process2(arr, L, equals[0] - 1);
        process2(arr, equals[1] + 1, R);
    }

    private static int[] netherlandFlag(int[] arr, int L, int R) {
        int less = L - 1;
        int more = R;
        int index = L;
        while (index < more) {
            if (arr[index] == arr[R]) {
                index++;
            } else if (arr[index] < arr[R]) {
                swap2(arr, index++, ++less);
            } else {
                swap2(arr, index, --more);
            }
        }
        swap2(arr, R, more);
        return new int[]{less + 1, more};
    }

    public static void quickSort3(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process3(arr, 0, arr.length - 1);
    }

    private static void process3(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        swap2(arr, L + (int) (Math.random() * (R - L + 1)), R);
        int[] equals = netherlandFlag(arr, L, R);
        process3(arr, L, equals[0] - 1);
        process3(arr, equals[1] + 1, R);
    }

    private static void swap1(int[] arr, int index, int i) {
        arr[index] = arr[index] ^ arr[i];
        arr[i] = arr[index] ^ arr[i];
        arr[index] = arr[index] ^ arr[i];
    }

    private static void swap2(int[] arr, int index, int i) {
        int temp = arr[index];
        arr[index] = arr[i];
        arr[i] = temp;
    }

    private static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            int[] arr3 = copyArray(arr1);
            quickSort1(arr1);
            quickSort2(arr2);
            quickSort3(arr3);
            if (!isEqual(arr1, arr2) || !isEqual(arr2, arr3)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Oops!");

    }
}
