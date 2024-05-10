package com.demo.exception;

public class PhotoRetrievalExсeption extends RuntimeException {
    public PhotoRetrievalExсeption(String error_retrieving_photo) {
        super(error_retrieving_photo);

    }
}