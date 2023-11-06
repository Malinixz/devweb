package com.example.origami.projeto;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjetoRepository extends JpaRepository<Projeto, Long> { // JpaRepository<Tipo esperado, tipo id>
}
