package com.example.kursach6sem.repos;

import com.example.kursach6sem.domain.Client;
import com.example.kursach6sem.domain.Credit;
import com.example.kursach6sem.domain.Debit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DebitRepo extends JpaRepository<Debit, Long> {
    Optional<Debit> findById(Long id);

    List<Debit> findAll();

    Debit findByClient(Client client);
}
