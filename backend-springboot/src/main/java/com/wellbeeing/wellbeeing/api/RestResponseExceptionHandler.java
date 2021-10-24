package com.wellbeeing.wellbeeing.api;

import com.wellbeeing.wellbeeing.domain.exception.ConflictException;
import com.wellbeeing.wellbeeing.domain.exception.ForbiddenException;
import com.wellbeeing.wellbeeing.domain.exception.IllegalArgumentException;
import com.wellbeeing.wellbeeing.domain.exception.NotFoundException;
import com.wellbeeing.wellbeeing.domain.message.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorMessage> handleException(Exception e){
        return new ResponseEntity<>(new ErrorMessage("Server error: " + e.getMessage(),
                "500"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<ErrorMessage> handleException(NotFoundException e){
        return new ResponseEntity<>(new ErrorMessage("Not found: " + e.getMessage(),
                "404"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ConflictException.class})
    public ResponseEntity<ErrorMessage> handleException(ConflictException e){
        return new ResponseEntity<>(new ErrorMessage("Conflict: " + e.getMessage(),
                "409"), HttpStatus.CONFLICT);
    }

    @ExceptionHandler({ForbiddenException.class})
    public ResponseEntity<ErrorMessage> handleException(ForbiddenException e){
        return new ResponseEntity<>(new ErrorMessage("Forbidden: " + e.getMessage(),
                "403"), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<ErrorMessage> handleException(IllegalArgumentException e){
        return new ResponseEntity<>(new ErrorMessage("Illegal argument: " + e.getMessage(),
                "400"), HttpStatus.BAD_REQUEST);
    }
}