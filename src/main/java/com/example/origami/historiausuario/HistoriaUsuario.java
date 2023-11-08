package com.example.origami.historiausuario;

import com.example.origami.epico.Categoria;
import com.example.origami.epico.Epico;
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
    private String descricao;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
    @ManyToOne @JoinColumn(name = "tipo_id")
    private TipoHist tipo;
    @ManyToOne @JoinColumn(name = "epico_id")
    private Epico epico;


    public HistoriaUsuario(Categoria categoria, TipoHist tipo, Epico epico){
        this.setCategoria(categoria);
        this.setEpico(epico);
        this.setTipo(tipo);
        this.geraDesc();
    }

    public void geraDesc(){
        this.descricao = "Eu, como " + this.getEpico().getAgente() + ", quero " + this.getTipo().getDescricao() + " um/uma " + this.getEpico().getEntidade();
    }

    /*public void setTipo(TipoHist tipo) {   // GARANTE QUE A DESCRICAO SEJA ALTERADA SEMPRE QUE HOUVER MUDANÇA NO TIPO
        this.tipo = tipo;
        this.setDescricao(this.getEpico().getAgente(),tipo.getDescricao(),this.getEpico().getEntidade());
    }*/
}
