package com.example.origami.tipos;

import jakarta.persistence.*;
import lombok.*;

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
}
