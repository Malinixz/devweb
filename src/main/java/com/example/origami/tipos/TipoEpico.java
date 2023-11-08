package com.example.origami.tipos;

import com.example.origami.epico.Epico;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "tipoepico")
@Table(name = "tipoepico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class TipoEpico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    @JsonIgnore @OneToMany(mappedBy = "tipo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Epico> epicos;
    @JsonIgnore @OneToMany(mappedBy = "tipoepico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TipoHist> tiposHist;
}
