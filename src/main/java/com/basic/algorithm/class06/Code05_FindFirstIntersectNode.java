package com.basic.algorithm.class06;

import com.basic.algorithm.Node;

import java.util.Map;

/**
 * 两个单链表可能有环或者无环相交，求他们相交的节点
 */
public class Code05_FindFirstIntersectNode {

    public static Node getIntersectNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node loop1 = getLoopNode(head1);
        Node loop2 = getLoopNode(head2);
        if (loop1 == null && loop2 == null) {
            return noLoop(head1, head2);
        } else if (loop1 != null && loop2 != null) {
            return bothLoop(head1, loop1, head2, loop2);
        }
        return null;
    }

    // 获取链表第一个入环节点，无环则返回null
    public static Node getLoopNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node n1 = head.next;
        Node n2 = head.next.next;
        while (n1 != n2) {
            if (n2.next == null || n2.next.next == null) {
                return null;
            }
            n1 = n1.next;
            n2 = n2.next.next;
        }
        n2 = head;
        while (n1 != n2) {
            n1 = n1.next;
            n2 = n2.next;
        }
        return n1;
    }

    // 如果两个链表都无环，返回第一个相交节点，如果不想交，返回null
    public static Node noLoop(Node head1, Node head2) {
        Node n1 = head1;
        Node n2 = head2;
        int n = 0;
        while (n1.next != null) {
            n++;
            n1 = n1.next;
        }
        while (n2.next != null) {
            n--;
            n2 = n2.next;
        }
        n1 = n > 0 ? head1 : head2;
        n2 = n1 == head1 ? head2 : head1;
        n = Math.abs(n);
        while (n > 0) {
            n--;
            n1 = n1.next;
        }
        while (n1 != n2) {
            n1 = n1.next;
            n2 = n2.next;
        }
        return n1;
    }

    // 两个有环链表，返回第一个相交节点，如果不相交返回null
    public static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
        Node n1 = head1;
        Node n2 = head2;
        if (loop1 == loop2) {
            int n = 0;
            while (n1.next != loop1) {
                n++;
                n1 = n1.next;
            }
            while (n2.next != loop2) {
                n--;
                n2 = n2.next;
            }
            n1 = n > 0 ? head1 : head2;
            n2 = n1 == head1 ? head2 : head1;
            n = Math.abs(n);
            while (n > 0){
                n--;
                n1 = n1.next;
            }
            while (n1 != n2) {
                n1 = n1.next;
                n2 = n2.next;
            }
            return n1;
        } else {
            Node cur = loop1.next;
            while (cur != loop1) {
                if (cur == loop2) {
                    return cur;
                }
                cur = cur.next;
            }
            return null;
        }
    }

    public static void main(String[] args) {
        // 1->2->3->4->5->6->7->null
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);

        // 0->9->8->6->7->null
        Node head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);

        // 1->2->3->4->5->6->7->4...
        head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->2...
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next; // 8->2
        System.out.println(getIntersectNode(head1, head2).value);

        // 0->9->8->6->4->5->6..
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);

    }

}

