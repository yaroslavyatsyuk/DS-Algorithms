package com.yyatsiuk.dynamic;

import java.util.Scanner;

/**
 * Implementation of classic dynamic programming algorithm Knapsack without repetition
 * Time complexity is O(W * n)
 * Memory O(W * n)
 */
public class KnapsackWithoutRepetition {

    static int optimalWeight(int W, int[] wt) {
        int n = wt.length;
        int[][] values = new int[n + 1][W + 1];
        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= W; w++) {
                if (i == 0 || w == 0)
                    values[i][w] = 0;
                else if (wt[i - 1] <= w)
                    values[i][w] = Math.max(wt[i - 1] + values[i - 1][w - wt[i - 1]], values[i - 1][w]);
                else
                    values[i][w] = values[i - 1][w];
            }
        }

        return values[n][W];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int W, n;
        W = scanner.nextInt();
        n = scanner.nextInt();
        int[] w = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = scanner.nextInt();
        }
        System.out.println(optimalWeight(W, w));
    }
}
