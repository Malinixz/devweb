package com.example.origami.controller;

import com.example.origami.epico.Epico;
import com.example.origami.epico.EpicoRepository;
import com.example.origami.projeto.Projeto;
import com.example.origami.projeto.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("epico")
public class EpicoController {

    @Autowired
    private EpicoRepository repository;
    @Autowired
    private ProjetoRepository projetoRepository;
    @PostMapping("{projetoId}")
    public void saveEpico(@RequestBody Epico epico, @PathVariable Long projetoId){   // SALVAR EPICO NO BANCO A PARTIR DO ID DO PROJETO
        Optional<Projeto> optionalProjeto = projetoRepository.findById(projetoId);
        if (optionalProjeto.isPresent()){
            Projeto projeto = optionalProjeto.get();
            epico.setProjeto(projeto);
            repository.save(epico);
        }
    }

    @GetMapping
    public List<Epico> getAll(){    // CONSULTA TODOS EPICOS CADASTRADOS NO SISTEMA
        List<Epico> epicolist = repository.findAll();
        return epicolist;
    }
    @GetMapping("{id}")
    public Epico getEpico(@PathVariable Long id){   // CONSULTA EPICO POR ID
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Epico not found with id: " + id));
    }
    @GetMapping("projeto/{projetoId}")
    public List<Epico> getEpicoProjeto(@PathVariable Long projetoId){   // CONSULTA EPICO POR ID DE PROJETO
        Projeto projeto = projetoRepository.findById(projetoId).orElseThrow(() -> new RuntimeException("Epicos not found with id: " + projetoId));
        return projeto.getBacklog();
    }
    @DeleteMapping("{id}")
    public void deleteProjeto(@PathVariable Long id) {  // DELETA EPICO POR ID
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("Epico not found with id: " + id);
        }
    }
    @PutMapping("{id}")
    public void updateProjeto(@PathVariable Long id, @RequestBody Epico epico) {
        if (repository.existsById(id) && epico.getProjeto() != null) {  // VERIFICA SE O ID DO EPICO A SER ALTERADO EXISTE E SE HÁ PROJETO PREENCHIDO
            epico.setId(id); // Certifique-se de definir o ID para o projeto
            repository.save(epico);
        } else {
            throw new RuntimeException("Epico not found with id: " + id);
        }
    }
}
