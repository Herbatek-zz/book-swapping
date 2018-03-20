package com.piotrek.bookswapping.services;

import com.piotrek.bookswapping.entities.BookForExchange;
import com.piotrek.bookswapping.exceptions.BookNotFoundException;
import com.piotrek.bookswapping.respositories.BookForExchangeRepository;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Optional;

@Service
public class BookForExchangeService {

    private BookForExchangeRepository bookForExchangeRepository;

    public BookForExchangeService(BookForExchangeRepository bookForExchangeRepository) {
        this.bookForExchangeRepository = bookForExchangeRepository;
    }

    public BookForExchange create(@Valid BookForExchange bookForExchange) {
        return bookForExchangeRepository.save(bookForExchange);
    }

    public Iterable<BookForExchange> findAll() {
        return bookForExchangeRepository.findAll();
    }

    public BookForExchange findById(Long id) {
        return bookForExchangeRepository.findById(id).orElseThrow(BookNotFoundException::new);
    }

    public BookForExchange update(Long id, @Valid BookForExchange updateForBook) {
        BookForExchange readBook = bookForExchangeRepository.findById(id)
                .orElseThrow(BookNotFoundException::new);
        readBook.setTitle(updateForBook.getTitle());
        readBook.setDescription(updateForBook.getDescription());
        readBook.setReleaseYear(updateForBook.getReleaseYear());
        return create(readBook);
    }

    public void delete(Long id) {
        BookForExchange book = bookForExchangeRepository.findById(id)
                .orElseThrow(BookNotFoundException::new);
        bookForExchangeRepository.delete(book);
    }

}
