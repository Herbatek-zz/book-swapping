package com.piotrek.bookswapping.services;

import com.piotrek.bookswapping.entities.BookForExchange;
import com.piotrek.bookswapping.exceptions.BookNotFoundException;
import com.piotrek.bookswapping.respositories.BookForExchangeRepository;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class BookForExchangeService {

    private BookForExchangeRepository bookForExchangeRepository;

    public BookForExchangeService(BookForExchangeRepository bookForExchangeRepository) {
        this.bookForExchangeRepository = bookForExchangeRepository;
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
        return bookForExchangeRepository.save(readBook);
    }

    public void delete(Long id) {
        BookForExchange book = bookForExchangeRepository.findById(id)
                .orElseThrow(BookNotFoundException::new);
        bookForExchangeRepository.delete(book);
    }

}
