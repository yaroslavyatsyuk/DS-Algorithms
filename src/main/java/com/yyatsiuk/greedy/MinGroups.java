package com.yyatsiuk.greedy;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class MinGroups {

    public static void main(String[] args) {
        List<Children> childrenList = new ArrayList<>();
        for (int i = 0; i <= 1_000_000; i++) {
            childrenList.add(new Children(Math.random() * 20));
        }

        double[] doubles = childrenList.stream().mapToDouble(Children::getAge).toArray();
        List<Double> doubles2 = childrenList.stream().map(Children::getAge).collect(Collectors.toList());

        long start = System.currentTimeMillis();
        int minGroupsNumber1 = findMinGroupsNumber(doubles);
        System.out.println(minGroupsNumber1);
        System.out.println(System.currentTimeMillis() - start);

        start = System.currentTimeMillis();
        int minGroupsNumber = findMinGroupsNumber(doubles2);
        System.out.println(minGroupsNumber);
        System.out.println(System.currentTimeMillis() - start);
    }

    private static int findMinGroupsNumber(List<Double> children) {
        if (children == null || children.isEmpty()) {
            return 0;
        }

        int groupCount = 0;
        while (!children.isEmpty()) {
            Double min = children.parallelStream().min(Comparator.comparingDouble(Double::doubleValue)).get();
            children.removeIf(child -> child - min <= 1);
            groupCount++;
        }

        return groupCount;
    }

    private static int findMinGroupsNumber(double[] ages) {
        Arrays.sort(ages);

        int result = 0;
        int i = 0;
        int n = ages.length;
        while (i < n) {
            double r = ages[i] + 1;

            result++;
            i++;
            while (i < n && ages[i] <= r) {
                i++;
            }
        }

        return result;
    }

    @Data
    private static class Children {
        private final double age;

        public Children(double age) {
            this.age = age;
        }

        public double getAge() {
            return age;
        }
    }
}
