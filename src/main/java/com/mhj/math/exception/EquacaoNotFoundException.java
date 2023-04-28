package com.mhj.math.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EquacaoNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
	public EquacaoNotFoundException(String message) {
        super(message);
    }
    public EquacaoNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
