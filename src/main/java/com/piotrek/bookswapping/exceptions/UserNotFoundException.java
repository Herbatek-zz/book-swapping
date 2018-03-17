package com.piotrek.bookswapping.exceptions;


import lombok.Getter;

public class UserNotFoundException extends RuntimeException {

    @Getter private final String MESSAGE = "404: Not found this user";

    public UserNotFoundException() {
        super();
    }

}
