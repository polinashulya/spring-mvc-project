package com.example.exception;

public class ControllerCustomException extends RuntimeException {


    public ControllerCustomException() {
        super();
    }

    public ControllerCustomException(String message) {
        super(message);
    }

    public ControllerCustomException(String message, Throwable cause) {
        super(message, cause);
    }

    public ControllerCustomException(Throwable cause) {
        super(cause);
    }
}
