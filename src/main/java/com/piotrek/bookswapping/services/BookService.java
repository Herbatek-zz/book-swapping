package com.piotrek.bookswapping.services;

import com.piotrek.bookswapping.entities.Book;
import com.piotrek.bookswapping.exceptions.BookNotFoundException;
import com.piotrek.bookswapping.respositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Iterable<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(Long bookId, Book updateForBook) {

        Book bookToUpdate = bookRepository.findById(bookId)
                .orElseThrow(BookNotFoundException::new);

        bookToUpdate.setTitle(updateForBook.getTitle());
        bookToUpdate.setReleaseYear(updateForBook.getReleaseYear());
        bookToUpdate.setDescription(updateForBook.getDescription());

        return bookRepository.save(bookToUpdate);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

}
