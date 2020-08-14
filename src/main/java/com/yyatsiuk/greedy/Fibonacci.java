package com.yyatsiuk.greedy;
import java.util.HashMap;
import java.util.Map;

public class Fibonacci {
    private static final Map<Integer, Integer> CACHE = new HashMap<>();

    public static void main(String[] args) {
        System.out.println(getFibonacciOptimized(2));
    }

    public static Integer getFibonacciOptimized(int n) {
        if (n <= 1) {
            return n;
        }
        if (CACHE.get(n) != null) {
            return CACHE.get(n);
        }

        Integer fibN = getFibonacciOptimized(n - 1) + (getFibonacciOptimized(n - 2));
        CACHE.putIfAbsent(n, fibN);
        return fibN;
    }

    public static int fibList(int n) {
        if (n < 2) {
            return n;
        }

        int[] fibs = new int[n + 1];
        fibs[0] = 0;
        fibs[1] = 1;

        for (int i = 2; i < fibs.length; i++) {
            fibs[i] = fibs[i - 1] + (fibs[i - 2]);
        }

        return fibs[n];
    }
}
