package com.yyatsiuk.greedy;


import java.math.BigInteger;
import java.util.Scanner;

public class FibonacciLastDigit {
    public static long lastDigitOfTheSumOfFibonacciNumbers(BigInteger n) {
        BigInteger f0 = BigInteger.ZERO;
        BigInteger f1 = BigInteger.ONE;

        if (n.equals(BigInteger.ZERO))
            return 0;
        if (n.equals(BigInteger.ONE))
            return 1;
        else {
            long rem = n.mod(BigInteger.valueOf(60)).longValue();

            if (rem == 0) {
                return 0;
            }

            for (int i = 2; i < rem + 3; i++) {
                BigInteger f = (f0.add(f1)).mod(BigInteger.valueOf(60));
                f0 = f1;
                f1 = f;
            }

            return f1.subtract(BigInteger.ONE).longValue();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BigInteger n = scanner.nextBigInteger();
        System.out.println(lastDigitOfTheSumOfFibonacciNumbers(n) % 10);
    }

}
