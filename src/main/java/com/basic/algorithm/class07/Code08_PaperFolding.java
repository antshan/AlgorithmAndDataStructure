package com.basic.algorithm.class07;

public class Code08_PaperFolding {

    public static void printAllFolds(int N) {
        printProcess(1, N, "down");
    }

    private static void printProcess(int i, int N, String status) {
        if (i > N) {
            return;
        }
        printProcess(i + 1, N, "down");
        System.out.print(status + " ");
        printProcess(i + 1, N, "up");
    }

    public static void main(String[] args) {
        printAllFolds(4);
    }
}
