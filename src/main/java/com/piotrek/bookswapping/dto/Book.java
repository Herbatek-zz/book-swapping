package com.piotrek.bookswapping.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
class Book {

    @NotBlank
    @Size(min = 1, max = 200)
    private String title;

    //@NotBlank
    private String description;

    private Integer releaseYear;

    public Book(String title, String description, Integer releaseYear) {
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
    }
}
