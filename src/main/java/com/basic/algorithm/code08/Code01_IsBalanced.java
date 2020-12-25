package com.basic.algorithm.code08;

public class Code01_IsBalanced {

    public static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static boolean isBalanced1(Node head) {
        boolean[] ans = new boolean[1];
        ans[0] = true;
        process1(head, ans);
        return ans[0];
    }

    private static int process1(Node head, boolean[] ans) {
        if (!ans[0] || head == null) {
            return -1;
        }
        int leftHeight = process1(head.left, ans);
        int rightHeight = process1(head.right, ans);
        if (Math.abs(leftHeight - rightHeight) > 1) {
            ans[0] = false;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static class Info {

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

    public static Info process2(Node head) {
        if (head == null) {
            return new Info(0, true);
        }
        Info leftNodeInfo = process2(head.left);
        Info rightNodeInfo = process2(head.right);
        int height = Math.max(leftNodeInfo.height, rightNodeInfo.height) + 1;
        boolean isBalanced = true;
        if (!leftNodeInfo.isBalanced || !rightNodeInfo.isBalanced || Math.abs(leftNodeInfo.height - rightNodeInfo.height) > 1) {
            isBalanced = false;
        }
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
        int testTimes = 10000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (isBalanced1(head) != isBalanced2(head)) {
                System.out.println("Oops!");
            }
//            if (!isBalanced1(head)){
//                System.out.println(isBalanced1(head));
//            }
//            if (!isBalanced2(head)){
//                System.out.println(isBalanced2(head));
//            }
        }
        System.out.println("finish!");
    }
}
