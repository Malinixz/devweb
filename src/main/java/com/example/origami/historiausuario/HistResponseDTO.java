package com.example.origami.historiausuario;

import com.example.origami.tipos.TipoHist;

public record HistResponseDTO(Long id, String desc, String categoria, TipoHist tipo) {
    public HistResponseDTO(HistoriaUsuario hist){
        this(hist.getId(),hist.getDescricao(), String.valueOf(hist.getCategoria()), hist.getTipo());
    }
}
