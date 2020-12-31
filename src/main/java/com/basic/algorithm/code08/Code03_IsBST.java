package com.basic.algorithm.code08;

import com.basic.algorithm.class07.Node;

import java.util.ArrayList;

public class Code03_IsBST {

    public static boolean isBST(Node head){
        if (head == null){
            return true;
        }
        return process(head).isBST;
    }

    private static Info process(Node head) {
        if (head == null){
            return null;
        }
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);
        int min = head.value;
        int max = head.value;
        if (leftInfo != null){
            min = Math.min(min, leftInfo.min);
            max = Math.max(max, leftInfo.max);
        }
        if (rightInfo != null){
            min = Math.min(min, rightInfo.min);
            max = Math.max(max, rightInfo.max);
        }
        boolean isBST = false;
        if ((leftInfo == null ? true : (leftInfo.isBST && head.value > leftInfo.max))
           && (rightInfo == null ? true : (rightInfo.isBST && head.value < rightInfo.min))
        ){
            isBST = true;
        }
        return new Info(isBST, min, max);
    }

    public static class Info{
        private boolean isBST;
        private int min;
        private int max;

        public Info(boolean isBST, int min, int max) {
            this.isBST = isBST;
            this.min = min;
            this.max = max;
        }
    }

    public static void main(String[] args) {
        Node head = new Node(2);
        head.left = new Node(1);
        head.right = new Node(3);
        System.out.println(isBST(head));
    }
}
