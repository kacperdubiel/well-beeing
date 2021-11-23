package com.wellbeeing.wellbeeing.domain.exception;

public class InvalidImportDataException extends Exception {
    public InvalidImportDataException(String errorMessage){
        super(errorMessage);
    }
}
