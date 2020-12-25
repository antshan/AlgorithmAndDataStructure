package com.basic.algorithm.class04;

public class Code01_Heap {

    public static class MyMaxHeap {

        private int[] num;
        private int heapSize;
        private final int limit;

        public MyMaxHeap(int limit) {
            this.heapSize = 0;
            this.limit = limit;
            num = new int[limit];
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }

        public boolean isFull() {
            return heapSize == limit;
        }

        public void push(int value) {
            if (isFull()) {
                throw new RuntimeException("大根堆已满!");
            }
            num[heapSize] = value;
            heapInsert(num, heapSize++);
        }

        public int pop() {
            if (isEmpty()) {
                throw new RuntimeException("大根堆已空");
            }
            int res = num[0];
            swap(num, 0, --heapSize);
            heapify(num, 0, heapSize);
            return res;
        }

        private void heapify(int[] num, int index, int heapSize) {
            int left = index * 2 + 1;
            while (left < heapSize) {
                int largest = left + 1 < heapSize && num[left] < num[left + 1] ? (left + 1) : left;
                largest = num[index] > num[largest] ? index : largest;
                if (largest == index) {
                    break;
                }
                swap(num, index, largest);
                index = largest;
                left = index * 2 + 1;
            }
        }

        private void heapInsert(int[] num, int index) {
            if (index <= 0) {
                return;
            }
            while (num[index] > num[(index - 1) / 2]) {
                swap(num, index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        private void swap(int[] arr, int i, int j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
}
