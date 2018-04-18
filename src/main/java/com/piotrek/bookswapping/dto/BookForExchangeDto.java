package com.piotrek.bookswapping.dto;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BookForExchangeDto extends Book {

    public BookForExchangeDto(String title, String description, Integer releaseYear) {
        super(title, description, releaseYear);
    }
}

