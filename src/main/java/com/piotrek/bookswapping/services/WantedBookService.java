package com.piotrek.bookswapping.services;

import com.piotrek.bookswapping.entities.WantedBook;
import com.piotrek.bookswapping.exceptions.BookNotFoundException;
import com.piotrek.bookswapping.respositories.WantedBookRepository;
import org.springframework.stereotype.Service;

@Service
public class WantedBookService {

    private WantedBookRepository wantedBookRepository;

    public WantedBookService(WantedBookRepository wantedBookRepository) {
        this.wantedBookRepository = wantedBookRepository;
    }


    public WantedBook create(WantedBook wantedBook) {
        return wantedBookRepository.save(wantedBook);
    }

    public Iterable<WantedBook> findAll() {
        return wantedBookRepository.findAll();
    }

    public WantedBook findById(Long wantedBookId) {
        return wantedBookRepository.findById(wantedBookId)
                .orElseThrow(BookNotFoundException::new);
    }

    public WantedBook update(Long wantedBookId, WantedBook updateForWantedBook) {
        WantedBook wantedBookToUpdate = wantedBookRepository.findById(wantedBookId)
                .orElseThrow(BookNotFoundException::new);
        wantedBookToUpdate.setTitle(updateForWantedBook.getTitle());
        wantedBookToUpdate.setDescription(updateForWantedBook.getDescription());
        wantedBookToUpdate.setReleaseYear(updateForWantedBook.getReleaseYear());
        return wantedBookRepository.save(wantedBookToUpdate);
    }

    public void delete(Long wantedBookId) {
        WantedBook wantedBook = wantedBookRepository.findById(wantedBookId)
                .orElseThrow(BookNotFoundException::new);
        wantedBookRepository.delete(wantedBook);
    }

}
