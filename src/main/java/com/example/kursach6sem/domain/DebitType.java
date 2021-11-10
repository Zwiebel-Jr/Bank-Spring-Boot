package com.example.kursach6sem.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "debit_type")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class DebitType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String currency;
    private String name;
    @Column(name = "interest_rate")
    private Double interestRate;
    private Integer period;
    @JsonIgnore
    @OneToMany(mappedBy="type", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Debit> debits;
}
