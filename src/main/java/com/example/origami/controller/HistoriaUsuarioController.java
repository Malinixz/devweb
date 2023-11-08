package com.example.origami.controller;

import com.example.origami.epico.Epico;
import com.example.origami.epico.EpicoRepository;
import com.example.origami.historiausuario.HistoriaUsuario;
import com.example.origami.historiausuario.HistResponseDTO;
import com.example.origami.historiausuario.HistoriaUsuarioRepository;
import com.example.origami.tipos.TipoHist;
import com.example.origami.tipos.TipoHistRepository;
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
    @Autowired
    private TipoHistRepository tipoHistRepository;

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
    // GETALL COM DTO
    @GetMapping
    public List<HistResponseDTO> getAllHistoriaUsuarios() {
        List<HistResponseDTO> historias = historiaUsuarioRepository.findAll().stream().map(HistResponseDTO::new).toList();
        return historias;
    }
    // GET COM DTO
    @GetMapping("{id}")
    public HistResponseDTO getHistoriaUsuario(@PathVariable Long id) {
        HistoriaUsuario historiaUsuario = historiaUsuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("História de Usuário não encontrada com ID: " + id));
        return new HistResponseDTO(historiaUsuario);
    }
    @DeleteMapping("{id}")
    public void deleteHistoriaUsuario(@PathVariable Long id) {
        historiaUsuarioRepository.deleteById(id);
    }
    @PutMapping("{id}")
    public void updateTipoHistoriaUsuario(@PathVariable Long id, @RequestBody HistoriaUsuario historiaUsuario) {    // ATUALIZA TANTO O TIPO QUANTO A CATEGORIA
        HistoriaUsuario existingHistoriaUsuario = historiaUsuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("História de usuário não encontrada com ID: " + id));

        if (historiaUsuario.getTipo() != null) { // APENAS O ID DO TIPO É PASSADO NO BODY PORTANTO O TIPO É PRODURADO POR ID E SETADO NA HISTORIA
            TipoHist existingTipoHist = tipoHistRepository.findById(historiaUsuario.getTipo().getId()).orElseThrow(() -> new RuntimeException("Tipo Historia de usuário não encontrada"));
            existingHistoriaUsuario.setTipo(existingTipoHist);
            existingHistoriaUsuario.geraDesc();
        }

        if (historiaUsuario.getCategoria() != null) {
            existingHistoriaUsuario.setCategoria(historiaUsuario.getCategoria());
        }

        historiaUsuarioRepository.save(existingHistoriaUsuario);
    }
}