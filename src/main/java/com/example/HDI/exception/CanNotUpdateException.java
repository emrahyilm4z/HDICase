package com.example.HDI.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CanNotUpdateException extends RuntimeException {

    public CanNotUpdateException() {
        super(Message.CANNOTUPDATE);
    }
}
