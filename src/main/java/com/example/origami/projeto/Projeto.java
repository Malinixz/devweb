package com.example.origami.projeto;

import com.example.origami.epico.Epico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "projeto")
@Table(name = "projeto")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")  // Lombok indicando que o ID é representacao unica da entidade
public class Projeto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @OneToMany(mappedBy = "projeto", cascade = CascadeType.ALL, orphanRemoval = true)  // Permite operacoes em cascata no backlog e remove o epico do banco caso seja removido do backlog
    private List<Epico> backlog;

    public Projeto(ProjetoRequestDTO data){     // Construtor para gerar um objeto Projeto a partir do DTO
        this.nome = data.nome();
    }
}
