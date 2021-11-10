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
@Table(name = "disability")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Disability {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
  //  @JsonIgnore
   // @OneToMany(mappedBy = "disability", cascade = CascadeType.ALL, orphanRemoval = true)
   // private List<Client> clients;
}
