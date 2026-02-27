package com.trading.project.exception;

public class UserNotFoundException extends ResourceNotFoundException {

    private static final String MESSAGE = "User not found";

    public UserNotFoundException() {
        super(MESSAGE);
    }

}
