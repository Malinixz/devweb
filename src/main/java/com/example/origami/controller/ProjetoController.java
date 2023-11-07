package com.example.origami.controller;

import com.example.origami.projeto.Projeto;
import com.example.origami.projeto.ProjetoRepository;
import com.example.origami.projeto.ProjetoRequestDTO;
import com.example.origami.projeto.ProjetoResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController  // INDICA QUE A CLASSE E CONTROLLER
@RequestMapping("projeto")
public class ProjetoController {

    @Autowired // Cria a instancia da dependencia (projetorepository) automaticamente
    private ProjetoRepository repository;
    @GetMapping
    // METODO GETALL COM A UTILIZACAO DE DTO
    /*public List<ProjetoResponseDTO> getAll(){

        List<ProjetoResponseDTO> projetolist = repository.findAll().stream().map(ProjetoResponseDTO::new).toList();
        return projetolist;
    }*/
    public List<Projeto> getAll(){
        List<Projeto> projetoList = repository.findAll();
        return projetoList;
    }

    @PostMapping
    // METODO SAVE COM A UTILIZACAO DE DTO
    /*public void saveProjeto(@RequestBody ProjetoRequestDTO data){
        Projeto projetoData = new Projeto(data);
        repository.save(projetoData);
    }*/
    public void saveProjeto(@RequestBody Projeto projeto){
        repository.save(projeto);
    }
    @GetMapping("{id}")
    public Projeto getProjeto(@PathVariable Long id) {  // CONSULTA PROJETO POR ID
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Projeto not found with id: " + id));
    }

    @DeleteMapping("{id}")
    public void deleteProjeto(@PathVariable Long id) {  // DELETA PROJETO POR ID
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("Projeto not found with id: " + id);
        }
    }

    @PutMapping("{id}")
    public void updateProjeto(@PathVariable Long id, @RequestBody Projeto projeto) {    // NAO ESTA FUNCIONANDO!!!
        if (repository.existsById(id)) {
            projeto.setId(id); // Certifique-se de definir o ID para o projeto
            repository.save(projeto);
        } else {
            throw new RuntimeException("Projeto not found with id: " + id);
        }
    }
}
