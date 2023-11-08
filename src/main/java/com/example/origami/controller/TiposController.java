package com.example.origami.controller;

import com.example.origami.epico.Epico;
import com.example.origami.projeto.Projeto;
import com.example.origami.tipos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("tipo")
public class TiposController {

    @Autowired
    private TipoEpicoRepository tipoEpicoRepository;
    @Autowired
    private TipoHistRepository tipoHistRepository;
    @Autowired
    private TipoTarefaRepository tipoTarefaRepository;



    // CRUD PARA TIPO EPICO
    @GetMapping("epico")
    public List<TipoEpico> getAllTiposEpico() {
        return tipoEpicoRepository.findAll();
    }

    @GetMapping("epico/{id}")
    public Optional<TipoEpico> getTipoEpicoById(@PathVariable Long id) {
        return tipoEpicoRepository.findById(id);
    }

    @PostMapping("epico")
    public void saveTipoEpico(@RequestBody TipoEpico tipoEpico) {
        tipoEpicoRepository.save(tipoEpico);
    }

    @PutMapping("epico/{id}")
    public void updateDescTipoEpico(@PathVariable Long id, @RequestBody TipoEpico tipoEpico) {  // NAO ESTA FUNCIONANDO!!!
        TipoEpico existingTipoEpico = tipoEpicoRepository.findById(id).orElseThrow(() -> new RuntimeException("TipoEpico nao encontrado com id: " + id));
        existingTipoEpico.setDescricao(tipoEpico.getDescricao());
        tipoEpicoRepository.save(existingTipoEpico);
    }

    @DeleteMapping("epico/{id}")
    public void deleteTipoEpico(@PathVariable Long id) {
        tipoEpicoRepository.deleteById(id);
    }

/*--------------------------------------------------------------------------------------------------------------------*/

    // CRUD PARA TIPO DE HISTORIA DE USUARIO
    @PostMapping("hist/{tipoEpicId}")
    public void saveTipoHist(@RequestBody TipoHist tipoHist,@PathVariable Long tipoEpicId){
        Optional<TipoEpico> optionalTipoEpico = tipoEpicoRepository.findById(tipoEpicId); // PROCURA O TIPOEPIC POR ID
        if(optionalTipoEpico.isPresent()){                                                // VERIFICA SE EXISTE
            TipoEpico tipoEpico = optionalTipoEpico.get();
            tipoHist.setTipoepico(tipoEpico);                                             // SETA O TIPOEPIC DO TIPOHIST

            tipoHistRepository.save(tipoHist);                                            // SALVA O TIPOHIST NO BANCO
        }
    }
    @DeleteMapping("hist/{id}")
    public void deleteTipoHist(@PathVariable Long id) {
        tipoHistRepository.deleteById(id);
    }

    @GetMapping("hist")
    public List<TipoHist> getAllTiposHist() {
        return tipoHistRepository.findAll();
    }
    @GetMapping("hist/{id}")
    public Optional<TipoHist> getTipoHistById(@PathVariable Long id) {
        return tipoHistRepository.findById(id);
    }

    @PutMapping("hist/{id}")
    public void updateDescTipoHist(@PathVariable Long id, @RequestBody TipoHist tipoHist) {
        TipoHist existingTipoHist = tipoHistRepository.findById(id).orElseThrow(() -> new RuntimeException("TipoHist nao encontrado com id: " + id));
        existingTipoHist.setDescricao(tipoHist.getDescricao());
        tipoHistRepository.save(existingTipoHist);
    }

/*--------------------------------------------------------------------------------------------------------------------*/

    // CRUD PARA TIPOS DE TAREFAS
    @PostMapping("tarefa/{tipoHistId}")
    public void saveTipoTarefa(@RequestBody TipoTarefa tipoTarefa,@PathVariable Long tipoHistId){
        Optional<TipoHist> optionalTipoHist = tipoHistRepository.findById(tipoHistId);   // PROCURA O TIPOHIST POR ID
        if(optionalTipoHist.isPresent()){                                                // VERIFICA SE EXISTE
            TipoHist tipoHist = optionalTipoHist.get();
            tipoTarefa.setTipoHist(tipoHist);                                            // SETA O TIPOHIST DO TIPOTAREFA

            tipoTarefaRepository.save(tipoTarefa);                                       // SALVA O TIPOTAREFA NO BANCO
        }
    }
    @DeleteMapping("tarefa/{id}")
    public void deleteTarefaHist(@PathVariable Long id) {
        tipoTarefaRepository.deleteById(id);
    }
    @GetMapping("tarefa")
    public List<TipoTarefa> getAllTiposTarefa() {
        return tipoTarefaRepository.findAll();
    }
    @GetMapping("tarefa/{id}")
    public Optional<TipoTarefa> getTipoTarefaById(@PathVariable Long id) {
        return tipoTarefaRepository.findById(id);
    }
    @PutMapping("tarefa/{id}")
    public void updateDescTipoTarefa(@PathVariable Long id, @RequestBody TipoTarefa tipoTarefa) {
        TipoTarefa existingTipoTarefa = tipoTarefaRepository.findById(id).orElseThrow(() -> new RuntimeException("TipoTarefa nao encontrado com id: " + id));
        existingTipoTarefa.setDescricao(tipoTarefa.getDescricao());
        tipoTarefaRepository.save(existingTipoTarefa);
    }
}
