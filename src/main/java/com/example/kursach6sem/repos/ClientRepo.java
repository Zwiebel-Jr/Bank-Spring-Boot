package com.example.kursach6sem.repos;

import com.example.kursach6sem.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClientRepo extends JpaRepository<Client, Long> {
    Optional<Client> findById(Long id);

    List<Client> findAll();
}
