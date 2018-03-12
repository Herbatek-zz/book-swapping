package com.piotrek.bookswapping.commandLineRunners;

import com.piotrek.bookswapping.entities.Book;
import com.piotrek.bookswapping.services.BookService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class BookCommandLineRunner implements CommandLineRunner {

    private BookService bookService;

    public BookCommandLineRunner(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
        Stream.of("Pan Lodowego Ogrodu Tom 1", "Idź i czekaj mrozów Tom 1", "Pan Lodowego Ogrodu Tom 2",
                "Wiedźmin: Ostatnie życzenie", "PORĄB I SPAL", "Mitologia Nordycka", "Księga jesiennych demonów")
                .forEach(title -> bookService.createBook(new Book(title)));
    }
}
