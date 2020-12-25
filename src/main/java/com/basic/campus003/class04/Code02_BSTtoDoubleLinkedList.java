package com.basic.campus003.class04;

public class Code02_BSTtoDoubleLinkedList {

    public static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static class Info {
        private Node start;
        private Node end;

        public Info(Node start, Node end) {
            this.start = start;
            this.end = end;
        }
    }

    public static Node convert2(Node head) {
        if (head == null) {
            return null;
        }
        return process(head).start;
    }

    private static Info process(Node head) {
        if (head == null) {
            return new Info(null, null);
        }
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);
        head.left = leftInfo.end;
        head.right = rightInfo.start;
        if (leftInfo.end != null){
            leftInfo.end.right = head;
        }
        if (rightInfo.start != null){
            rightInfo.start.left = head;
        }

        return new Info(leftInfo.start != null ? leftInfo.start : head,
                        rightInfo.end != null ? rightInfo.end : head );
    }
}
