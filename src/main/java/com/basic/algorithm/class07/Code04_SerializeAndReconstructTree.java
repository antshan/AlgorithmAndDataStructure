package com.basic.algorithm.class07;

import javax.print.attribute.standard.NumberUp;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 序列化和反序列化二叉树
 */
public class Code04_SerializeAndReconstructTree {

    public static Queue<String> preSerial(Node head) {
        Queue<String> res = new LinkedList<>();
        pre(head, res);
        return res;
    }

    private static void pre(Node head, Queue<String> ans) {
        if (head == null){
            ans.add(null);
        }else {
            ans.add(String.valueOf(head.value));
            pre(head.left, ans);
            pre(head.right, ans);
        }
    }

    public static Queue<String> inSerial(Node head) {
        Queue<String> res = new LinkedList<>();
        in(head, res);
        return res;
    }

    private static void in(Node head, Queue<String> ans) {
        if (head == null){
            ans.add(null);
        }else {
            in(head.left, ans);
            ans.add(String.valueOf(head.value));
            in(head.right, ans);
        }
    }

    public static Queue<String> posSerial(Node head){
        Queue<String> res = new LinkedList<>();
        pos(head, res);
        return res;
    }

    private static void pos(Node head, Queue<String> ans) {
        if (head == null){
            ans.add(null);
        }else {
            pos(head.left, ans);
            pos(head.right, ans);
            ans.add(String.valueOf(head.value));
        }
    }

    public static Node buildByPreQueue(Queue<String> preList){
        if (preList.isEmpty() || preList == null){
            return null;
        }
        return preb(preList);
    }

    private static Node preb(Queue<String> preList) {
        String value = preList.poll();
        if (value == null){
            return null;
        }
        Node head = new Node(Integer.valueOf(value));
        head.left = preb(preList);
        head.right = preb(preList);
        return head;
    }

    public static Node buildByPosQueue(Queue<String> posList){
        if (posList.isEmpty() || posList == null){
            return null;
        }
        Stack<String> stack = new Stack<>();
        while (!posList.isEmpty()){
            stack.push(posList.poll());
        }
        return posb(stack);
    }

    private static Node posb(Stack<String> posList) {
        String value = posList.pop();
        if (value == null){
            return null;
        }
        Node head = new Node(Integer.valueOf(value));
        head.right = posb(posList);
        head.left = posb(posList);
        return head;
    }

    public static Queue<String> levelSerial(Node head){
        Queue<String> res = new LinkedList<>();
        if (head == null){
            res.add(null);
        }else {
            Queue<Node> queue = new LinkedList<>();
            queue.add(head);
            res.add(String.valueOf(head.value));
            while (!queue.isEmpty()){
                head = queue.poll();
                if (head.left != null){
                    queue.add(head.left);
                    res.add(String.valueOf(head.left.value));
                }else {
                    res.add(null);
                }
                if (head.right != null){
                    queue.add(head.right);
                    res.add(String.valueOf(head.right.value));
                }else {
                    res.add(null);
                }
            }
        }
        return res;
    }

    public static Node buildByLevelQueue(Queue<String> levelList){
        if (levelList == null){
            return null;
        }
        Queue<Node> queue = new LinkedList<>();
        Node head = generateNode(levelList.poll());
        queue.add(head);
        Node cur = null;
        while (!queue.isEmpty()){
            cur = queue.poll();
            cur.left = generateNode(levelList.poll());
            cur.right = generateNode(levelList.poll());
            if (cur.left != null){
                queue.add(cur.left);
            }
            if (cur.right != null){
                queue.add(cur.right);
            }
        }
        return head;
    }

    private static Node generateNode(String poll) {
        if (poll == null){
            return null;
        }
        return new Node(Integer.valueOf(poll));
    }

    public static void main(String[] args) {

        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

        Queue<String> res = preSerial(head);
        Node buildFromLevel = buildByPreQueue(res);

        res = inSerial(head);

        res = posSerial(head);
        buildFromLevel = buildByPosQueue(res);

        res = levelSerial(head);
        buildFromLevel = buildByLevelQueue(res);
        System.out.println();
    }

}
