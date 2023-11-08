package com.example.origami.tarefa;

import com.example.origami.historiausuario.HistoriaUsuario;
import com.example.origami.tipos.TipoTarefa;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "tarefa")
@Table(name = "tarefa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Tarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    @ManyToOne @JoinColumn(name = "tipo_id")
    private TipoTarefa tipo;
    @ManyToOne @JoinColumn(name = "hist_id")
    private HistoriaUsuario histUsua;

    public Tarefa(TipoTarefa tipo, HistoriaUsuario histUsua){
        this.setTipo(tipo);
        this.setHistUsua(histUsua);
        this.geraDesc();
    }

    public void geraDesc(){
        this.descricao = this.getHistUsua().getTipo().getDescricao() + " "+ this.getTipo().getDescricao() + " de " + this.getHistUsua().getEpico().getEntidade();
    }
}
