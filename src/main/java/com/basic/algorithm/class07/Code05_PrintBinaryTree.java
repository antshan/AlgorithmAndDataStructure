package com.basic.algorithm.class07;

public class Code05_PrintBinaryTree {

    public static class Node{
        private int data;
        private Node left;
        private Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    public static void printTree(Node head){
        System.out.println("Binary Tree:");
        printInOrder(head,0,"H",17);
        System.out.println();
    }

    private static void printInOrder(Node head, int height, String to, int len) {
        if (head == null){
            return;
        }
        printInOrder(head.right,height+1,"V",len);
        String val = to+head.data+to;
        int lenM = val.length();
        int lenL = (len - lenM)/2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL)+val+getSpace(lenR);
        System.out.println(getSpace(height*len)+val);
        printInOrder(head.left,height+1,"^",len);
    }

    private static String getSpace(int num) {
        String space = " ";
        StringBuilder stringBuilder = new StringBuilder("");
        for (int i =0;i<num;i++){
            stringBuilder.append(space);
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(-222222222);
        head.right = new Node(3);
        head.left.left = new Node(Integer.MIN_VALUE);
        head.right.left = new Node(55555555);
        head.right.right = new Node(66);
        head.left.left.right = new Node(777);
        printTree(head);

        head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.right.left = new Node(5);
        head.right.right = new Node(6);
        head.left.left.right = new Node(7);
        printTree(head);

        head = new Node(1);
        head.left = new Node(1);
        head.right = new Node(1);
        head.left.left = new Node(1);
        head.right.left = new Node(1);
        head.right.right = new Node(1);
        head.left.left.right = new Node(1);
        printTree(head);

    }
}
