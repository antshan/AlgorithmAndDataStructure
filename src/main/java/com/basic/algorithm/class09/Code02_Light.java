package com.basic.algorithm.class09;

import java.util.HashSet;

public class Code02_Light {

    public static int minLight1(String road) {
        if (road == null || road.length() == 0) {
            return 0;
        }
        return process(road.toCharArray(), 0, new HashSet<>());
    }

    private static int process(char[] toCharArray, int index, HashSet<Integer> lights) {
        if (index == toCharArray.length) {
            for (int i = 0; i < toCharArray.length; i++) {
                if (toCharArray[i] == '.') {
                    if (!lights.contains(i - 1) && !lights.contains(i) && !lights.contains(i + 1)) {
                        return Integer.MAX_VALUE;
                    }
                }
            }
            return lights.size();
        } else {
            int no = process(toCharArray, index + 1, lights);
            int yes = Integer.MAX_VALUE;
            if (toCharArray[index] == '.') {
                lights.add(index);
                yes = process(toCharArray, index + 1, lights);
                lights.remove(index);
            }
            return Math.min(yes, no);
        }
    }

    public static int minLight2(String road) {
        if (road == null || road.length() == 0) {
            return 0;
        }
        char[] chars = road.toCharArray();
        int index = 0;
        int lights = 0;
        while (index < chars.length){
            if (chars[index] == 'X') {
                index++;
            }else {
                lights++;
                if (index + 1 == chars.length){
                    break;
                }else if (chars[index + 1] == '.'){
                    index = index + 3;
                }else{
                    index = index + 2;
                }
            }
        }
        return lights;
    }

    public static void main(String[] args) {
        String str = "XX..XXXXX.X...XXXXX.X..XXX...";
        System.out.println(minLight1(str));
        System.out.println(minLight2(str));
    }
}
