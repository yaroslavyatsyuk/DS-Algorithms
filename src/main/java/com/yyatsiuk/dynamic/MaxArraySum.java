package com.yyatsiuk.dynamic;

import java.util.Scanner;

/**
 * Given an array of integers, find the subset of non-adjacent elements with the maximum sum.
 * Calculate the sum of that subset.
 */
public class MaxArraySum {

    // O(n) space complexity
    static int maxSubsetSum(int[] arr) {
        int n = arr.length;
        if (n == 0) return 0;
        if (n == 1) return arr[0];

        int[] dp = new int[n];
        dp[0] = Math.max(0, arr[0]);
        dp[1] = Math.max(dp[0], arr[1]);

        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], arr[i] + dp[i - 2]);
        }

        return dp[n - 1];
    }

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        int n = SCANNER.nextInt();
        int[] arr = new int[n];
        String[] arrItems = SCANNER.nextLine().split(" ");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        int res = maxSubsetSum(arr);

        System.out.println(res);
    }
}
