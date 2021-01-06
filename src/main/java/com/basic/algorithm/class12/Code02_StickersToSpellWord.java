package com.basic.algorithm.class12;

import java.util.HashMap;
import java.util.Map;

public class Code02_StickersToSpellWord {

    public static int minSticker1(String[] stickers, String target) {
        int[][] map = new int[stickers.length][26];
        for (int i = 0; i < stickers.length; i++) {
            for (int j = 0; j < stickers[i].length(); j++) {
                map[i][stickers[i].charAt(j) - 'a']++;
            }
        }
        Map<String, Integer> dp = new HashMap<>();
        dp.put("", 0);
        return process(dp, map, target);
    }

    private static int process(Map<String, Integer> dp, int[][] map, String target) {
        if (dp.containsKey(target)) {
            return dp.get(target);
        }
        int ans = Integer.MAX_VALUE;
        int[] tmap = new int[26];
        for (int i = 0; i < target.length(); i++) {
            tmap[target.charAt(i) - 'a']++;
        }

        for (int i = 0; i < map.length; i++) {
            if (map[i][target.charAt(0) - 'a'] == 0) {
                continue;
            }
            StringBuilder stringBuilder = new StringBuilder();
            for (int j = 0; j < 26; j++) {
                if (tmap[j] > 0) {
                    for (int k = 0; k < Math.max(0, tmap[j] - map[i][j]); k++) {
                        stringBuilder.append((char) ('a' + j));
                    }
                }
            }
            int tmp = process(dp, map, stringBuilder.toString());
            if (tmp != -1) {
                ans = Math.min(ans, tmp + 1);
            }
        }
        dp.put(target, ans == Integer.MAX_VALUE ? -1 : ans);
        return dp.get(target);
    }
}
