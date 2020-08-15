package com.yyatsiuk.basic;

import java.util.Arrays;

public class SelectionSort {

    public static void main(String[] args) {
        int[] ints = {5, 1, 8, 11, 3, 112, 1, 2, 3};
        selectionSort(ints);
        System.out.println(Arrays.toString(ints));
    }

    private static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] >= arr[minIndex]) {
                    minIndex = j;
                }
            }
            swap(arr, i, minIndex);
        }
    }

    private static void swap(int[] arr, int index1, int index2) {
        int tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
    }
}
