package com.piotrek.bookswapping.dto;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class WantedBookDto extends Book {

    public WantedBookDto(String title, String description, Integer releaseYear) {
        super(title, description, releaseYear);
    }
}
