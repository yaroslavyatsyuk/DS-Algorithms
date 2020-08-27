package com.yyatsiuk.divide_and_conquer;

import java.util.Arrays;
import java.util.Scanner;

public class Inversions {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        System.out.println(invCount(a));
    }

    private static long merge(int[] arr, int[] left, int[] right) {
        int i = 0;
        int j = 0;
        int count = 0;
        while (i < left.length || j < right.length) {
            if (i == left.length) {
                arr[i + j] = right[j++];
            } else if (j == right.length) {
                arr[i + j] = left[i++];
            } else if (left[i] <= right[j]) {
                arr[i + j] = left[i++];
            } else {
                arr[i + j] = right[j++];
                count += left.length - i;
            }
        }
        return count;
    }

    private static long invCount(int[] arr) {
        if (arr.length < 2)
            return 0;

        int m = (arr.length + 1) / 2;
        int[] left = Arrays.copyOfRange(arr, 0, m);
        int[] right = Arrays.copyOfRange(arr, m, arr.length);

        return invCount(left) + invCount(right) + merge(arr, left, right);
    }
}
