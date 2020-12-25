package com.basic.campus003.class04;

import java.util.Arrays;
import java.util.Comparator;

public class Code05_EnvelopesProblem {

    public static class Envelop {
        private int l;
        private int h;

        public Envelop(int l, int h) {
            this.l = l;
            this.h = h;
        }
    }

    public static class EnvelopComparator implements Comparator<Envelop> {
        @Override
        public int compare(Envelop o1, Envelop o2) {
            return o1.l != o2.l ? o1.l - o2.l : o2.h - o2.h;
        }
    }

    public static Envelop[] generateSortedEnvelop(int[][] matrix){
        Envelop[] envelops = new Envelop[matrix.length];
        for (int i=0;i<matrix.length;i++){
            envelops[i] = new Envelop(matrix[i][0],matrix[i][1]);
        }
        Arrays.sort(envelops,new EnvelopComparator());
        return envelops;
    }

    public static int maxEnvelopes(int[][] matrix){
        Envelop[] envelops = generateSortedEnvelop(matrix);
        int[] ends = new int[envelops.length];
        int l =0;
        int r =0;
        int right =0;
        ends[0] = envelops[0].h;
        for (int i =1;i<envelops.length;i++){
            l=0;
            r=right;
            while (l<=r){
                int mid = l + ((r-l)>>1);
                if (envelops[i].h > ends[mid]){
                    l = mid+1;
                }else {
                    r= mid-1;
                }
            }
            right = Math.max(right,l);
            ends[l] = envelops[i].h;
        }
        return right+1;
    }

    public static void main(String[] args) {
        int[][] test = { { 3, 4 }, { 2, 3 }, { 4, 5 }, { 1, 3 }, { 2, 2 }, { 3, 6 }, { 1, 2 }, { 3, 2 }, { 2, 4 } };
        System.out.println(maxEnvelopes(test));
    }
}
