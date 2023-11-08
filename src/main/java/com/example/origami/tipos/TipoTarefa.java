package com.example.origami.tipos;

import com.example.origami.historiausuario.HistoriaUsuario;
import com.example.origami.tarefa.Tarefa;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "tipotarefa")
@Table(name = "tipotarefa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class TipoTarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String descricao;
    @ManyToOne @JoinColumn(name = "tipohist_id")
    private TipoHist tipoHist;
    @JsonIgnore
    @OneToMany(mappedBy = "tipo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tarefa> tarefas;
}
