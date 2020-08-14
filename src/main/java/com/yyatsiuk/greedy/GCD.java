package com.yyatsiuk.greedy;
public class GCD {

    public static void main(String[] args) {
        System.out.println(euclidAlgorithm(9232344231111111L, 9123121231111111L));
    }


    public static long euclidAlgorithm(long a, long b) {
        if(a % b == 0) {
            return b;
        } else {
            return euclidAlgorithm(b, a % b);
        }
    }

    public static long naiveGCD(long a, long b) {
        long gcd = 0;

        for (long i = 1; i <= Math.max(a, b); i++) {
            if ((a % i == 0) && (b % i == 0)) {
                gcd = i;
            }
        }

        return gcd;
    }
}
