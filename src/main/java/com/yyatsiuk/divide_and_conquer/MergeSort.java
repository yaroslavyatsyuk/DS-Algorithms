package com.yyatsiuk.divide_and_conquer;

import java.util.Arrays;

public class MergeSort {

    public static void main(String[] args) {
        int[] ints = {5, 1, 8, 11, 3, 112, 1, 2, 3};
        mergeSort(ints);
        System.out.println(Arrays.toString(ints));
    }

    private static int[] mergeSort(int[] arr) {
        if (arr.length <= 1) {
            return arr;
        }

        int[] first = new int[arr.length / 2];
        int[] second = new int[arr.length - first.length];
        System.arraycopy(arr, 0, first, 0, first.length);
        System.arraycopy(arr, first.length, second, 0, second.length);

        int[] arr1 = mergeSort(first);
        int[] arr2 = mergeSort(second);

        return merge(arr1, arr2, arr);
    }

    private static int[] merge(int[] arr1, int[] arr2, int[] result) {
        int i = 0;
        int j = 0;
        int index = 0;

        while (true) {
            if (i == arr1.length) {
                System.arraycopy(arr2, j, result, i + j, arr2.length - j);
                return result;
            } else if (j == arr2.length) {
                System.arraycopy(arr1, i, result, i + j, arr1.length - i);
                return result;
            }

            if (arr1[i] < arr2[j]) {
                result[index++] = arr1[i++];
            } else {
                result[index++] = arr2[j++];
            }
        }
    }
}
