package com.shortUrl.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "URL not found")
public class urlNotFoundException extends RuntimeException{
    public urlNotFoundException() {
    }

    public urlNotFoundException(String message) {
        super(message);
    }

    public urlNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public urlNotFoundException(Throwable cause) {
        super(cause);
    }

    public urlNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
