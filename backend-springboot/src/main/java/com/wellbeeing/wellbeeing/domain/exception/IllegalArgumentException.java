package com.wellbeeing.wellbeeing.domain.exception;

public class IllegalArgumentException extends Exception{
    public IllegalArgumentException(String errorMessage){
        super(errorMessage);
    }
}
