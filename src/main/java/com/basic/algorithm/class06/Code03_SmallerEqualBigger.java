package com.basic.algorithm.class06;

import com.basic.algorithm.Node;

import javax.print.attribute.standard.NumberUp;

/**
 * 对链表进行分区，< = > 三个区域依次从左到右
 */
public class Code03_SmallerEqualBigger {

    // 方法一：先将节点放入数组中，使用数组的分区方法
    public static Node listPartition1(Node head, int pivot) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        int i = 0;
        while (cur != null) {
            i++;
            cur = cur.next;
        }
        Node[] nodes = new Node[i];
        i = 0;
        cur = head;
        while (cur != null) {
            nodes[i++] = cur;
            cur = cur.next;
        }
        arrPartition(nodes, pivot);
        for (i = 1; i < nodes.length; i++) {
           nodes[i - 1].next = nodes[i];
        }
        nodes[i - 1].next = null;
        return nodes[0];
    }

    public static void arrPartition(Node[] arrNode, int pivot) {
        int less = -1;
        int more = arrNode.length;
        int index = 0;
        while (index < more){
            if (arrNode[index].value == pivot){
                index++;
            }else if (arrNode[index].value < pivot){
                swap(arrNode, index++, ++less);
            }else {
                swap(arrNode, index, --more);
            }
        }
    }

    public static void swap(Node[] nodeArr, int i, int j) {
        Node temp = nodeArr[i];
        nodeArr[i] = nodeArr[j];
        nodeArr[j] = temp;
    }

    // 将整个链表拆分成小于等于大于的三个链表，最后将他们连接起来
    public static Node listPartition2(Node head, int pivot) {
        Node sT = null;
        Node sH = null;
        Node eH = null;
        Node eT = null;
        Node mH = null;
        Node mT = null;
        Node next = null;
        while (head != null){
            next = head.next;
            head.next = null;
            if (head.value < pivot){
                if (sH == null){
                    sH = head;
                    sT = head;
                }else {
                    sT.next = head;
                    sT = head;
                }
            }else if (head.value == pivot){
                if (eT == null){
                    eH = head;
                    eT = head;
                }else {
                    eT.next = head;
                    eT = head;
                }
            }else {
                if (mT == null){
                    mH = head;
                    mT = head;
                }else {
                    mT.next = head;
                    mT = head;
                }
            }
            head = next;
        }
        if (sT != null){
            sT.next = eH == null ? (mH == null ? null : mH) : eH;
        }

        if (eT != null){
            eT.next = mH == null ? null : mH;
        }
        return sH != null ? sH : (eH != null ? eH : mH);
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
        Node head1 = new Node(7);
        head1.next = new Node(9);
        head1.next.next = new Node(1);
        head1.next.next.next = new Node(8);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(2);
        head1.next.next.next.next.next.next = new Node(5);
        printLinkedList(head1);
//        head1 = listPartition1(head1, 7);
        head1 = listPartition2(head1, 4);
        printLinkedList(head1);

    }
}
