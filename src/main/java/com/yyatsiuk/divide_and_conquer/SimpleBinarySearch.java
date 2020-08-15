package com.yyatsiuk.divide_and_conquer;

import com.yyatsiuk.exceptions.IncorrectResultException;

public class SimpleBinarySearch {

    public static void main(String[] args) {
        int[] ints = {1, 55, 61, 77, 122, 567};
        int r1 = binarySearch(ints, 77);
        int r2 = binarySearch(ints, 33);

        if (r1 != 3) {
            throw new IncorrectResultException(String.format("Expected 3, but actual: [%s]", r1));
        }
        if (r2 != 1) {
            throw new IncorrectResultException(String.format("Expected 1, but actual: [%s]", r2));
        }
    }

    private static int binarySearch(int[] arr, int key) {
        return bSearchHelper(arr, key, 0, arr.length - 1);
    }

    private static int bSearchHelper(int[] arr, int key, int l, int r) {
        if (r < l) {
            return l;
        }

        int mid = l + (r - l) / 2;

        if (key == arr[mid]) {
            return mid;
        } else if (key < arr[mid]) {
            return bSearchHelper(arr, key, l, mid - 1);
        } else {
            return bSearchHelper(arr, key, mid + 1, r);
        }
    }
}
