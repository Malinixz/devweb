package com.example.origami.projeto;

import com.example.origami.epico.Epico;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "projeto")
@Table(name = "projeto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")  // Lombok indicando que o ID é representacao unica da entidade
public class Projeto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @JsonIgnore @OneToMany(mappedBy = "projeto", cascade = CascadeType.ALL, orphanRemoval = true)  // Permite operacoes em cascata no backlog e remove o epico do banco caso seja removido do backlog
    private List<Epico> backlog;

}
