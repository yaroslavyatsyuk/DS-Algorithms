package com.yyatsiuk.miscellaneous;

import java.util.Scanner;

public class RecursiveDigitSum {

    static int superDigit(String n, int k) {
        String basicStringSum = superDigitHelper(n);
        long fullStringSum = Long.parseLong(basicStringSum) * k;

        String result = String.valueOf(fullStringSum);
        while (result.length() > 1) {
            result = superDigitHelper(result);
        }

        return Integer.parseInt(result);
    }


    private static String superDigitHelper(String d) {
        if (d.length() == 1) {
            return d;
        }

        int mid = d.length() / 2;

        String sum1 = "";
        String sum2 = "";
        sum1 += superDigitHelper(d.substring(0, mid));
        sum2 += superDigitHelper(d.substring(mid));

        return String.valueOf(Long.parseLong(sum1) + Long.parseLong(sum2));
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String[] nk = scanner.nextLine().split(" ");

        String n = nk[0];

        int k = Integer.parseInt(nk[1]);

        int result = superDigit(n, k);

        System.out.println(result);
        scanner.close();
    }
}
