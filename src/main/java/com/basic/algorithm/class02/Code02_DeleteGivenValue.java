package com.basic.algorithm.class02;

import com.basic.algorithm.Node;

public class Code02_DeleteGivenValue {

    public static Node removeGivenValue(Node head, int num) {
        while (head != null) {
            if (head.value != num) {
                break;
            }
            head = head.next;
        }
        Node pre = head;
        Node cur = head;
        while (cur != null) {
            if (cur.value == num) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }

    public static void main(String[] args) {
        Node node = new Node(1);
        node.next = new Node(2);
        node.next.next = new Node(3);
        node.next.next.next = new Node(2);
        Node head = removeGivenValue(node,3);
        System.out.println(head);
    }
}
