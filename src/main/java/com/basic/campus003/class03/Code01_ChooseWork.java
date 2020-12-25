package com.basic.campus003.class03;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

public class Code01_ChooseWork {

    public static class Job {
        private int hard;
        private int money;

        public Job(int hard, int money) {
            this.hard = hard;
            this.money = money;
        }
    }

    public static class JobComparator implements Comparator<Job> {
        @Override
        public int compare(Job o1, Job o2) {
            return o1.hard != o2.hard ? o1.hard - o2.hard : o2.money - o1.money;
        }
    }

    public static int[] getMaxMoney(Job[] jobs, int[] abilities) {
        Arrays.sort(jobs, new JobComparator());
        TreeMap<Integer,Integer> treeMap = new TreeMap<>();
        treeMap.put(jobs[0].hard,jobs[0].money);
        Job pre = jobs[0];
        for (int i=1;i<jobs.length;i++){
            if (jobs[i].hard != pre.hard && jobs[i].money > pre.money){
                pre = jobs[i];
                treeMap.put(jobs[i].hard,jobs[i].money);
            }
        }
        int[] ans = new int[abilities.length];
        for (int j=0;j<ans.length;j++){
            Integer key = treeMap.floorKey(abilities[j]);
            ans[j] = key==null?0:treeMap.get(key);
        }
        return ans;
    }
}
