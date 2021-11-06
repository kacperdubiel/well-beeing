package com.wellbeeing.wellbeeing.domain.exception;

public class ForbiddenException extends Exception {
    public ForbiddenException(String errorMessage){
        super(errorMessage);
    }
}
