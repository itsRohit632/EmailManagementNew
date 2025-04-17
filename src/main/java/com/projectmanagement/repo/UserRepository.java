package com.projectmanagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectmanagement.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
