package com.basic.algorithm.class06;

import com.basic.algorithm.Node;

import java.util.Stack;

/**
 * 判断单链表是否具有回文
 */
public class Code02_IsPanlindromeList {

    // 该方法是先将链表元素依次压入栈中，然后再将栈中的元素依次弹出与链表中的元素进行比较
    // 需要额外的空间复杂度 O(n)
    public static boolean isPalindrome1(Node head){
        Stack<Node> stack = new Stack<>();
        Node node = head;
        while (node != null){
            stack.push(node);
            node = node.next;
        }

        while (!stack.isEmpty()){
            if (stack.pop().value != head.value){
                return false;
            }
            head = head.next;
        }
        return true;
    }

    // 将链表的下半部分放入栈内，依次弹出和链表的上半部分进行对比, n/2的空间复杂度
    public static boolean isPalindrome2(Node head){
        if (head == null || head.next == null){
            return true;
        }
        Node slow = head.next;
        Node fast = head.next;
        while (fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        Node n1 = slow;
        Stack<Node> stack = new Stack<>();
        while (n1 != null){
            stack.push(n1);
            n1 = n1.next;
        }

        while (!stack.isEmpty()){
            if (stack.pop().value != head.value){
                return false;
            }
            head = head.next;
        }
        return true;
    }

    // 将单链表的后半部分进行反转，依次从链表的头部和尾部向中间移动，数据一致则是回文，额外空间复杂度O(1)
    public static boolean isPalindrome3(Node head){
        if (head == null || head.next == null){
            return true;
        }
        // 先找到中点(不论是上中点或者下中点)
        Node slow = head;
        Node fast = head;
        while (fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        Node n1 = slow;
        Node n2 = slow.next;
        // 将链表的上下部分断开
        n1.next = null;
        // 反转链表的下半部分
        Node pre = null;
        Node next = null;
        while (n2 != null){
            next = n2.next;
            n2.next = pre;
            pre = n2;
            n2 = next;
        }

        // 将链表的上下两部分的值进行对比
        Node n3 = head;
        Node n4 = pre;
        boolean res = true;
        while (n3 != null && pre != null){
            if (n3.value != pre.value){
                res = false;
            }
            n3 = n3.next;
            pre = pre.next;
        }

        // 还原原来的链表
        pre = null;
        next = null;
        while (n4 != null){
            next = n4.next;
            n4.next = pre;
            pre = n4;
            n4 = next;
        }
        n1.next = pre;
        return res;
    }

    public static void printLinkedList(Node node) {
        System.out.print("Linked List: ");
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {

        Node head = null;
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(2);
        head.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(2);
        head.next.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

    }
}
