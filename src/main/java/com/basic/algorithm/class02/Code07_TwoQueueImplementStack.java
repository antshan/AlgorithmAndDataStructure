package com.basic.algorithm.class02;

import java.util.LinkedList;
import java.util.Queue;

public class Code07_TwoQueueImplementStack {

    public static class TwoQueueStack{
        private Queue<Integer> data;
        private Queue<Integer> helper;

        public TwoQueueStack() {
            data = new LinkedList<>();
            helper = new LinkedList<>();
        }

        public void push(Integer value){
            data.add(value);
        }

        public int pop(){
            if (data.isEmpty()){
                throw new RuntimeException("队列为空");
            }
            while (data.size()>1){
                helper.add(data.poll());
            }
            int res = data.poll();
            Queue<Integer> temp = data;
            data = helper;
            helper = temp;
            return res;
        }

        public int peek(){
            if (data.isEmpty()){
                throw new RuntimeException("队列为空");
            }
            while (data.size() > 1){
                helper.add(data.poll());
            }
            int res = data.peek();
            helper.add(data.poll());
            Queue<Integer> temp = data;
            data = helper;
            helper = temp;
            return res;
        }
    }
}
