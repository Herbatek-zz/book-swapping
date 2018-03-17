package com.piotrek.bookswapping.exceptions;

import lombok.Getter;

public class BookNotFoundException extends RuntimeException {

    @Getter
    private final String MESSAGE = "404: Not found this book";

    public BookNotFoundException() {
        super();
    }
}
