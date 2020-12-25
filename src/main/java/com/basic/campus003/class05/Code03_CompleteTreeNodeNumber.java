package com.basic.campus003.class05;

public class Code03_CompleteTreeNodeNumber {

    public static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static int nodeNum(Node head) {
        if (head == null) {
            return 0;
        }
        return bs(head, 1, mostLeftLevel(head, 1));
    }

    private static int bs(Node node, int level, int height) {
        if (level == height) {
            return 1;
        }
        int ans = 0;
        if (mostLeftLevel(node.right, level + 1) == height) {
            ans = (1 << (height - level)) + bs(node.right, level + 1, height);
        } else {
            ans = (1 << (height - level - 1)) + bs(node.left, level + 1, height);
        }
        return ans;
    }

    private static int mostLeftLevel(Node node, int level) {
        while (node != null) {
            level++;
            node = node.left;
        }
        return level - 1;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        System.out.println(nodeNum(head));

    }

}
