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
@Table(name = "citizenship")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Citizenship {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "country_name")
    private String countryName;
   // @JsonIgnore
   // @OneToMany(mappedBy = "citizenship", cascade = CascadeType.ALL, orphanRemoval = true)
   // private List<Client> clients;
}
