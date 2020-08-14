package com.yyatsiuk.greedy;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Scanner;

public class FractionalKnapsack {

    private static class Item {
        private final int value;
        private final int weight;

        public Item(int value, int weight) {
            this.value = value;
            this.weight = weight;
        }

        public int getValue() {
            return value;
        }

        public int getWeight() {
            return weight;
        }
    }

    private static double getOptimalValue(int capacity, int[] values, int[] weights) {
        double value = 0;

        Item[] items = new Item[values.length];
        for (int i = 0; i < values.length; i++) {
            items[i] = new Item(values[i], weights[i]);
        }

        Arrays.sort(items, (item1, item2) -> {
            long dif1 = (long) item1.getValue() * item2.getWeight();
            long dif2 = (long) item2.getValue() * item1.getWeight();

            return -Long.compare(dif1, dif2);
        });

        int i = 0;
        while (capacity > 0 && i < items.length) {
            if (items[i].getWeight() <= capacity) {
                value += items[i].getValue();
                capacity -= items[i].getWeight();
            } else if (items[i].getWeight() > capacity) {
                value += (double) capacity * items[i].getValue() / items[i].getWeight();
                break;
            }

            i++;
        }

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(4, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int capacity = scanner.nextInt();
        int[] values = new int[n];
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
            weights[i] = scanner.nextInt();
        }
        System.out.println(getOptimalValue(capacity, values, weights));
    }
}
