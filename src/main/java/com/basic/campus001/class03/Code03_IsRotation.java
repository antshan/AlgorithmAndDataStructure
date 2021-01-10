package com.basic.campus001.class03;

public class Code03_IsRotation {

    public static boolean isRotation(String s1,String s2){
        if (s1 == null || s2 == null || s1.length() != s2.length()){
            return false;
        }
        String s = s1 + s1;
        return Code01_KMP.getIndexOf(s, s2) != -1;
    }

    public static void main(String[] args) {
        String s1 = "abcdefg";
        String s2 = "cdefgab";
        System.out.println(isRotation(s1, s2));
    }
}
