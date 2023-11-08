package com.example.origami.controller;

import com.example.origami.epico.Epico;
import com.example.origami.epico.EpicoRepository;
import com.example.origami.historiausuario.HistoriaUsuario;
import com.example.origami.historiausuario.HistoriaUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("hist")
public class HistoriaUsuarioController {

    @Autowired
    private HistoriaUsuarioRepository historiaUsuarioRepository;
    @Autowired
    private EpicoRepository epicoRepository;

    @PostMapping("{epicoId}")
    public void gerarHistoriasUsuario(@PathVariable Long epicoId) {
        Optional<Epico> optionalEpico = epicoRepository.findById(epicoId);

        if (optionalEpico.isEmpty()) {
            return;
        }

        Epico epico = optionalEpico.get();
        List<HistoriaUsuario> historiasUsuario = epico.geraHists();

        for (HistoriaUsuario historia : historiasUsuario) {
            historiaUsuarioRepository.save(historia); // Salva cada história de usuário no banco
        }
    }
    @GetMapping
    public List<HistoriaUsuario> getAllHistoriaUsuarios() {
        return historiaUsuarioRepository.findAll();
    }
    @GetMapping("{id}")
    public HistoriaUsuario getHistoriaUsuario(@PathVariable Long id) {
        return historiaUsuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Historia de Usuario nao encontrado com id: " + id));
    }
    @DeleteMapping("{id}")
    public void deleteHistoriaUsuario(@PathVariable Long id) {
        historiaUsuarioRepository.deleteById(id);
    }
    @PutMapping("tipo/{id}")
    public void updateTipoHistoriaUsuario(@PathVariable Long id, @RequestBody HistoriaUsuario historiaUsuario) {    // FALTA CONSERTAR!!!!
        HistoriaUsuario existingHistoriaUsuario = historiaUsuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("História de usuário não encontrada com ID: " + id));

        if (historiaUsuario.getTipo() != null) {
            existingHistoriaUsuario.setTipo(historiaUsuario.getTipo());
            existingHistoriaUsuario.setDescricao(existingHistoriaUsuario.getEpico().getAgente(), historiaUsuario.getTipo().getDescricao(), existingHistoriaUsuario.getEpico().getEntidade());
        }

        if (historiaUsuario.getCategoria() != null) {
            existingHistoriaUsuario.setCategoria(historiaUsuario.getCategoria());
        }

        historiaUsuarioRepository.save(existingHistoriaUsuario);
    }
}