package com.basic.algorithm.class07;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * 计算二叉树所有层节点个数的最大值
 */
public class Code06_TreeMaxWidth {

    public static int maxWidthWithMap(Node head){
        if (head == null){
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        Map<Node, Integer> map = new HashMap<>();
        map.put(head, 1);
        int curLevel = 1;
        int curLevelNodes = 0;
        int max = 0;
        while (!queue.isEmpty()){
            Node cur = queue.poll();
            int curNodeLevel = map.get(cur);
            if (cur.left != null){
                queue.add(cur.left);
                map.put(cur.left, curNodeLevel + 1);
            }
            if (cur.right != null){
                queue.add(cur.right);
                map.put(cur.right, curNodeLevel + 1);
            }
            if (curLevel == curNodeLevel){
                curLevelNodes++;
                max = Math.max(max,curLevelNodes);
            }else {
                max = Math.max(max,curLevelNodes);
                curLevel = curNodeLevel;
                curLevelNodes = 1;
            }
        }
        return max;
    }

    public static int maxWidthNoMap(Node head){
        if (head == null){
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        Node curEnd = head;
        Node nextEnd = null;
        int curLevelNodes = 0;
        int max = 0;
        while (!queue.isEmpty()){
            Node cur = queue.poll();
            curLevelNodes++;
            if (cur.left != null){
                queue.add(cur.left);
                nextEnd = cur.left;
            }
            if (cur.right !=null){
                queue.add(cur.right);
                nextEnd = cur.right;
            }
            if (cur == curEnd){
                max = Math.max(max, curLevelNodes);
                curLevelNodes = 0;
                curEnd = nextEnd;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

        System.out.println(maxWidthNoMap(head));
        System.out.println(maxWidthWithMap(head));
        System.out.println("========");
    }
}
