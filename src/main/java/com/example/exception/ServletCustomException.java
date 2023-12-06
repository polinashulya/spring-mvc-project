package com.example.exception;

public class ServletCustomException extends RuntimeException {


    public ServletCustomException() {
        super();
    }

    public ServletCustomException(String message) {
        super(message);
    }

    public ServletCustomException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServletCustomException(Throwable cause) {
        super(cause);
    }
}
