package com.piotrek.bookswapping.commandLineRunners;

import com.piotrek.bookswapping.entities.Book;
import com.piotrek.bookswapping.entities.User;
import com.piotrek.bookswapping.services.BookService;
import com.piotrek.bookswapping.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class InitCommandLineRunner implements CommandLineRunner {

    private BookService bookService;
    private UserService userService;

    public InitCommandLineRunner(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        User user = new User("Piotr", "Cużytek", "kenshin", "password",
                "kenshin@email.com");
        userService.createUser(user);

        Stream.of("Pan Lodowego Ogrodu Tom 1", "Idź i czekaj mrozów Tom 1", "Pan Lodowego Ogrodu Tom 2",
                "Wiedźmin: Ostatnie życzenie", "PORĄB I SPAL", "Mitologia Nordycka", "Księga jesiennych demonów")
                .forEach(title -> userService.createBook(1L, new Book(title)));
    }
}
