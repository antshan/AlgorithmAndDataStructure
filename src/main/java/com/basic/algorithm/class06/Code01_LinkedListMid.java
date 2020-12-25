package com.basic.algorithm.class06;

import com.basic.algorithm.Node;

/**
 * 求单链表的中间节点以及中间节点的前一个节点
 */
public class Code01_LinkedListMid {


    // 链表的中间节点或者上中节点
    public static Node midOrUpMidNode(Node head){
        if (head == null || head.next == null || head.next.next == null){
            return head;
        }
        Node slow = head.next;
        Node fast = head.next.next;
        while (fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // 链表的中间或者下中节点
    public static Node midOrDownMidNode(Node head){
        if (head == null || head.next == null){
            return head;
        }
        Node slow = head.next;
        Node fast = head.next;
        while (fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // 链表的中间或者上中节点的前一个节点
    public static Node midOrUpMidPreNode(Node head){
        if (head == null || head.next == null || head.next.next == null){
            return null;
        }
        Node slow = head;
        Node fast = head.next.next;
        while (fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // 链表的中间或者下中节点的前一个节点
    public static Node midOrDownMidPreNode(Node head){
        if (head == null || head.next == null){
            return null;
        }
        Node slow = head;
        Node fast = head.next;
        while (fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        Node test = null;
        test = new Node(1);
        test.next = new Node(2);
        test.next.next = new Node(3);
        test.next.next.next = new Node(4);

        Node res = midOrDownMidNode(test);
        System.out.println(res.value);

        test.next.next.next.next = new Node(5);
        res = midOrDownMidNode(test);
        System.out.println(res.value);

        test.next.next.next.next.next = new Node(6);
        res = midOrUpMidNode(test);
        System.out.println(res.value);

        res = midOrDownMidPreNode(test);
        System.out.println(res.value);

        res = midOrUpMidPreNode(test);
        System.out.println(res.value);

        test.next.next.next.next.next.next = new Node(7);
        res = midOrUpMidNode(test);
        System.out.println(res.value);

        res = midOrDownMidPreNode(test);
        System.out.println(res.value);

        res = midOrUpMidPreNode(test);
        System.out.println(res.value);

    }
}
