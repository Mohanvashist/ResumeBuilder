package com.ResumeBuilder.ResumeBuilder.service;

import com.ResumeBuilder.ResumeBuilder.model.User;
import com.ResumeBuilder.ResumeBuilder.repository.UserReporsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserReporsitory userReporsitory;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userReporsitory.save(user);
    }

    @Override
    public User updateUser(User user){
        return userReporsitory.save(user);
    }

    @Override
    public void deleteUser(Long id){
        userReporsitory.deleteById(id);
    }

    @Override
    public User findByUsername(String username){
        return userReporsitory.findByUsername(username).orElse(null);
    }

    @Override
    public List<User> findAllUsers(){
        return userReporsitory.findAll();
    }

    @Override
    public Long numberOfUsers(){
        return userReporsitory.count();
    }
}
