package com.example.origami.projeto;

public record ProjetoResponseDTO(Long id, String nome) {
    public ProjetoResponseDTO(Projeto projeto){
        this(projeto.getId(), projeto.getNome());
    }
}
