package com.example.HDI.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotEnoughQuotaException extends RuntimeException {
    public NotEnoughQuotaException() {
        super(Message.NOTENOUGHQUOTA);
    }
}
