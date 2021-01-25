package com.basic.campus002.class01;

public class Code04_RotateMatrix {

    // 正方形顺时针90度旋转
    public static void rotate(int[][] matrix) {
        int a = 0;
        int b = 0;
        int c = matrix.length - 1;
        int d = matrix[0].length - 1;
        while (a < c) {
            rotateEdge(matrix, a++, b++, c--, d--);
        }
    }

    private static void rotateEdge(int[][] matrix, int a, int b, int c, int d) {
        int temp = 0;
        for (int i = 0; i < d - b; i++){
            temp = matrix[a][b+i];
            matrix[a][b+i] = matrix[c-i][b];
            matrix[c-i][b] = matrix[c][d-i];
            matrix[c][d-i] = matrix[a+i][d];
            matrix[a+i][d] = temp;
        }
    }
}
