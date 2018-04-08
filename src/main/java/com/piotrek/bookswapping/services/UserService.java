package com.piotrek.bookswapping.services;

import com.piotrek.bookswapping.entities.BookForExchange;
import com.piotrek.bookswapping.entities.User;
import com.piotrek.bookswapping.entities.WantedBook;
import com.piotrek.bookswapping.exceptions.UserNotFoundException;
import com.piotrek.bookswapping.respositories.BookForExchangeRepository;
import com.piotrek.bookswapping.respositories.UserRepository;
import com.piotrek.bookswapping.respositories.WantedBookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Slf4j
@Service
public class UserService {

    private UserRepository userRepository;
    private BookForExchangeRepository bookForExchangeRepository;
    private WantedBookRepository wantedBookRepository;

    public UserService(UserRepository userRepository, BookForExchangeRepository bookForExchangeRepository,
                       WantedBookRepository wantedBookRepository) {
        this.userRepository = userRepository;
        this.bookForExchangeRepository = bookForExchangeRepository;
        this.wantedBookRepository = wantedBookRepository;
    }

    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long userId) {
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public User update(Long userId, User updateForUser) {

        User userToUpdate = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        userToUpdate.setUsername(updateForUser.getUsername());
        userToUpdate.setEmail(updateForUser.getEmail());
        userToUpdate.setPassword(updateForUser.getPassword());
        userToUpdate.setFirstName(updateForUser.getFirstName());
        userToUpdate.setLastName(updateForUser.getLastName());

        return create(userToUpdate);
    }

    public void delete(Long id) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        userRepository.delete(user);
    }

    public WantedBook createWantedBook(Long id, @Valid WantedBook wantedBook) {
        User user = findById(id);
        wantedBook.setUser(user);
        return wantedBookRepository.save(wantedBook);
    }

    public BookForExchange createBookForExchange(Long id, @Valid BookForExchange bookForExchange) {
        User user = findById(id);
        bookForExchange.setUser(user);
        return bookForExchangeRepository.save(bookForExchange);
    }

    public Iterable<WantedBook> findWantedBooksById(Long userId) {
        return wantedBookRepository.findAllByUserId(userId);
    }

    public Iterable<BookForExchange> findBooksForExchange(Long userId) {
        return bookForExchangeRepository.findAllByUserId(userId);
    }
}
