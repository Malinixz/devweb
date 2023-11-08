package com.example.origami.historiausuario;

import com.example.origami.epico.Categoria;
import com.example.origami.epico.Epico;
import com.example.origami.tarefa.Tarefa;
import com.example.origami.tipos.TipoHist;
import com.example.origami.tipos.TipoTarefa;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

    @JsonIgnore @OneToMany(mappedBy = "histUsua", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tarefa> tarefas;


    public HistoriaUsuario(Categoria categoria, TipoHist tipo, Epico epico){
        this.setCategoria(categoria);
        this.setEpico(epico);
        this.setTipo(tipo);
        this.geraDesc();
    }

    public void geraDesc(){
        this.descricao = "Eu, como " + this.getEpico().getAgente() + ", quero " + this.getTipo().getDescricao() + " um/uma " + this.getEpico().getEntidade();
    }

    // METODO PARA GERAR AS TAREFAS
    public List<Tarefa> geraTarefas(){
        List<Tarefa> listTarefas = new ArrayList<>();
        List<TipoTarefa> tiposTarefa = this.getTipo().getTiposTarefa();

        for (TipoTarefa tipoTarefa : tiposTarefa){
            // INSTANCIA TAREFA
            Tarefa tarefa = new Tarefa(tipoTarefa,this);
            // ADICIONA NO ARRAY
            listTarefas.add(tarefa);
        }
        return listTarefas;
    }
}
