package com.basic.campus002.class01;

public class Code06_ZigZagPrintMatrix {

    public static void printMatrixZigZag(int[][] matrix){
        int aR = 0;
        int aC = 0;
        int bR = 0;
        int bC = 0;
        int endR = matrix.length - 1;
        int endC = matrix[0].length - 1;
        boolean fromUp = false;
        while (aR <= endR){
            printLevel(matrix, aR, aC, bR, bC, fromUp);
            aR = aC != endC ? aR : aR + 1;
            aC = aC != endC ? aC + 1 : aC;
            bC = bR != endR ? bC  : bC + 1;
            bR = bR != endR ? bR + 1 : bR;
            fromUp = !fromUp;
        }
    }

    private static void printLevel(int[][] matrix, int aR, int aC, int bR, int bC, boolean fromUp) {
        if(fromUp){
            while (aR <= bR){
                System.out.print(matrix[aR++][aC--] + " ");
            }
        }else {
            while (aR <= bR){
                System.out.print(matrix[bR--][bC++] + " ");
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1,2,3,4},{5,6,7,8}};
        printMatrixZigZag(matrix);
    }
}
