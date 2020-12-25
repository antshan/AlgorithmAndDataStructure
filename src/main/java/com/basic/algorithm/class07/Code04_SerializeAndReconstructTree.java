package com.basic.algorithm.class07;

import com.sun.xml.internal.org.jvnet.mimepull.CleanUpExecutorFactory;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 序列化和反序列化二叉树
 */
public class Code04_SerializeAndReconstructTree {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Queue<String> preSerial(Node head) {
        Queue<String> ans = new LinkedList<>();
        pre(head, ans);
        return ans;
    }

    private static void pre(Node head, Queue<String> ans) {
        if (head == null) {
            ans.add(null);
        } else {
            ans.add(String.valueOf(head.value));
            pre(head.left, ans);
            pre(head.right, ans);
        }
    }

    public static Queue<String> inSerial(Node head) {
        Queue<String> ans = new LinkedList<>();
        in(head, ans);
        return ans;
    }

    private static void in(Node head, Queue<String> ans) {
        if (head == null) {
            ans.add(null);
        } else {
            in(head.left, ans);
            ans.add(String.valueOf(head.value));
            in(head.right, ans);
        }
    }

    public static Queue<String> posSerial(Node head){
        Queue<String> ans = new LinkedList<>();
        pos(head,ans);
        return ans;
    }

    private static void pos(Node head, Queue<String> ans) {
        if (head == null){
            ans.add(null);
        }else {
            pos(head.left,ans);
            pos(head.right,ans);
            ans.add(String.valueOf(head.value));
        }
    }

    public static Node buildByPreQueue(Queue<String> preList){
        if (preList == null || preList.size() ==0){
            return null;
        }
        return preb(preList);
    }

    private static Node preb(Queue<String> preList) {
        String value = preList.poll();
        if (value == null){
            return null;
        }
        Node node = new Node(Integer.valueOf(value));
        node.left = preb(preList);
        node.right = preb(preList);
        return node;
    }

    public static Node buildByPosQueue(Queue<String> posList){
        if (posList == null || posList.size() == 0){
            return null;
        }
        Stack<String> stack = new Stack<>();
        while (!posList.isEmpty()){
            stack.push(posList.poll());
        }
        return posb(stack);
    }

    private static Node posb(Stack<String> posList) {
        String val = posList.pop();
        if (val == null){
            return null;
        }
        Node node = new Node(Integer.valueOf(val));
        node.right = posb(posList);
        node.left = posb(posList);
        return node;
    }

    public static Queue<String> levelSerial(Node head){
        Queue<String> ans = new LinkedList<>();
        if (head == null){
            ans.add(null);
        }else {
            Queue<Node> queue = new LinkedList<>();
            ans.add(String.valueOf(head.value));
            queue.add(head);
            while (!queue.isEmpty()){
                head = queue.poll();
                if (head.left != null){
                    queue.add(head.left);
                    ans.add(String.valueOf(head.left.value));
                }else {
                    ans.add(null);
                }

                if (head.right != null){
                    queue.add(head.right);
                    ans.add(String.valueOf(head.right.value));
                }else {
                    ans.add(null);
                }
            }
        }
        return ans;
    }

    public static Node buildByLevelQueue(Queue<String> levelList){
        if (levelList == null || levelList.size()==0){
            return null;
        }
        Node head = generateNode(levelList.poll());
        Queue<Node> queue = new LinkedList<>();
        if (head != null){
            queue.add(head);
        }
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
