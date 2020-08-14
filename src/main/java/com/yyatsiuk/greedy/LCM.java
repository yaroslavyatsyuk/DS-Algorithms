package com.yyatsiuk.greedy;

import java.util.Scanner;

public class LCM {
    private static long lsm(long a, long b) {
        return (a * b) / euclidAlgorithm(a, b);
    }

    private static long euclidAlgorithm(long a, long b) {
        if(a % b == 0) {
            return b;
        } else {
            return euclidAlgorithm(b, a % b);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();

        System.out.println(lsm(a, b));
    }
}
