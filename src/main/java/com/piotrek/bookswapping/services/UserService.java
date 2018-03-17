package com.piotrek.bookswapping.services;

import com.piotrek.bookswapping.entities.Book;
import com.piotrek.bookswapping.entities.User;
import com.piotrek.bookswapping.exceptions.UserNotFoundException;
import com.piotrek.bookswapping.respositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;
    private BookService bookService;

    public UserService(UserRepository userRepository, BookService bookService) {
        this.userRepository = userRepository;
        this.bookService = bookService;
    }

    public Iterable<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long userId, User updateForUser) {

        User userToUpdate = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        userToUpdate.setUsername(updateForUser.getUsername());
        userToUpdate.setEmail(updateForUser.getEmail());
        userToUpdate.setPassword(updateForUser.getPassword());
        userToUpdate.setFirstName(updateForUser.getFirstName());
        userToUpdate.setLastName(updateForUser.getLastName());

        return userRepository.save(userToUpdate);
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        userRepository.delete(user);
    }

    public Book createBook(Long userId, Book book) {
        User user = findUserById(userId);
        book.setUser(user);
        return bookService.createBook(book);
    }
}
