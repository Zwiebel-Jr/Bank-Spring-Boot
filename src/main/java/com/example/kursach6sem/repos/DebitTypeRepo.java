package com.example.kursach6sem.repos;

import com.example.kursach6sem.domain.CreditType;
import com.example.kursach6sem.domain.DebitType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DebitTypeRepo extends JpaRepository<DebitType, Long> {
    List<DebitType> findAll();

    DebitType findByName(String name);
}
