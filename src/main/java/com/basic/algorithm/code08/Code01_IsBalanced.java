package com.basic.algorithm.code08;

import com.basic.algorithm.class07.Node;

public class Code01_IsBalanced {

    public static boolean isBalanced1(Node head) {
        boolean[] res = {true};
        process1(head, res);
        return res[0];
    }

    private static int process1(Node node, boolean[] res) {
        if (!res[0] || node == null){
            return 0;
        }
        int leftHeight = process1(node.left, res);
        int rightHeight = process1(node.right, res);
        if (Math.abs(leftHeight - rightHeight) > 1){
            res[0] = false;
        }
        return Math.max(leftHeight,rightHeight) + 1;

    }

    public static class Info{
        private int height;
        private boolean isBalanced;

        public Info(int height, boolean isBalanced) {
            this.height = height;
            this.isBalanced = isBalanced;
        }
    }

    public static boolean isBalanced2(Node head) {
        return process2(head).isBalanced;
    }

    public static Info process2(Node node){
        if (node == null){
            return new Info(0, true);
        }
        Info leftInfo = process2(node.left);
        Info rightInfo = process2(node.right);
        boolean isBalanced = true;
        if (!leftInfo.isBalanced || !rightInfo.isBalanced || Math.abs(leftInfo.height- rightInfo.height)>1){
            isBalanced = false;
        }
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        return new Info(height, isBalanced);
    }


    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 100000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            boolean res1;boolean res2;
            if ((res1 = isBalanced1(head)) != (res2 = isBalanced2(head))) {
                System.out.println("Oops!");
            }
//            System.out.print(res1+" ");
//            System.out.print(res2+" ");
//            System.out.print("|| ");
        }
        System.out.println("finish!");
    }
}
