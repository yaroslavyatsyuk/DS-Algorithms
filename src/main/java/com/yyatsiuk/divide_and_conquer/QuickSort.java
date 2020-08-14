package com.yyatsiuk.divide_and_conquer;

import java.io.IOException;

public class QuickSort {

    private static long counter = 0;

    public static void main(String[] args) throws IOException {

    }

    private static void quickSort(int[] arr, int lo, int hi) {
        if (lo >= hi) return;

        counter += (hi - lo);
        int pivot = getPivot(lo, hi, arr);
        int tmp = arr[lo];
        arr[lo] = arr[pivot];
        arr[pivot] = tmp;
        int j = partition(arr, lo, hi);

        quickSort(arr, lo, j - 1);
        quickSort(arr, j + 1, hi);

    }

    private static int getPivot(int lo, int hi, int[] arr) {
        int mid = lo + (hi - lo) / 2;
        int pivot;

        if ((arr[lo] >= arr[mid] && arr[lo] <= arr[hi]) || (arr[lo] <= arr[mid] && arr[lo] >= arr[hi])) pivot = lo;
        else if ((arr[mid] >= arr[lo] && arr[mid] <= arr[hi]) || (arr[mid] <= arr[lo] && arr[mid] >= arr[hi]))
            pivot = mid;
        else pivot = hi;

        return pivot;
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[low];
        int i = low + 1;
        for (int j = low + 1; j <= high; j++) {
            if (arr[j] <= pivot) {
                swap(arr, i, j);
                i++;
            }
        }

        swap(arr, i - 1, low);
        return i - 1;
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}

