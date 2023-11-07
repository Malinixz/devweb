package com.example.origami.epico;

import com.example.origami.projeto.Projeto;
import com.example.origami.tipos.TipoEpico;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Epico")
@Table(name = "Epico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")  // Lombok indicando que o ID é representacao unica da entidade
public class Epico {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @ManyToOne @JoinColumn(name = "projeto_id")
    private Projeto projeto;
    @ManyToOne @JoinColumn(name = "tipo_id")
    private TipoEpico tipo;
}
