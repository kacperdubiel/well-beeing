package com.wellbeeing.wellbeeing.domain.exception;

public class NotFoundException extends Exception {
    public NotFoundException(String errorMessage){
        super(errorMessage);
    }
}
