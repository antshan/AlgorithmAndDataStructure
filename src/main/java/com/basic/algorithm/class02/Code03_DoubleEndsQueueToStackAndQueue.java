package com.basic.algorithm.class02;

public class Code03_DoubleEndsQueueToStackAndQueue {


    public static class Node<T> {
        private T value;
        private Node<T> last;
        private Node<T> next;

        public Node(T value) {
            this.value = value;
        }
    }

    public static class DoubleEndsQueue<T> {
        private Node<T> head;
        private Node<T> tail;

        public void addFromHead(T value){
            Node<T> node = new Node<>(value);
            if (head == null){
                head = node;
                tail = node;
            }else {
                node.next = head;
                head.last = node;
                head = node;
            }
        }

        public void addFromBottom(T value){
            Node<T> cur = new Node<>(value);
            if (tail == null){
                head = cur;
                tail = cur;
            }else {
                tail.next = cur;
                cur.last = tail;
                tail = cur;
            }
        }

        public T popFromHead(){
            if (head == null){
                return null;
            }
            Node<T> cur = head;
            if (head == tail){
                head = null;
                tail = null;
            }else {
                head = cur.next;
                head.last = null;
                cur.next = null;

            }
            return cur.value;
        }

        public T popFromTail(){
            if (tail == null){
                return null;
            }
            Node<T> cur = tail;
            if (head == tail){
                head = null;
                tail = null;
            }else {
                tail = cur.last;
                tail.next = null;
                cur.last = null;
            }
            return cur.value;
        }
    }

    public static class MyStack<T>{
        private DoubleEndsQueue<T> doubleEndsQueue;

        public MyStack() {
            this.doubleEndsQueue = new DoubleEndsQueue<>();
        }

        public void push(T value){
            doubleEndsQueue.addFromHead(value);
        }

        public T pop(){
            return doubleEndsQueue.popFromHead();
        }
    }

    public static class MyQueue<T>{

        private DoubleEndsQueue<T> doubleEndsQueue;

        public MyQueue() {
            this.doubleEndsQueue = new DoubleEndsQueue<>();
        }

        public void push(T value){
            doubleEndsQueue.addFromBottom(value);
        }

        public T poll(){
            return doubleEndsQueue.popFromHead();
        }
    }
}
