package com.seis739.AbsoluteUnit.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String alert) {
        super(alert);
    }
}
