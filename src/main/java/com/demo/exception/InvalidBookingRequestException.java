package com.demo.exception;

public class InvalidBookingRequestException extends RuntimeException{
    public InvalidBookingRequestException(String s) {
        super(s);

    }
}