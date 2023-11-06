package com.example.origami.epico;

import com.example.origami.projeto.Projeto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Epico")
@Table(name = "Epico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")  // Lombok indicando que o ID é representacao unica da entidade
public class Epico {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @ManyToOne @JoinColumn(name = "projeto_id")
    private Projeto projeto;
}
