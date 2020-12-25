package com.basic.algorithm.class07;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 宽度遍历二叉树
 *
 */
public class Code03_LevelTraversalBT {

    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    // 按层遍历数，输出节点
    public static void level(Node head){
        if (head == null){
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()){
            head = queue.poll();
            System.out.print(head.value + " ");
            if (head.left != null){
                queue.add(head.left);
            }
            if (head.right != null){
                queue.add(head.right);
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

        level(head);
        System.out.println("========");
    }

}
