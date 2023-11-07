package com.example.origami.controller;

import com.example.origami.projeto.Projeto;
import com.example.origami.projeto.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController  // INDICA QUE A CLASSE E CONTROLLER
@RequestMapping("projeto")
public class ProjetoController {

    @Autowired // Cria a instancia da dependencia (projetorepository) automaticamente
    private ProjetoRepository repository;
    @GetMapping
    public List<Projeto> getAll(){
        List<Projeto> projetoList = repository.findAll();
        return projetoList;
    }

    @PostMapping
    public void saveProjeto(@RequestBody Projeto projeto){
        repository.save(projeto);
    }
    @GetMapping("{id}")
    public Projeto getProjeto(@PathVariable Long id) {  // CONSULTA PROJETO POR ID
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Projeto nao encontrado com id: " + id));
    }

    @DeleteMapping("{id}")
    public void deleteProjeto(@PathVariable Long id) {  // DELETA PROJETO POR ID
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("Projeto nao encontrado com id: " + id);
        }
    }

    @PutMapping("{id}")
    public void updateProjeto(@PathVariable Long id, @RequestBody Projeto projeto) {
        Projeto existingProjeto = repository.findById(id).orElseThrow(() -> new RuntimeException("Projeto nao encontrado com id: " + id));
        existingProjeto.setNome(projeto.getNome()); // ATUALIZA O CAMPO NECESSARIO
        repository.save(existingProjeto);
    }
}
