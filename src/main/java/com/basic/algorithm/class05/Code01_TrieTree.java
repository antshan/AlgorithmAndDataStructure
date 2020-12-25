package com.basic.algorithm.class05;

import java.util.HashMap;
import java.util.Map;

/**
 * 前缀树的两种实现方式
 */
public class Code01_TrieTree {

    public static class Node1 {

        private int pass;
        private int end;

        private Node1[] nexts;

        public Node1() {
            this.pass = 0;
            this.end = 0;
            this.nexts = new Node1[26];
        }
    }

    public static class TrieTree1 {

        private Node1 root;

        public TrieTree1() {
            this.root = new Node1();
        }

        public void insert(String value) {
            if (value == null || value.length() == 0) {
                return;
            }
            Node1 node = root;
            node.pass++;
            char[] chars = value.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                int index = chars[i] - 'a';
                if (node.nexts[index] == null) {
                    node.nexts[index] = new Node1();
                }
                node = node.nexts[index];
                node.pass++;
            }
            node.end++;
        }

        public int search(String value) {
            if (value == null || value.length() == 0) {
                return 0;
            }
            Node1 node = root;
            char[] chars = value.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                int index = chars[i] - 'a';
                if (node.nexts[index] == null) {
                    return 0;
                }
                node = node.nexts[index];
            }
            return node.end;
        }

        public int prefixNum(String value) {
            if (value == null || value.length() == 0) {
                return 0;
            }
            Node1 node = root;
            char[] chars = value.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                int index = chars[i] - 'a';
                if (node.nexts[index] == null) {
                    return 0;
                }
                node = node.nexts[index];
            }
            return node.pass;
        }

        public void delete(String value) {
            if (search(value) == 0) {
                return;
            }
            Node1 node = root;
            node.pass--;
            char[] chars = value.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                int index = chars[i] - 'a';
                if (--node.nexts[index].pass == 0) {
                    node.nexts[index] = null;
                    return;
                }
                node = node.nexts[index];
            }
            node.end--;
        }
    }

    public static class Node2 {

        private int pass;
        private int end;

        private Map<Integer, Node2> nexts;

        public Node2() {
            this.pass = 0;
            this.end = 0;
            this.nexts = new HashMap<>();
        }
    }

    public static class TrieTree2 {

        private Node2 root;

        public TrieTree2() {
            this.root = new Node2();
        }

        public void insert(String value) {
            if (value == null || value.length() == 0) {
                return;
            }
            Node2 node = root;
            node.pass++;
            char[] chars = value.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                int index = chars[i] - 'a';
                if (node.nexts.get(index) == null) {
                    node.nexts.put(index, new Node2());
                }
                node = node.nexts.get(index);
                node.pass++;
            }
            node.end++;
        }

        public int search(String value) {
            if (value == null || value.length() == 0) {
                return 0;
            }
            Node2 node = root;
            char[] chars = value.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                int index = chars[i] - 'a';
                if (node.nexts.get(index) == null) {
                    return 0;
                }
                node = node.nexts.get(index);
            }
            return node.end;
        }

        public int prefixNumber(String value) {
            if (value == null || value.length() == 0) {
                return 0;
            }
            Node2 node = root;
            char[] chars = value.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                int index = chars[i] - 'a';
                if (node.nexts.get(index) == null) {
                    return 0;
                }
                node = node.nexts.get(index);
            }
            return node.pass;
        }

        public void delete(String value) {
            if (search(value) == 0) {
                return;
            }
            char[] chars = value.toCharArray();
            Node2 node = root;
            node.pass--;
            for (int i = 0; i < chars.length; i++) {
                int index = chars[i] - 'a';
                if (--node.nexts.get(index).pass == 0) {
                    node.nexts.put(index, null);
                    return;
                }
                node = node.nexts.get(index);
            }
            node.end--;
        }
    }

}
