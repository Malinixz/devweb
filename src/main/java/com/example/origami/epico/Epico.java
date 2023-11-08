package com.example.origami.epico;

import com.example.origami.historiausuario.HistoriaUsuario;
import com.example.origami.projeto.Projeto;
import com.example.origami.tipos.TipoEpico;
import com.example.origami.tipos.TipoHist;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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
    private String titulo;      // ACAO DO EPICO, EXEMPLO : "CADASTRAR"
    private String descricao;   // NÃO PREENCHER DESCRIÇÃO NO FRONT
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
    private Integer relevancia;
    private String agente;      // AGENTE PARA AS HU'S
    private String entidade;    // ENTIDADE PARA AS HU'S

    @ManyToOne @JoinColumn(name = "projeto_id")
    private Projeto projeto;
    @ManyToOne @JoinColumn(name = "tipo_id")
    private TipoEpico tipo;

    @JsonIgnore @OneToMany(mappedBy = "epico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HistoriaUsuario> hists;


    // METODO PARA GERAR DESCRICAO DO EPICO A PARTIR DO TITULO, AGENTE E ENTIDADE
    public String geraDesc(){
        return ("Eu, como " + this.agente + ", quero " + this.titulo + " um/uma " + this.entidade);
    }


    // METODO PARA GERAR AS HISTORIAS DE USUARIO
    public List<HistoriaUsuario> geraHists(){
        List<HistoriaUsuario> listHists = new ArrayList<>();        // INSTANCIA LISTA DE HISTORIAS A SER ALIMENTADA
        List<TipoHist> tiposHists = this.getTipo().getTiposHist();  // LISTA DE TIPOS DE HISTORIAS DESSE TIPO DE EPICO

        for (TipoHist tipoHist : tiposHists) {
            // INSTANCIA HISTORIA DE USUARIO
            HistoriaUsuario historiaUsuario = new HistoriaUsuario(this.getCategoria(),tipoHist,this);
            // ADICIONA NO ARRAY
            listHists.add(historiaUsuario);
        }
        return listHists;
    }
}
