package com.basic.campus001.class03;

public class Code01_KMP {

    public static int getIndexOf(String s, String m) {
        if (s == null || m == null || s.length() < m.length() || m.length() < 1) {
            return -1;
        }
        char[] str = s.toCharArray();
        char[] match = m.toCharArray();
        int[] next = getNextArray(match);
        int x = 0;
        int y = 0;
        while (x < str.length && y < match.length) {
            if (str[x] == match[y]) {
                x++;
                y++;
            } else if (next[y] == -1) {
                x++;
            } else {
                y = next[y];
            }
        }
        return y == match.length ? x - y : -1;
    }

    private static int[] getNextArray(char[] match) {
        if (match.length == 1) {
            return new int[]{-1};
        }
        int[] next = new int[match.length];
        next[0] = -1;
        next[1] = 0;
        int index = 2;
        int cn = 0;
        while (index < match.length) {
            if (match[index - 1] == match[cn]) {
                next[index++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[index++] = 0;
            }
        }
        return next;
    }

    public static void main(String[] args) {
        String s = "adcfvbghnjyug";
        String m = "bghn";
        System.out.println(getIndexOf(s, m));
    }
}
