package com.piotrek.bookswapping;

import com.piotrek.bookswapping.dto.BookForExchangeDto;
import com.piotrek.bookswapping.dto.WantedBookDto;
import com.piotrek.bookswapping.entities.BookForExchange;
import com.piotrek.bookswapping.entities.WantedBook;

public class BookTestFactory {

    private static final String TITLE = "Pan lodowego Ogrodu";
    private static final String DESCRIPTION = "fajna książką";
    private static final Integer RELEASE_YEAR = 2012;

    public static WantedBook createWantedBook() {
        WantedBook wantedBook = new WantedBook(TITLE);
        wantedBook.setDescription(DESCRIPTION);
        wantedBook.setReleaseYear(RELEASE_YEAR);
        return wantedBook;
    }

    public static WantedBookDto createWantedBookDto() {
        return new WantedBookDto(TITLE, DESCRIPTION, RELEASE_YEAR);
    }

    public static BookForExchange createBookForExchange() {
        BookForExchange bookForExchange = new BookForExchange(TITLE);
        bookForExchange.setDescription(DESCRIPTION);
        bookForExchange.setReleaseYear(RELEASE_YEAR);
        return bookForExchange;
    }

    public static BookForExchangeDto createBookForExchangeDto() {
        return new BookForExchangeDto(TITLE, DESCRIPTION, RELEASE_YEAR);
    }
}
