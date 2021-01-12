package com.basic.campus001.class05;

public class Code01_Manacher {

    public static int manacher(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = manacherString(s);
        int C = -1;
        int R = -1;
        int[] pArr = new int[str.length];
        int max = 0;
        for (int i = 0; i < str.length; i++) {
            pArr[i] = i < R ? Math.min(R - i, 2 * C - i) : 1;
            while (i + pArr[i] < str.length && i - pArr[i] > -1) {
                if (str[i + pArr[i]] == str[i - pArr[i]]) {
                    pArr[i]++;
                } else {
                    break;
                }
            }
            if (i + pArr[i] > R){
                R = i + pArr[i];
                C = i;
            }
            max = Math.max(max, pArr[i]);
        }
        return max - 1;
    }

    private static char[] manacherString(String s) {
        char[] chars = s.toCharArray();
        char[] res = new char[s.length() * 2 + 1];
        int index = 0;
        for (int i = 0; i < res.length; i++) {
            res[i] = ((i & 1) == 0) ? '#' : chars[index++];
        }
        return res;
    }

    public static void main(String[] args) {
        String s = "abbacd";
        System.out.println(manacher(s));
    }
}
