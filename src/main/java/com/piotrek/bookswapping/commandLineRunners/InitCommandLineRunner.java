package com.piotrek.bookswapping.commandLineRunners;

import com.piotrek.bookswapping.entities.BookForExchange;
import com.piotrek.bookswapping.entities.User;
import com.piotrek.bookswapping.entities.WantedBook;
import com.piotrek.bookswapping.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.stream.Stream;

@Component
public class InitCommandLineRunner implements CommandLineRunner {

    private UserService userService;

    public InitCommandLineRunner(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) {

        Random random = new Random();

        User piotrek = new User("Piotr", "Cużytek", "kenshin",
            new BCryptPasswordEncoder().encode("password"), "kenshin@email.com");
        User grzesiek = new User("Grzegorz", "Lenartowicz", "leny",
                new BCryptPasswordEncoder().encode("jestemGlupi123"), "leny@email.com");
        userService.create(piotrek);
        userService.create(grzesiek);

        Stream.of("Pan Lodowego Ogrodu Tom 1", "Idź i czekaj mrozów Tom 1", "Pan Lodowego Ogrodu Tom 2",
                "Wiedźmin: Ostatnie życzenie", "PORĄB I SPAL", "Mitologia Nordycka")
                .forEach(title -> userService.createBookForExchange((long)(random.nextInt(2) + 1), new BookForExchange(title)));

        Stream.of("Zaszyj oczy wilkom", "Ksiega jesiennych demonów", "Wilk samotnik")
                .forEach(title -> userService.createWantedBook((long)(random.nextInt(2) + 1), new WantedBook(title)));
    }
}
