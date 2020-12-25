package com.basic.algorithm.class07;

import java.util.Stack;

/**
 * 递归方式 进行二叉树的前序、中序以及后序遍历
 */
public class Code02_UnRecursiveTraversalBT {

    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static void pre(Node head){
        if (head == null){
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(head);
        while (!stack.isEmpty()){
            head = stack.pop();
            if (head.right != null){
                stack.push(head.right);
            }
            if (head.left != null){
                stack.push(head.left);
            }
            System.out.print(head.value+" ");
        }
        System.out.println();
    }

    public static void in(Node head){
        if (head == null){
            return;
        }
        Stack<Node> stack = new Stack<>();
        while (!stack.isEmpty() || head!=null){
            if (head != null){
                stack.push(head);
                head = head.left;
            }else {
                head = stack.pop();
                System.out.print(head.value+" ");
                head = head.right;
            }
        }
        System.out.println();
    }

    public static void pos1(Node head){
        // 使用两个栈实现后序遍历
        if (head == null){
            return;
        }
        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();
        stack1.push(head);
        while (!stack1.isEmpty()){
            head = stack1.pop();
            stack2.push(head);
            if (head.left != null){
                stack1.push(head.left);
            }
            if (head.right != null){
                stack1.push(head.right);
            }

        }
        while (!stack2.isEmpty()) {
            System.out.print(stack2.pop().value + " ");
        }
        System.out.println();
    }

    public static void pos2(Node head){
        // 使用一个栈实现后序遍历
        if (head == null){
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(head);
        Node c = null;
        while (!stack.isEmpty()){
            c = stack.peek();
            if (c.left != null && head != c.left && head != c.right){
                stack.push(c.left);
            }else if (c.right != null && head != c.right){
                stack.push(c.right);
            }else {
                System.out.print(stack.pop().value + " ");
                head = c;
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

        pre(head);
        System.out.println("========");
        in(head);
        System.out.println("========");
        pos1(head);
        System.out.println("========");
        pos2(head);

    }

}
