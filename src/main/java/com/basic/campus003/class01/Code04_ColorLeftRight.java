package com.basic.campus003.class01;

public class Code04_ColorLeftRight {

    public static int minPaint(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }
        int rightRNum = 0;
        int leftGNum = 0;
        int N = s.length();
        char[] chars = s.toCharArray();
        for (int i = 0; i < N; i++) {
            rightRNum += chars[i] == 'R' ? 1 : 0;
        }
        int ans = rightRNum;
        for (int i = 0; i < N; i++) {
            leftGNum += chars[i] == 'G' ? 1 : 0;
            rightRNum -= chars[i] == 'R' ? 1 : 0;
            ans = Math.min(ans, leftGNum + rightRNum);
        }
//        ans = Math.min(ans, leftGNum + (chars[N - 1] == 'G' ? 1 : 0));  个人觉得不用单独拎出来处理
        return ans;
    }

    public static void main(String[] args) {
        String test = "RGGRGGR";
        System.out.println(minPaint(test));
    }
}
