package com.example.origami.controller;

import com.example.origami.projeto.Projeto;
import com.example.origami.projeto.ProjetoRepository;
import com.example.origami.projeto.ProjetoRequestDTO;
import com.example.origami.projeto.ProjetoResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("projeto")
public class ProjetoController {

    @Autowired // Cria a instancia da dependencia (projetorepository) automaticamente
    private ProjetoRepository repository;
    @GetMapping
    public List<ProjetoResponseDTO> getAll(){

        List<ProjetoResponseDTO> projetolist = repository.findAll().stream().map(ProjetoResponseDTO::new).toList();
        return projetolist;
    }
    // METODO GETALL SEM A UTILIZACAO DE DTO
    /*public List<Projeto> getAll(){
        List<Projeto> projetoList = repository.findAll().stream().toList();
        return projetoList;
    }*/

    @PostMapping
    public void saveProjeto(@RequestBody ProjetoRequestDTO data){
        Projeto projetoData = new Projeto(data);
        repository.save(projetoData);
    }
}
