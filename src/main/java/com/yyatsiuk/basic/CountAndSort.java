package com.yyatsiuk.basic;

import com.yyatsiuk.exceptions.IncorrectResultException;

import java.util.Arrays;

/**
 * CountSort Non-Comparison Based Sorting Algorithms O(n)
 */
public class CountAndSort {

    public static void main(String[] args) {
        int[] ints = {5, 1, 8, 11, 3, 21, 41, 101, 1, 2, 3};
        countSort(ints);
        System.out.println(Arrays.toString(ints));
        boolean isEqual = Arrays.equals(new int[]{1, 1, 2, 3, 3, 5, 8, 11, 21, 41, 101}, ints);
        if (!isEqual) {
            throw new IncorrectResultException("Incorrect result");
        }
    }

    private static void countSort(int[] arr) {
        int max = arr[0];
        for (int i : arr) {
            if (i >= max) {
                max = i;
            }
        }

        int[] count = new int[max + 1];
        for (int value : arr) {
            count[value]++;
        }

        int iterator = 0;
        for (int i = 0; i < count.length; i++) {
            int j = count[i];
            while (j > 0) {
                arr[iterator++] = i;
                j--;
            }
        }
    }
}

