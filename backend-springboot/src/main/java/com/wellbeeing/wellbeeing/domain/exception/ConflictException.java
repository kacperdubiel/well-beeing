package com.wellbeeing.wellbeeing.domain.exception;

public class ConflictException extends Exception {
    public ConflictException(String errorMessage){
        super(errorMessage);
    }
}
