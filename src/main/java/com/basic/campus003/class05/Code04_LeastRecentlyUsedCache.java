package com.basic.campus003.class05;

import java.util.HashMap;
import java.util.Map;

public class Code04_LeastRecentlyUsedCache {

    public static class Node<K, V> {
        private K key;
        private V value;
        private Node<K, V> last;
        private Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public static class NodeDoubleLikedList<K, V> {
        private Node<K, V> head;
        private Node<K, V> tail;

        public NodeDoubleLikedList() {
            head = null;
            tail = null;
        }

        public void addNode(Node<K, V> newNode) {
            if (newNode == null) {
                return;
            }
            if (tail == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                newNode.last = tail;
                tail = newNode;
            }
        }

        public void moveNodeToTail(Node<K, V> node) {
            if (tail == null || node == null || tail == node) {
                return;
            }
            if (head == node) {
                head = head.next;
            } else {
                node.last.next = node.next;
            }
            node.next.last = node.last;
            tail.next = node;
            node.last = tail;
            tail = node;
            node.next = null;
        }

        public Node removeHead() {
            if (head == null) {
                return null;
            }
            Node<K, V> res = head;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                head = head.next;
                res.next.last = null;
                res.next = null;
            }
            return res;
        }
    }

    public static class MyCache<K, V> {
        private final int capacity;
        private Map<K, Node<K, V>> nodeMap;
        private NodeDoubleLikedList<K, V> nodeList;

        public MyCache(int capacity) {
            if (capacity < 1) {
                throw new RuntimeException("cap value is too low!");
            }
            this.capacity = capacity;
            nodeMap = new HashMap<>();
            nodeList = new NodeDoubleLikedList<>();
        }

        public V get(K key) {
            if (nodeMap.containsKey(key)) {
                Node<K, V> res = nodeMap.get(key);
                nodeList.moveNodeToTail(res);
                return res.value;
            }
            return null;
        }

        public void set(K key, V value) {
            if (nodeMap.containsKey(key)) {
                Node<K, V> res = nodeMap.get(key);
                res.value = value;
                nodeList.moveNodeToTail(res);
            }else {
                if (capacity == nodeMap.size()){
                    Node<K,V> head = nodeList.removeHead();
                    nodeMap.remove(head.key);
                }
                Node<K,V> res = new Node<>(key,value);
                nodeMap.put(key, res);
                nodeList.addNode(res);
            }
        }
    }

    public static void main(String[] args) {
        MyCache<String, Integer> testCache = new MyCache<String, Integer>(3);
        testCache.set("A", 1);
        testCache.set("B", 2);
        testCache.set("C", 3);
        System.out.println(testCache.get("B"));
        System.out.println(testCache.get("A"));
        testCache.set("D", 4);
        System.out.println(testCache.get("D"));
        System.out.println(testCache.get("C"));

    }
}
