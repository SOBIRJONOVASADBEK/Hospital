package com.demo.exception;

public class InternalServerException extends RuntimeException {
    public InternalServerException(String s) {
        super(s);
    }
}