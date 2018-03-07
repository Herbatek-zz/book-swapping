package com.piotrek.bookswapping.services;

import com.piotrek.bookswapping.entities.Book;
import com.piotrek.bookswapping.respositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getBooks() {
        return null;
    }

    public Book createBook() {
        return null;
    }

    public Book updateBook() {
        return null;
    }

    public Book deleteBook() {
        return null;
    }

}
