package com.piotrek.bookswapping.services;

import com.piotrek.bookswapping.dto.BookForExchangeDto;
import com.piotrek.bookswapping.entities.BookForExchange;
import com.piotrek.bookswapping.exceptions.BookNotFoundException;
import com.piotrek.bookswapping.repositories.BookForExchangeRepository;
import com.piotrek.bookswapping.services.converters.BookForExchangeConverter;
import org.modelmapper.internal.util.Lists;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookForExchangeService {

    private BookForExchangeRepository bookForExchangeRepository;
    private BookForExchangeConverter converter;

    public BookForExchangeService(BookForExchangeRepository bookForExchangeRepository, BookForExchangeConverter converter) {
        this.bookForExchangeRepository = bookForExchangeRepository;
        this.converter = converter;
    }


    public Iterable<BookForExchangeDto> findAll() {
        List<BookForExchange> books = Lists.from(bookForExchangeRepository.findAll().iterator());
        return books.stream()
                .map(converter::convertToDto)
                .collect(Collectors.toList());
    }

    public BookForExchangeDto findById(Long id) {
        BookForExchange book = bookForExchangeRepository.findById(id).orElseThrow(BookNotFoundException::new);
        return converter.convertToDto(book);
    }

    BookForExchangeDto create(@Valid BookForExchange bookForExchange) {
        BookForExchange book = bookForExchangeRepository.save(bookForExchange);
        return converter.convertToDto(book);
    }

    public void update(Long id, @Valid BookForExchange bookUpdate) {
        BookForExchange readBook = bookForExchangeRepository.findById(id).orElseThrow(BookNotFoundException::new);
        readBook.setTitle(bookUpdate.getTitle());
        readBook.setDescription(bookUpdate.getDescription());
        readBook.setReleaseYear(bookUpdate.getReleaseYear());
        bookForExchangeRepository.save(readBook);
    }

    public void delete(Long id) {
        BookForExchange book = bookForExchangeRepository.findById(id).orElseThrow(BookNotFoundException::new);
        bookForExchangeRepository.delete(book);
    }

    Iterable<BookForExchangeDto> findBooksForExchangeByUserId(Long userId) {
        List<BookForExchange> booksForExchange = Lists.from(bookForExchangeRepository.findAllByUserId(userId).iterator());
        return booksForExchange.stream()
                .map(converter::convertToDto)
                .collect(Collectors.toList());
    }

}
