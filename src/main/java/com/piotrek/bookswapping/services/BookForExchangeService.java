package com.piotrek.bookswapping.services;

import com.piotrek.bookswapping.dto.BookForExchangeDto;
import com.piotrek.bookswapping.entities.BookForExchange;
import com.piotrek.bookswapping.exceptions.BookNotFoundException;
import com.piotrek.bookswapping.repositories.BookForExchangeRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.util.Lists;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookForExchangeService {

    private BookForExchangeRepository bookForExchangeRepository;
    private ModelMapper modelMapper;

    public BookForExchangeService(BookForExchangeRepository bookForExchangeRepository, ModelMapper modelMapper) {
        this.bookForExchangeRepository = bookForExchangeRepository;
        this.modelMapper = modelMapper;
    }


    public Iterable<BookForExchangeDto> findAll() {
        List<BookForExchange> books = Lists.from(bookForExchangeRepository.findAll().iterator());
        return books.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public BookForExchangeDto findById(Long id) {
        BookForExchange book = bookForExchangeRepository.findById(id).orElseThrow(BookNotFoundException::new);
        return convertToDto(book);
    }

    public void update(Long id, @Valid BookForExchange bookUpdate) {
        BookForExchange readBook = bookForExchangeRepository.findById(id).orElseThrow(BookNotFoundException::new);
        readBook.setTitle(bookUpdate.getTitle());
        readBook.setDescription(bookUpdate.getDescription());
        readBook.setReleaseYear(bookUpdate.getReleaseYear());
    }

    public void delete(Long id) {
        BookForExchange book = bookForExchangeRepository.findById(id).orElseThrow(BookNotFoundException::new);
        bookForExchangeRepository.delete(book);
    }

    private BookForExchangeDto convertToDto(BookForExchange bookForExchange) {
        return modelMapper.map(bookForExchange, BookForExchangeDto.class);
    }

    private BookForExchange convertToEntity(BookForExchangeDto bookForExchangeDto) {
        return modelMapper.map(bookForExchangeDto, BookForExchange.class);
    }

}
