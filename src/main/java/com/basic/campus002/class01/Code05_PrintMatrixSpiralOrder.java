package com.basic.campus002.class01;

public class Code05_PrintMatrixSpiralOrder {

    // 旋涡式打印矩阵
    public static void spiralOrderPrint(int[][] matrix){
        int a = 0;
        int b = 0;
        int c = matrix.length - 1;
        int d = matrix[0].length - 1;
        while (a <= c && b <= d){
            printEdge(matrix, a++, b++, c--, d--);
        }
    }

    private static void printEdge(int[][] matrix, int a, int b, int c, int d) {
        if (a == c){
            for (int i = b; i <= d; i++){
                System.out.print(matrix[a][i] + " ");
            }
        }else if(b == d){
            for (int i = a; i<=c;i++){
                System.out.print(matrix[i][b] + " ");
            }
        }else {
            int curRow = a;
            int curCol = b;
            while (curCol < d){
                System.out.print(matrix[a][curCol++] + " ");
            }
            while (curRow < c){
                System.out.print(matrix[curRow++][d] + " ");
            }
            while (curCol > b){
                System.out.print(matrix[c][curCol--] + " ");
            }
            while (curRow > a){
                System.out.print(matrix[curRow--][b] + " ");
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
        spiralOrderPrint(matrix);
    }
}
