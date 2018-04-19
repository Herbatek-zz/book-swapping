package com.piotrek.bookswapping.services;

import com.piotrek.bookswapping.dto.WantedBookDto;
import com.piotrek.bookswapping.entities.WantedBook;
import com.piotrek.bookswapping.exceptions.BookNotFoundException;
import com.piotrek.bookswapping.repositories.WantedBookRepository;
import com.piotrek.bookswapping.services.converters.WantedBookConverter;
import org.modelmapper.internal.util.Lists;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WantedBookService {

    private WantedBookRepository wantedBookRepository;
    private WantedBookConverter converter;

    public WantedBookService(WantedBookRepository wantedBookRepository, WantedBookConverter converter) {
        this.wantedBookRepository = wantedBookRepository;
        this.converter = converter;
    }


    public Iterable<WantedBookDto> findAll() {
        List<WantedBook> wantedBooks = Lists.from(wantedBookRepository.findAll().iterator());
        return wantedBooks.stream()
                .map(converter::convertToDto)
                .collect(Collectors.toList());
    }

    public WantedBookDto findById(Long id) {
        WantedBook wantedBook = wantedBookRepository.findById(id).orElseThrow(BookNotFoundException::new);
        return converter.convertToDto(wantedBook);
    }

    WantedBookDto create(@Valid WantedBook wantedBook) {
        WantedBook book = wantedBookRepository.save(wantedBook);
        return converter.convertToDto(book);
    }

    public void update(Long id, WantedBook bookUpdate) {
        WantedBook wantedBookToUpdate = wantedBookRepository.findById(id).orElseThrow(BookNotFoundException::new);
        wantedBookToUpdate.setTitle(bookUpdate.getTitle());
        wantedBookToUpdate.setDescription(bookUpdate.getDescription());
        wantedBookToUpdate.setReleaseYear(bookUpdate.getReleaseYear());
        wantedBookRepository.save(wantedBookToUpdate);
    }

    public void delete(Long id) {
        WantedBook wantedBook = wantedBookRepository.findById(id).orElseThrow(BookNotFoundException::new);
        wantedBookRepository.delete(wantedBook);
    }

    Iterable<WantedBookDto> findWantedBookByUserId(Long userId) {
        List<WantedBook> wantedBooks = Lists.from(wantedBookRepository.findAllByUserId(userId).iterator());
        return wantedBooks.stream()
                .map(converter::convertToDto)
                .collect(Collectors.toList());
    }

}
