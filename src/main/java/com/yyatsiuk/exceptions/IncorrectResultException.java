package com.yyatsiuk.exceptions;

public class IncorrectResultException extends RuntimeException {
    public IncorrectResultException() {
    }

    public IncorrectResultException(String message) {
        super(message);
    }
}
