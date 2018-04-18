package com.piotrek.bookswapping.exceptions;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException() {
        super();
    }

    @Override
    public String getMessage() {
        return "404: Not found this book";
    }
}
