package com.basic.campus003.class06;

public class Code02_ExpressionNumber {

    public static int num1(String expression, boolean desired) {
        if (expression == null || expression.length() == 0) {
            return 0;
        }
        char[] chars = expression.toCharArray();
        if (!isValid(chars)) {
            return 0;
        }
        return f(chars, desired, 0, chars.length - 1);
    }

    private static int f(char[] exp, boolean desired, int L, int R) {
        if (L == R) {
            if (desired) {
                return exp[L] == '1' ? 1 : 0;
            } else {
                return exp[L] == '0' ? 1 : 0;
            }
        }
        int res = 0;
        for (int i = L + 1; i < R; i += 2) {
            switch (exp[i]) {
                case '&':
                    if (desired) {
                        res += f(exp, true, L, i - 1) * f(exp, true, i + 1, R);
                    } else {
                        res += f(exp, true, L, i - 1) * f(exp, false, i + 1, R);
                        res += f(exp, false, L, i - 1) * f(exp, true, i + 1, R);
                        res += f(exp, false, L, i - 1) * f(exp, false, i + 1, R);
                    }
                    break;
                case '|':
                    if (desired) {
                        res += f(exp, true, L, i - 1) * f(exp, true, i + 1, R);
                        res += f(exp, true, L, i - 1) * f(exp, false, i + 1, R);
                        res += f(exp, false, L, i - 1) * f(exp, true, i + 1, R);
                    } else {
                        res += f(exp, false, L, i - 1) * f(exp, false, i + 1, R);
                    }
                    break;
                case '^':
                    if (desired) {
                        res += f(exp, true, L, i - 1) * f(exp, false, i + 1, R);
                        res += f(exp, false, L, i - 1) * f(exp, true, i + 1, R);
                    } else {
                        res += f(exp, true, L, i - 1) * f(exp, true, i + 1, R);
                        res += f(exp, false, L, i - 1) * f(exp, false, i + 1, R);
                    }
                    break;
            }
        }
        return res;
    }

    private static boolean isValid(char[] chars) {
        if ((chars.length & 1) == 0) {
            return false;
        }
        for (int i = 0; i < chars.length; i = i + 2) {
            if (chars[i] != '1' && chars[i] != '0') {
                return false;
            }
        }
        for (int i = 1; i < chars.length; i = i + 2) {
            if (chars[i] != '&' && chars[i] != '|' && chars[i] != '^') {
                return false;
            }
        }
        return true;
    }

    public static int num2(String expression, boolean desired) {
        if (expression == null || expression.length() == 0) {
            return 0;
        }
        char[] chars = expression.toCharArray();
        if (!isValid(chars)) {
            return 0;
        }
        int N = chars.length;
        int[][] tMap = new int[N][N];
        int[][] fMap = new int[N][N];
        for (int i = 0; i < N; i += 2) {
            tMap[i][i] = chars[i] == '1' ? 1 : 0;
            fMap[i][i] = chars[i] == '0' ? 1 : 0;
        }
        for (int L = N - 3; L >= 0; L -= 2) {
            for (int R = L + 2; R < N; R += 2) {
                // 上述两层循环确定L->R区域
                // 下面的循环确定每一个L->R的满足期望的最大个数
                for (int i = L + 1; i < R; i += 2) {
                    switch (chars[i]) {
                        case '&':
                            tMap[L][R] += tMap[L][i-1] * tMap[i+1][R];
                            fMap[L][R] += fMap[L][i-1] * tMap[i+1][R];
                            fMap[L][R] += tMap[L][i-1] * fMap[i+1][R];
                            fMap[L][R] += fMap[L][i-1] * fMap[i+1][R];
                            break;
                        case '|':
                            tMap[L][R] += tMap[L][i-1] * tMap[i+1][R];
                            tMap[L][R] += tMap[L][i-1] * fMap[i+1][R];
                            tMap[L][R] += fMap[L][i-1] * tMap[i+1][R];
                            fMap[L][R] += fMap[L][i-1] * fMap[i+1][R];
                            break;
                        case '^':
                            tMap[L][R] += fMap[L][i-1] * tMap[i+1][R];
                            tMap[L][R] += tMap[L][i-1] * fMap[i+1][R];
                            fMap[L][R] += tMap[L][i-1] * tMap[i+1][R];
                            fMap[L][R] += fMap[L][i-1] * fMap[i+1][R];
                            break;
                    }
                }
            }
        }
        return desired ? tMap[0][N - 1] : fMap[0][N - 1];
    }

    public static void main(String[] args) {
        String express = "1^0&0|1&1^0&0^1|0|1&1";
        boolean desired = true;
        System.out.println(num1(express, desired));
        System.out.println(num2(express, desired));
    }
}
