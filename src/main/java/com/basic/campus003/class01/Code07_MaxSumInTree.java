package com.basic.campus003.class01;

public class Code07_MaxSumInTree {

    public static class Node {
        private Node left;
        private Node right;
        private int value;

        public Node(int value) {
            this.value = value;
        }
    }

    public static int maxDis(Node head) {
        if (head == null) {
            return 0;
        }
        return process(head);
    }

    private static int process(Node head) {
        if (head.left == null && head.right == null) {
            return head.value;
        }
        int max = Integer.MIN_VALUE;
        if (head.left != null) {
            max = process(head.left);
        }
        if (head.right != null) {
            max = Math.max(max, process(head.right));
        }
        return head.value + max;
    }

    public static class Info {
        private int allTreeMaxSum;
        private int fromHeadMaxSum;

        public Info(int allTreeMaxSum, int fromHeadMaxSum) {
            this.allTreeMaxSum = allTreeMaxSum;
            this.fromHeadMaxSum = fromHeadMaxSum;
        }
    }

    public static int maxSum(Node head) {
        if (head == null) {
            return 0;
        }
        return f(head).allTreeMaxSum;
    }

    private static Info f(Node head) {
        if (head == null) {
            return null;
        }
        Info leftInfo = f(head.left);
        Info rightInfo = f(head.right);
        int p1 = Integer.MIN_VALUE;
        if (leftInfo != null) {
            p1 = leftInfo.allTreeMaxSum;
        }

        int p2 = Integer.MIN_VALUE;
        if (rightInfo != null) {
            p2 = rightInfo.allTreeMaxSum;
        }

        int p3 = head.value;
        int p4 = Integer.MIN_VALUE;
        if (leftInfo != null) {
            p4 = head.value + leftInfo.fromHeadMaxSum;
        }

        int p5 = Integer.MIN_VALUE;
        if (rightInfo != null) {
            p5 = head.value + rightInfo.fromHeadMaxSum;
        }

        int allTreeMaxSum = Math.max(Math.max(Math.max(p1, p2), p3), Math.max(p4, p5));
        int fromHeadMaxSum = Math.max(Math.max(p3, p4), p5);
        return new Info(allTreeMaxSum, fromHeadMaxSum);
    }

    public static int maxSum2(Node head) {
        if (head == null) {
            return 0;
        }
        return f2(head).allTreeMaxSum;
    }

    private static Info f2(Node head) {
        if (head == null) {
            return null;
        }
        Info lInfo = f2(head.left);
        Info rInfo = f2(head.right);
        int p1 = Integer.MIN_VALUE;
        if (lInfo != null) {
            p1 = lInfo.allTreeMaxSum;
        }
        int p2 = Integer.MIN_VALUE;
        if (rInfo != null) {
            p2 = rInfo.allTreeMaxSum;
        }
        int p3 = head.value;
        int p4 = Integer.MIN_VALUE;
        if (lInfo != null) {
            p4 = head.value + lInfo.fromHeadMaxSum;
        }

        int p5 = Integer.MIN_VALUE;
        if (rInfo != null) {
            p5 = head.value + rInfo.fromHeadMaxSum;
        }
        int p6 = Integer.MIN_VALUE;
        if (lInfo != null && rInfo != null) {
            p6 = head.value + lInfo.fromHeadMaxSum + rInfo.fromHeadMaxSum;
        }

        int allTreeMaxSum = Math.max(Math.max(Math.max(Math.max(Math.max(p1, p2), p3), p4), p5), p6);
        int fromHeadMaxSum = Math.max(Math.max(p3, p4), p5);
        return new Info(allTreeMaxSum, fromHeadMaxSum);
    }
}
