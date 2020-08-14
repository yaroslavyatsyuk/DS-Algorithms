package com.yyatsiuk.greedy;
import java.util.Scanner;

public class Change {
    private static final int ONE = 1;
    private static final int FIVE = 5;
    private static final int TEN = 10;

    private static int getChange(int m) {
        int counter = 0;

        while (m > 0) {
            if (m >= TEN) {
                m -= TEN;
            } else if (m >= FIVE) {
                m -= FIVE;
            } else {
                m -= ONE;
            }
            counter++;
        }
        return counter;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        System.out.println(getChange(m));

    }
}

