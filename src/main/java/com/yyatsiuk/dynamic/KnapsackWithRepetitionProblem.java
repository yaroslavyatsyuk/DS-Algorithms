package com.yyatsiuk.dynamic;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of classic dynamic programming algorithm Knapsack with repetition
 * Time complexity is O(W * v.length)
 * Memory O(W)
 */
public class KnapsackWithRepetitionProblem {

    public static void main(String[] args) {
        int W = 4;
        int[] w = {1, 2, 3, 4};
        int[] v = {1, 5, 6, 11};

        int knapsack = knapsack(W, w, v);
        System.out.println(knapsack);
    }

    private static int knapsack(int W, int[] w, int[] v) {
        List<Integer> values = new ArrayList<>();
        values.add(0, 0);

        for (int weight = 1; weight <= W; weight++) {
            values.add(weight, 0);
            for (int j = 0; j < w.length; j++) {
                if (w[j] <= weight) {
                    int val = values.get(weight - w[j]) + v[j];
                    if (val > values.get(weight)) {
                        values.add(weight, val);
                    }
                }
            }
        }

        return values.get(W);
    }

}
