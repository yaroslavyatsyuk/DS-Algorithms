package com.yyatsiuk.miscellaneous;

import java.util.Scanner;

/**
 * Given two strings, a and b, that may or may not be of the same length,
 * determine the minimum number of character deletions required to make a and b anagrams.
 * Any characters can be deleted from either of the strings.
 */
public class MakingAnagrams {

    // O(N + M) solution
    static int makeAnagram(String a, String b) {
        int deletion = 0;
        char[] chars1 = a.toCharArray();
        char[] chars2 = b.toCharArray();

        char[] charsCount1 = new char[255];
        char[] charsCount2 = new char[255];

        for (char c : chars1) {
            charsCount1[c]++;
        }

        for (char c : chars2) {
            charsCount2[c]++;
        }

        for (int i = 0; i < charsCount1.length; i++) {
            deletion += Math.abs(charsCount1[i] - charsCount2[i]);
        }

        return deletion;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String a = scanner.nextLine();
        String b = scanner.nextLine();

        int res = makeAnagram(a, b);

        System.out.println(res);

        scanner.close();
    }
}
