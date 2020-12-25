package com.basic.campus003.class03;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Code06_TopKTimes {

    public static class Node{
        private String str;
        private int times;

        public Node(String str, int times) {
            this.str = str;
            this.times = times;
        }
    }

    public static class NodeComparator implements Comparator<Node>{
        @Override
        public int compare(Node o1, Node o2) {
            return o1.times - o2.times;
        }
    }

    public static void printTopKAndRank(String[] strings,int topK){
        if (strings == null || strings.length ==0 || topK<1 || topK>strings.length){
            return;
        }
        HashMap<String,Integer> map = new HashMap<>();
        for (String s : strings){
            if (map.containsKey(s)){
                map.put(s,map.get(s)+1);
            }else {
                map.put(s,1);
            }
        }
        topK = Math.min(topK,strings.length);
        PriorityQueue<Node> heap = new PriorityQueue<>(new NodeComparator());
        for (Map.Entry<String,Integer> entry:map.entrySet()){
            Node node = new Node(entry.getKey(),entry.getValue());
            if (heap.size() < topK){
                heap.add(node);
            }else if (node.times > heap.peek().times){
                heap.poll();
                heap.add(node);
            }
        }
        while (!heap.isEmpty()){
            System.out.println(heap.poll().str);
        }
    }

    public static String[] generateRandomArray(int len, int max) {
        String[] res = new String[len];
        for (int i = 0; i != len; i++) {
            res[i] = String.valueOf((int) (Math.random() * (max + 1)));
        }
        return res;
    }

    public static void printArray(String[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        String[] arr1 = { "A", "B", "A", "C", "A", "C", "B", "B", "K" };
        printTopKAndRank(arr1, 2);

        String[] arr2 = generateRandomArray(50, 10);
        int topK = 3;
        printArray(arr2);
        printTopKAndRank(arr2, topK);

    }
}
