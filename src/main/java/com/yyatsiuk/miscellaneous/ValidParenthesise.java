package com.yyatsiuk.miscellaneous;

import java.util.LinkedList;
import java.util.Queue;

public class ValidParenthesise {

    private static boolean isValidParenthesise(String val) {
        final String open = "(";
        final String close = ")";

        Queue<String> queue = new LinkedList<>();
        for (int i = 0; i < val.length(); i++) {
            String currentSymbol = val.substring(i, i + 1);
            if (currentSymbol.equals(open)) {
                queue.add(currentSymbol);

            }
            if (currentSymbol.equals(close)) {
                String fromQueue = queue.poll();
                if (fromQueue == null || !fromQueue.equals(open)) {
                    return false;
                }
            }
        }

        return queue.isEmpty();
    }
}
