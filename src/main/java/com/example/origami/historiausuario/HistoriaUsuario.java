package com.example.origami.historiausuario;

import com.example.origami.tipos.TipoHist;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "hist")
@Table(name = "hist")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class HistoriaUsuario {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @ManyToOne @JoinColumn(name = "tipo_id")
    private TipoHist tipo;
}
