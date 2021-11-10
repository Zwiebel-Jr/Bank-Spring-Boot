package com.example.kursach6sem.repos;

import com.example.kursach6sem.domain.Client;
import com.example.kursach6sem.domain.Credit;
import com.example.kursach6sem.domain.Debit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CreditRepo extends JpaRepository<Credit, Long> {
    Optional<Credit> findById(Long id);

    List<Credit> findAll();

    Credit findByClient(Client client);
}
