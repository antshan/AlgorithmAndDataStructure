package com.basic.campus003.class03;

import com.sun.scenario.effect.impl.sw.java.JSWColorAdjustPeer;

import java.util.HashMap;
import java.util.Map;

import static com.sun.tools.javac.jvm.ByteCodes.swap;

public class Code07_TopKTimesRealTime {

    public static class Node {
        private String str;
        private int times;

        public Node(String str, int times) {
            this.str = str;
            this.times = times;
        }
    }

    public static class TopRecord {
        private Node[] heap;
        private int heapSize;

        private Map<String, Node> strNodeMap;
        private Map<Node, Integer> indexNodeMap;

        public TopRecord(int k) {
            this.heapSize = 0;
            this.heap = new Node[k];
            this.strNodeMap = new HashMap<>();
            this.indexNodeMap = new HashMap<>();
        }

        public void add(String s) {
            Node cur = null;
            int preIndex = -1;
            if (!strNodeMap.containsKey(s)) {
                cur = new Node(s, 1);
                strNodeMap.put(s, cur);
                indexNodeMap.put(cur, -1);
            } else {
                cur = strNodeMap.get(s);
                cur.times++;
                preIndex = indexNodeMap.get(cur);
            }

            if (preIndex == -1) {//不在堆中
                if (heapSize == heap.length) {//堆满
                    if (cur.times > heap[0].times) {
                        indexNodeMap.put(heap[0], -1);
                        indexNodeMap.put(cur, 0);
                        heap[0] = cur;
                        heapify(0, heapSize);
                    }
                } else {
                    indexNodeMap.put(cur, heapSize);
                    heap[heapSize] = cur;
                    heapInsert(heapSize++);
                }
            } else {
                heapify(preIndex, heapSize);
            }

        }

        public void printTopK() {
            System.out.println("TopK:");
            for (int i = 0; i < heap.length; i++) {
                if (heap[i] == null) {
                    return;
                }
                System.out.println("Str:" + heap[i].str);
                System.out.println("Times:" + heap[i].times);
            }
        }

        private void heapInsert(int i) {
            while (i != 0) {
                int parent = (i - 2) / 2;
                if (heap[parent].times > heap[i].times) {
                    swap(i, parent);
                    i = parent;
                } else {
                    break;
                }
            }
        }

        private void heapify(int i, int heapSize) {
            int l = i * 2 + 1;
            int r = i * 2 + 2;
            int smallest = i;
            while (l < heapSize) {
                if (heap[l].times < heap[smallest].times) {
                    smallest = l;
                }
                if (r < heapSize && heap[r].times < heap[smallest].times) {
                    smallest = r;
                }
                if (smallest != i) {
                    swap(i, smallest);
                } else {
                    break;
                }
                i = smallest;
                l = i * 2 + 1;
                r = i * 2 + 2;
            }
        }

        private void swap(int i, int smallest) {
            indexNodeMap.put(heap[i], smallest);
            indexNodeMap.put(heap[smallest], i);
            Node temp = heap[i];
            heap[i] = heap[smallest];
            heap[smallest] = temp;
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
            TopRecord record = new TopRecord(2);
            record.add("zuo");
            record.printTopK();
            record.add("cheng");
            record.add("cheng");
            record.printTopK();
            record.add("Yun");
            record.add("Yun");
            record.printTopK();
            record.add("Shan");
            record.add("Shan");
            record.add("Shan");
            record.add("Shan");
            System.out.println("=======================");
            record.printTopK();

        }
    }
}
