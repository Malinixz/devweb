package com.example.origami.projeto;

public record ProjetoResponseDTO(Long id, String nome) {

    public ProjetoResponseDTO(Projeto projeto){   // CONTRUTOR DO DTO DE RESPOSTA DE PROJETO
        this(projeto.getId(), projeto.getNome());
    }
}
