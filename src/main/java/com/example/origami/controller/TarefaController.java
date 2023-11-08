package com.example.origami.controller;

import com.example.origami.historiausuario.HistoriaUsuario;
import com.example.origami.historiausuario.HistoriaUsuarioRepository;
import com.example.origami.tarefa.Tarefa;
import com.example.origami.tarefa.TarefaRepository;
import com.example.origami.tarefa.TarefaUptadeDTO;
import com.example.origami.tarefa.TarefaResponseDTO;
import com.example.origami.tipos.TipoHist;
import com.example.origami.tipos.TipoTarefa;
import com.example.origami.tipos.TipoTarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("tarefa")
public class TarefaController {

    @Autowired
    TarefaRepository tarefaRepository;
    @Autowired
    HistoriaUsuarioRepository histRepository;
    @Autowired
    TipoTarefaRepository tipoTarefaRepository;

    @PostMapping("{histId}")
    public void saveTarefa(@PathVariable Long histId){
        Optional<HistoriaUsuario> optionalHist = histRepository.findById(histId);

        if (optionalHist.isEmpty()){
            return;
        }

        HistoriaUsuario hist = optionalHist.get();
        List<Tarefa> listTarefas = hist.geraTarefas();

        for (Tarefa tarefa : listTarefas){
            tarefaRepository.save(tarefa);
        }
    }
    @GetMapping
    public List<TarefaResponseDTO> getAllTarefas() {
        List<TarefaResponseDTO> tarefas = tarefaRepository.findAll().stream().map(TarefaResponseDTO::new).toList();
        return tarefas;
    }
    @GetMapping("{id}")
    public TarefaResponseDTO getTarefa(@PathVariable Long id) {
        Tarefa tarefa = tarefaRepository.findById(id).orElseThrow(() -> new RuntimeException("Tarefa não encontrada com ID: " + id));
        return new TarefaResponseDTO(tarefa);
    }
    @DeleteMapping("{id}")
    public void deleteTarefaUsuario(@PathVariable Long id) {
        tarefaRepository.deleteById(id);
    }
    @PutMapping("{id}")
    public void updateTipoTarefa(@PathVariable Long id, @RequestBody TarefaUptadeDTO data) {
        Tarefa existingTarefa = tarefaRepository.findById(id).orElseThrow(() -> new RuntimeException("Tarefa não encontrada com ID: " + id));

        if (data.tipo_id() != null) {
            TipoTarefa existingTipoTarefa = tipoTarefaRepository.findById(data.tipo_id()).orElseThrow(() -> new RuntimeException("Tipo Historia de usuário não encontrada"));
            existingTarefa.setTipo(existingTipoTarefa);
            existingTarefa.geraDesc();
        }

        tarefaRepository.save(existingTarefa);
    }
}
