package com.basic.algorithm.code08;

import com.basic.algorithm.class07.Node;

import java.util.ArrayList;

public class Code04_MaxSubBSTSize {

    public static int maxSubBSTSize(Node head) {
        if (head == null) {
            return 0;
        }
        return process(head).maxSubBSTSize;
    }

    private static Info process(Node head) {
        if (head == null) {
            return null;
        }
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);
        int min = head.value;
        int max = head.value;
        if (leftInfo != null) {
            min = Math.min(min, leftInfo.min);
            max = Math.max(max, leftInfo.max);
        }
        if (rightInfo != null) {
            min = Math.min(min, rightInfo.min);
            max = Math.max(max, rightInfo.max);
        }
        boolean isAllBST = false;
        int maxSubBSTSize = 0;
        if (leftInfo != null){
            maxSubBSTSize = Math.max(maxSubBSTSize, leftInfo.maxSubBSTSize);
        }
        if (rightInfo != null){
            maxSubBSTSize = Math.max(maxSubBSTSize, rightInfo.maxSubBSTSize);
        }
        if (
                (leftInfo == null || (leftInfo.isAllBST && head.value > leftInfo.max))
            &&  (rightInfo == null || (rightInfo.isAllBST && head.value < rightInfo.min))
        ){
            isAllBST = true;
            maxSubBSTSize = (leftInfo == null ? 0 : leftInfo.maxSubBSTSize)
                        + (rightInfo == null ? 0 : rightInfo.maxSubBSTSize)
                        + 1;

        }
        return new Info(maxSubBSTSize, isAllBST, min, max);
    }

    public static class Info {

        private int maxSubBSTSize;
        private boolean isAllBST;
        private int min;
        private int max;

        public Info(int maxSubBSTSize, boolean isAllBST, int min, int max) {
            this.maxSubBSTSize = maxSubBSTSize;
            this.isAllBST = isAllBST;
            this.min = min;
            this.max = max;
        }
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.left.left = new Node(3);

        head.right = new Node(4);
        head.right.left = new Node(2);
        head.right.right = new Node(6);
        head.right.left.left = new Node(1);
        head.right.left.right = new Node(3);
//        head.right.right.left = new Node(5);
//        head.right.right.right = new Node(10);

        System.out.println(maxSubBSTSize(head));
    }
}
