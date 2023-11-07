package com.example.origami.epico;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EpicoRepository extends JpaRepository<Epico,Long> {  // JpaRepository<Tipo esperado, tipo id>
}
