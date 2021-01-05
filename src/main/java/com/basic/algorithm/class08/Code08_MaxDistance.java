package com.basic.algorithm.class08;

import com.basic.algorithm.class07.Node;

public class Code08_MaxDistance {

    public static int maxDistance(Node head) {
        if (head == null) {
            return 0;
        }
        return process(head).maxDistance;
    }

    private static Info process(Node head) {
        if (head == null) {
            return new Info(0, 0);
        }
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        int maxDistance = Math.max(Math.max(leftInfo.maxDistance, rightInfo.maxDistance),
                leftInfo.height + rightInfo.height + 1);
        return new Info(maxDistance, height);
    }

    public static class Info {
        private int maxDistance;
        private int height;

        public Info(int maxDistance, int height) {
            this.maxDistance = maxDistance;
            this.height = height;
        }
    }

    public static void main(String[] args) {

        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(4);
        head.right.left = new Node(5);
        head.right.right = new Node(6);
        head.right.left.left = new Node(7);
        head.right.left.right = new Node(8);
        head.right.right.left = new Node(9);
        head.right.right.right = new Node(10);
        head.left.left = new Node(3);
        System.out.println(maxDistance(head));
    }
}
