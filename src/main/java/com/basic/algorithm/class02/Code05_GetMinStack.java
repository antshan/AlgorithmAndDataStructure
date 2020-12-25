package com.basic.algorithm.class02;

import java.util.Stack;

public class Code05_GetMinStack {

    public static class MyStack1 {
        private Stack<Integer> stackData;
        private Stack<Integer> stackMin;

        public void push(Integer value) {
            if (stackMin.isEmpty()) {
                stackMin.push(value);
            } else if (value <= getMin()) {
                stackMin.push(value);
            }
            stackData.push(value);
        }

        public Integer pop() {
            if (stackMin.isEmpty()) {
                throw new RuntimeException("栈为空!");
            }
            int res = stackData.pop();
            if (getMin() == res) {
                stackMin.pop();
            }
            return res;
        }

        public int getMin() {
            if (stackMin.isEmpty()) {
                throw new RuntimeException("栈为空");
            }
            return stackMin.peek();
        }
    }

    public static class MyStack2{

        private Stack<Integer> data;
        private Stack<Integer> min;

        public void push(Integer value){
            if (min.isEmpty()){
                min.push(value);
            }else if (getMin() <= value){
                min.push(getMin());
            }else {
                min.push(value);
            }
            data.push(value);
        }

        public int pop(){
            if (data.isEmpty()){
                throw new RuntimeException("栈为空!");
            }
            min.pop();
            return data.pop();
        }

        public int getMin(){
            if (min.isEmpty()){
                throw new RuntimeException("栈为空!");
            }
            return min.peek();
        }
    }
}
