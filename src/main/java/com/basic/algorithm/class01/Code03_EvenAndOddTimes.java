package com.basic.algorithm.class01;

public class Code03_EvenAndOddTimes {

    // 只有一个数出现奇数次
    public static void printOddTimesNum1(int[] arr) {
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        System.out.println(eor);
    }

    // 有两个数出现奇数次
    public static void printOddTimesNum2(int[] arr) {
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }

        int mostRightOne = eor & ((~eor + 1));

        int includeOne = 0;
        for (int i=0;i<arr.length;i++){
            if ((arr[i] & mostRightOne) != 0){
                includeOne ^= arr[i];
            }
        }

        System.out.println(includeOne + " " + (includeOne ^ eor));
    }

    public static void main(String[] args) {
        int[] arr1 = {1,1,2,2,3,4,5,5,3,6};
//        printOddTimesNum1(arr1);
        printOddTimesNum2(arr1);
    }
}
