package com.basic.algorithm.class08;

import com.basic.algorithm.class07.Node;

import java.util.*;

public class Code07_LowestCommonAncestor {

    public static Node lowestAncestor(Node head, Node n1, Node n2){
        if (head == null) {
            return null;
        }
        Map<Node, Node> headIndexMap = new HashMap<>();
        headIndexMap.put(head, null);
        processHeadIndex(headIndexMap, head);
        Set<Node> set = new HashSet<>();
        Node cur = n1;
        while (cur != null){
            set.add(cur);
            cur = headIndexMap.get(cur);
        }
        cur = n2;
        while (cur != null){
            if (set.contains(cur)){
                return cur;
            }
            cur = headIndexMap.get(cur);
        }
        return cur;
    }

    private static void processHeadIndex(Map<Node, Node> headIndexMap, Node head) {
        if (head == null){
            return;
        }
        if (head.left != null){
            headIndexMap.put(head.left, head);
            processHeadIndex(headIndexMap, head.left);
        }
        if (head.right != null){
            headIndexMap.put(head.right, head);
            processHeadIndex(headIndexMap, head.right);
        }
    }

    public static class Info{
        Node ans;
        boolean find1;
        boolean find2;

        public Info(Node ans, boolean find1, boolean find2) {
            this.ans = ans;
            this.find1 = find1;
            this.find2 = find2;
        }
    }

    public static Node lowestCommonAncestor2(Node head, Node n1, Node n2){
        if (head == null){
            return null;
        }
        return process(head, n1, n2).ans;
    }

    private static Info process(Node head, Node n1, Node n2) {
        if (head == null){
            return new Info(null, false, false);
        }
        Info leftInfo = process(head.left, n1, n2);
        Info rightInfo = process(head.right, n1, n2);
        boolean find1 = (head == n1) || leftInfo.find1 || rightInfo.find1;
        boolean find2 = (head == n2) || leftInfo.find2 || rightInfo.find2;
        Node ans = null;
        if (leftInfo.ans != null){
            ans = leftInfo.ans;
        }else if (rightInfo.ans != null){
            ans = rightInfo.ans;
        }
        else {
            if (find1 && find2){
                ans = head;
            }
        }
        return new Info(ans, find1, find2);
    }

    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    // for test
    public static Node pickRandomOne(Node head) {
        if (head == null) {
            return null;
        }
        ArrayList<Node> arr = new ArrayList<>();
        fillPrelist(head, arr);
        int randomIndex = (int) (Math.random() * arr.size());
        return arr.get(randomIndex);
    }

    // for test
    public static void fillPrelist(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        arr.add(head);
        fillPrelist(head.left, arr);
        fillPrelist(head.right, arr);
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 10000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            Node o1 = pickRandomOne(head);
            Node o2 = pickRandomOne(head);
            if (lowestAncestor(head, o1, o2) != lowestCommonAncestor2(head, o1, o2)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
