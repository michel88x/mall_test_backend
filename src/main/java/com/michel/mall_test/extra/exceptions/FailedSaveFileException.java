package com.michel.mall_test.extra.exceptions;

public class FailedSaveFileException extends RuntimeException{
    public FailedSaveFileException(String message){
        super(message);
    }
}
