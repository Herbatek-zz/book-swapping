package com.piotrek.bookswapping.exceptions;


public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super();
    }

    @Override
    public String getMessage() {
        return "404: Not found this user";
    }
}
