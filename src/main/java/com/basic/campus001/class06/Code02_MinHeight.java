package com.basic.campus001.class06;

import com.basic.campus001.Node;

public class Code02_MinHeight {

    public static int minHeight1(Node head) {
        if (head == null) {
            return 0;
        }
        return process(head);
    }

    private static int process(Node head) {
        if (head.left == null && head.right == null) {
            return 1;
        }
        int leftHeight = Integer.MAX_VALUE;
        if (head.left != null) {
            leftHeight = process(head.left);
        }

        int rightHeight = Integer.MAX_VALUE;
        if (head.right != null) {
            rightHeight = process(head.right);
        }
        return 1 + Math.min(leftHeight, rightHeight);
    }

    // 使用morris遍历求树的最小高度
    public static int minHeight2(Node head){
        if (head == null){
            return 0;
        }
        Node cur = head;
        Node mostRight = null;
        int minHeight = Integer.MAX_VALUE;
        int curLevel = 0;
        while (cur != null){
            mostRight = cur.left;
            int rightBoardSize = 1;
            if (mostRight != null){
                while (mostRight.right != null && mostRight.right != cur){
                    rightBoardSize++;
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null){
                    curLevel++;
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }else {
                    if (mostRight.left == null){
                        minHeight = Math.min(minHeight, curLevel);
                    }
                    curLevel -= rightBoardSize;
                    mostRight.right = null;
                }
            }else {
                curLevel++;
            }
            cur = cur.right;
        }
        int rightHeight = 1;
        cur = head;
        while (cur.right != null){
            rightHeight++;
            cur = cur.right;
        }
        if (cur.left == null){
            minHeight = Math.min(minHeight, rightHeight);
        }
        return minHeight;
    }

    public static void main(String[] args) {
        Node head = new Node(4);
        head.left = new Node(2);
        head.right = new Node(6);
        head.left.left = new Node(1);
        head.left.right = new Node(3);
        head.right.left = new Node(5);
        head.right.right = new Node(7);
        System.out.println(minHeight1(head));
        System.out.println(minHeight2(head));
    }
}
