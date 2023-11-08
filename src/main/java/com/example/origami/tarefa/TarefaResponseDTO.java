package com.example.origami.tarefa;

import com.example.origami.tipos.TipoTarefa;

public record TarefaResponseDTO(Long id, String desc, TipoTarefa tipo) {
    public TarefaResponseDTO(Tarefa tarefa){
        this(tarefa.getId(), tarefa.getDescricao(), tarefa.getTipo());
    }
}
