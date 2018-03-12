package com.piotrek.bookswapping.services;

import com.piotrek.bookswapping.entities.User;
import com.piotrek.bookswapping.respositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Iterable<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser() {
        return null;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
