package com.ResumeBuilder.ResumeBuilder.service;

import com.ResumeBuilder.ResumeBuilder.model.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);

    User updateUser(User user);

    void deleteUser(Long id);

    User findByUsername(String username);

    List<User> findAllUsers();

    Long numberOfUsers();
}
