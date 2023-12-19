package com.example.proiectcolectiv731_be.repository;

import com.example.proiectcolectiv731_be.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // checks uniqueness of given username
    // returns TRUE if a user with the given username already exists
    // false otherwise
    boolean existsByUsername(String username);
}
