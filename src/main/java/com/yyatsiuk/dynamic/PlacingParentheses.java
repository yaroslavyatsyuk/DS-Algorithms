package com.yyatsiuk.dynamic;

import java.util.Scanner;

/**
 * Maximum Value of an Arithmetic Expression
 * <p>
 * Find the maximum value of an arithmetic expression by specifying the order
 * of applying its arithmetic operations using additional parentheses.
 */
public class PlacingParentheses {
    private static long getMaximValue(String exp) {
        char[] op = new char[exp.length() / 2];
        int[] digit = new int[exp.length() / 2 + 1];
        int d = 0;
        int o = 0;

        for (int i = 0; i < exp.length(); i++) {
            if (i % 2 == 0) {
                digit[d] = Character.getNumericValue(exp.charAt(i));
                d++;
            } else {
                op[o] = exp.charAt(i);
                o++;
            }
        }

        int n = digit.length;
        long[][] m = new long[n][n];
        long[][] M = new long[n][n];
        for (int i = 0; i < n; i++) {
            m[i][i] = digit[i];
            M[i][i] = digit[i];
        }
        for (int s = 1; s < n; s++) {
            for (int i = 0; i < n - s; i++) {
                int j = i + s;
                long[] minMaxArr = minAndMax(i, j, op, m, M);
                m[i][j] = minMaxArr[0];
                M[i][j] = minMaxArr[1];
            }
        }
        return M[0][n - 1];
    }

    private static long[] minAndMax(int i, int j, char[] op, long[][] m, long[][] M) {
        long mMin = Long.MAX_VALUE;
        long mMax = Long.MIN_VALUE;

        for (int k = i; k < j; k++) {
            long a = eval(M[i][k], M[k + 1][j], op[k]);
            long b = eval(M[i][k], m[k + 1][j], op[k]);
            long c = eval(m[i][k], M[k + 1][j], op[k]);
            long d = eval(m[i][k], m[k + 1][j], op[k]);
            mMin = getMin(mMin, a, b, c, d);
            mMax = getMax(mMax, a, b, c, d);
        }

        long[] result = new long[2];
        result[0] = mMin;
        result[1] = mMax;
        return result;
    }

    private static long getMax(long mMax, long a, long b, long c, long d) {
        return Math.max(Math.max(Math.max(Math.max(mMax, a), b), c), d);
    }

    private static long getMin(long mMin, long a, long b, long c, long d) {
        return Math.min(Math.min(Math.min(Math.min(mMin, a), b), c), d);
    }

    private static long eval(long a, long b, char op) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            default:
                return 0;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String exp = scanner.next();
        System.out.println(getMaximValue(exp));

    }
}