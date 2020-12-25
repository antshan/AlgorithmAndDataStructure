package com.basic.campus003.class07;

public class Code03_MinPatches {

    public static int minPatches(int[] arr, int aim) {
        int patches = 0;
        long range = 0;
        for (int i = 0; i < arr.length; i++) {
            while (arr[i] > range + 1) {
                range += range + 1;
                patches++;
                if (range >= aim) {
                    return patches;
                }
            }
            range += arr[i];
            if (range >= aim) {
                return patches;
            }
        }
        while (range < aim){
            range += range+1;
            patches++;
        }
        return patches;
    }

    public static void main(String[] args) {
        int[] test = { 1, 2, 31, 33 };
        int n = 2147483647;
        System.out.println(minPatches(test, n));

    }
}
