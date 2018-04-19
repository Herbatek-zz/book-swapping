package com.piotrek.bookswapping.services;

import com.piotrek.bookswapping.dto.BookForExchangeDto;
import com.piotrek.bookswapping.dto.UserDto;
import com.piotrek.bookswapping.dto.WantedBookDto;
import com.piotrek.bookswapping.entities.BookForExchange;
import com.piotrek.bookswapping.entities.User;
import com.piotrek.bookswapping.entities.WantedBook;
import com.piotrek.bookswapping.exceptions.UserNotFoundException;
import com.piotrek.bookswapping.repositories.UserRepository;
import com.piotrek.bookswapping.services.converters.UserConverter;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.internal.util.Lists;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserService {

    private UserRepository userRepository;
    private BookForExchangeService bookForExchangeService;
    private WantedBookService wantedBookService;
    private UserConverter userConverter;

    public UserService(UserRepository userRepository, BookForExchangeService bookForExchangeService,
                       WantedBookService wantedBookService, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.wantedBookService = wantedBookService;
        this.bookForExchangeService = bookForExchangeService;
        this.userConverter = userConverter;
    }

    public Iterable<UserDto> findAll() {
        List<User> users = Lists.from(userRepository.findAll().iterator());
        return users.stream()
                .map(userConverter::convertToDto)
                .collect(Collectors.toList());
    }

    public UserDto findById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        return userConverter.convertToDto(user);
    }

    public UserDto create(User user) {
        User createdUser = userRepository.save(user);
        return userConverter.convertToDto(createdUser);
    }

    public void update(Long userId, User updateForUser) {
        User userToUpdate = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        userToUpdate.setUsername(updateForUser.getUsername());
        userToUpdate.setEmail(updateForUser.getEmail());
        userToUpdate.setPassword(updateForUser.getPassword());
        userToUpdate.setFirstName(updateForUser.getFirstName());
        userToUpdate.setLastName(updateForUser.getLastName());
        userRepository.save(userToUpdate);
    }

    public void delete(Long id) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        userRepository.delete(user);
    }

    public WantedBookDto createWantedBook(Long id, @Valid WantedBook wantedBook) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        wantedBook.setUser(user);
        return wantedBookService.create(wantedBook);
    }

    public BookForExchangeDto createBookForExchange(Long id, @Valid BookForExchange bookForExchange) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        bookForExchange.setUser(user);
        return bookForExchangeService.create(bookForExchange);
    }

    public Iterable<WantedBookDto> findWantedBooksById(Long userId) {
        return wantedBookService.findWantedBookByUserId(userId);
    }

    public Iterable<BookForExchangeDto> findBooksForExchange(Long userId) {
        return bookForExchangeService.findBooksForExchangeByUserId(userId);
    }

}
