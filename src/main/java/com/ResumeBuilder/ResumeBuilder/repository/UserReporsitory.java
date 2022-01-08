package com.ResumeBuilder.ResumeBuilder.repository;

import com.ResumeBuilder.ResumeBuilder.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserReporsitory extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String name);
}
