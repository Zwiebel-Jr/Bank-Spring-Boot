package com.example.kursach6sem.repos;

import com.example.kursach6sem.domain.Credit;
import com.example.kursach6sem.domain.CreditType;
import com.example.kursach6sem.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditTypeRepo extends JpaRepository<CreditType, Long> {
    List<CreditType> findAll();

    CreditType findByName(String name);
}
