package com.yyatsiuk.greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class DifferentSummands {
    private static List<Integer> optimalSummands(int n) {
        if (n - 1 <= 1) {
            return Collections.singletonList(n);
        }

        List<Integer> summands = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            n -= i;
            if (n <= i) {
                summands.add(n + i);
                return summands;
            }
            summands.add(i);
        }
        return summands;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> summands = optimalSummands(n);
        System.out.println(summands.size());
        for (Integer summand : summands) {
            System.out.print(summand + " ");
        }
    }
}
