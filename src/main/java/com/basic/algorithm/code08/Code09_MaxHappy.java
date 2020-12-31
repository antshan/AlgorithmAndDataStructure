package com.basic.algorithm.code08;

import java.util.ArrayList;
import java.util.List;

public class Code09_MaxHappy {

    public static class Employee{
        private int happy;
        private List<Employee> nexts;

        public Employee(int happy) {
            this.happy = happy;
            this.nexts = new ArrayList<>();
        }
    }

    public static int maxHappy(Employee employee){
        if (employee == null){
            return 0;
        }
        Info info = process(employee);
        return Math.max(info.yesMaxHappy, info.noMaxHappy);
    }

    private static Info process(Employee employee) {
        if (employee.nexts.isEmpty()){
            return new Info(employee.happy, 0);
        }
        int yesHappy = employee.happy;
        int noHappy = 0;
        for (Employee employ : employee.nexts){
            Info info = process(employ);
            yesHappy += info.noMaxHappy;
            noHappy += Math.max(info.yesMaxHappy, info.noMaxHappy);
        }
        return new Info(yesHappy, noHappy);
    }

    public static class Info{
        private int yesMaxHappy;
        private int noMaxHappy;

        public Info(int yesMaxHappy, int noMaxHappy) {
            this.yesMaxHappy = yesMaxHappy;
            this.noMaxHappy = noMaxHappy;
        }
    }
}
