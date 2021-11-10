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
@Table(name = "marital_status")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MaritalStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String status;
  //  @JsonIgnore
  //  @OneToMany(mappedBy = "maritalStatus", cascade = CascadeType.ALL, orphanRemoval = true)
   // private List<Client> clients;
}
