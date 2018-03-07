package com.piotrek.bookswapping.services;

import com.piotrek.bookswapping.entities.User;
import com.piotrek.bookswapping.respositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers() {
        return null;
    }

    public User createUser() {
        return null;
    }

    public User updateUser() {
        return null;
    }

    public User deleteUser() {
        return null;
    }
}
