package com.example.origami.controller;

import com.example.origami.epico.Epico;
import com.example.origami.epico.EpicoRepository;
import com.example.origami.projeto.Projeto;
import com.example.origami.projeto.ProjetoRepository;
import com.example.origami.tipos.TipoEpico;
import com.example.origami.tipos.TipoEpicoRepository;
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
    @Autowired
    private TipoEpicoRepository tipoEpicoRepository;
    @PostMapping("{projetoId}/{tipoEpicoId}")
    public void saveEpico(@RequestBody Epico epico, @PathVariable Long projetoId, @PathVariable Long tipoEpicoId){   // SALVAR EPICO NO BANCO A PARTIR DO ID DO PROJETO
        Optional<Projeto> optionalProjeto = projetoRepository.findById(projetoId);
        Optional<TipoEpico> optionalTipoEpico = tipoEpicoRepository.findById(tipoEpicoId);

        if (optionalProjeto.isPresent() && optionalTipoEpico.isPresent()){

            Projeto projeto = optionalProjeto.get();
            TipoEpico tipo = optionalTipoEpico.get();
            epico.setProjeto(projeto);
            epico.setTipo(tipo);

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
    public void deleteEpico(@PathVariable Long id) {  // DELETA EPICO POR ID
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("Epico not found with id: " + id);
        }
    }
    @PutMapping("titulo/{id}")
    public void updateTituloEpico(@PathVariable Long id, @RequestBody Epico epico) {
        Epico existingEpico = repository.findById(id).orElseThrow(() -> new RuntimeException("Epico nao encontrado com id: " + id));
        existingEpico.setTitulo(epico.getTitulo());
        repository.save(existingEpico);
    }
}
