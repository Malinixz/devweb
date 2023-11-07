package com.example.origami.tipos;

import com.example.origami.historiausuario.HistoriaUsuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "tipohist")
@Table(name = "tipohist")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class TipoHist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    @ManyToOne @JoinColumn(name = "tipoepic_id")
    private TipoEpico tipoepico;
    @JsonIgnore @OneToMany(mappedBy = "tipo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HistoriaUsuario> historias;
}
