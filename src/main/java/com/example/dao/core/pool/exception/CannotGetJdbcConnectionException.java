package com.example.dao.core.pool.exception;

public class CannotGetJdbcConnectionException extends RuntimeException {

    public CannotGetJdbcConnectionException() {
        super();
    }

    public CannotGetJdbcConnectionException(String message) {
        super(message);
    }

    public CannotGetJdbcConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public CannotGetJdbcConnectionException(Throwable cause) {
        super(cause);
    }
}

