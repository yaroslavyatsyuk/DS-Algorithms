package com.yyatsiuk.greedy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class LargestNumber {
    private static String largestNumber(List<String> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            return "";
        }

        return numbers.stream().sorted((a, b) -> (b + a).compareTo(a + b)).collect(Collectors.joining(""));
    }

    public static void main(String[] args) {
        System.out.println(Integer.parseInt("04") + 12);
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<String> a = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            a.add(scanner.next());
        }
        System.out.println(largestNumber(a));
    }
}
