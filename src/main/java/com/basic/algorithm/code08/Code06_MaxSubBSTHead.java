package com.basic.algorithm.code08;

import com.basic.algorithm.class07.Node;

import java.util.ArrayList;

public class Code06_MaxSubBSTHead {

    public static Node maxSubBSTHead(Node head){
        if (head == null){
            return null;
        }
        return process(head).maxSubBSTHead;
    }

    private static Info process(Node head) {
        if (head == null){
            return null;
        }
        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);

        int min = head.value;
        int max = head.value;
        Node maxSubBSTHead = null;
        int maxSubBSTSize = 0;
        if (leftInfo != null){
            min = Math.min(min, leftInfo.min);
            max = Math.max(max, leftInfo.max);
            maxSubBSTSize = leftInfo.maxSubBSTSize;
            maxSubBSTHead = leftInfo.maxSubBSTHead;
        }
        if (rightInfo != null){
            min = Math.min(min, rightInfo.min);
            max = Math.max(max, rightInfo.max);
            if (rightInfo.maxSubBSTSize > maxSubBSTSize){
                maxSubBSTSize = rightInfo.maxSubBSTSize;
                maxSubBSTHead = rightInfo.maxSubBSTHead;
            }
        }
        if ((leftInfo == null || (leftInfo.maxSubBSTHead == head.left && head.value > leftInfo.max))
        && (rightInfo == null || (rightInfo.maxSubBSTHead == head.right && head.value < rightInfo.min))){
            maxSubBSTHead = head;
            maxSubBSTSize = (leftInfo == null ? 0 : leftInfo.maxSubBSTSize) +
                    (rightInfo == null ? 0 : rightInfo.maxSubBSTSize) + 1;
        }
        return new Info(maxSubBSTHead,maxSubBSTSize, min, max);
    }

    public static class Info{
        private Node maxSubBSTHead;
        private int maxSubBSTSize;
        private int min;
        private int max;

        public Info(Node maxSubBSTHead, int maxSubBSTSize, int min, int max) {
            this.maxSubBSTHead = maxSubBSTHead;
            this.maxSubBSTSize = maxSubBSTSize;
            this.min = min;
            this.max = max;
        }
    }
}
