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
    private EpicoRepository epicoRepository;
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
            epico.setDescricao(epico.geraDesc());

            epicoRepository.save(epico);
        }
    }

    @GetMapping
    public List<Epico> getAll(){    // CONSULTA TODOS EPICOS CADASTRADOS NO SISTEMA
        List<Epico> epicolist = epicoRepository.findAll();
        return epicolist;
    }
    @GetMapping("{id}")
    public Epico getEpico(@PathVariable Long id){   // CONSULTA EPICO POR ID
        return epicoRepository.findById(id).orElseThrow(() -> new RuntimeException("Epico not found with id: " + id));
    }
    @GetMapping("projeto/{projetoId}")
    public List<Epico> getEpicoProjeto(@PathVariable Long projetoId){   // CONSULTA EPICO POR ID DE PROJETO
        Projeto projeto = projetoRepository.findById(projetoId).orElseThrow(() -> new RuntimeException("Epicos not found with id: " + projetoId));
        return projeto.getBacklog();
    }
    @DeleteMapping("{id}")
    public void deleteEpico(@PathVariable Long id) {  // DELETA EPICO POR ID
        if (epicoRepository.existsById(id)) {
            epicoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Epico not found with id: " + id);
        }
    }
    @PutMapping("{id}")
    public void updateEpico(@PathVariable Long id, @RequestBody Epico epico) {
        Epico existingEpico = epicoRepository.findById(id).orElseThrow(() -> new RuntimeException("Epico nao encontrado com id: " + id));
        // SE O ATRIBUTO FOI PASSADO NO BODY, ELE SETA NO EPICO, CASO CONTRARIO MANTEM O ANTERIOR
        if(epico.getTitulo() != null) { existingEpico.setTitulo(epico.getTitulo()); }              // TITULO
        if(epico.getAgente() != null) { existingEpico.setAgente(epico.getAgente()); }              // AGENTE
        if(epico.getEntidade() != null) { existingEpico.setEntidade(epico.getEntidade()); }        // ENTIDADE
        if(epico.getRelevancia() != null) { existingEpico.setRelevancia(epico.getRelevancia()); }  // RELEVANCIA
        if(epico.getCategoria() != null) { existingEpico.setCategoria(epico.getCategoria()); }     // CATEGORIA

        existingEpico.setDescricao(existingEpico.geraDesc());                                      // GERA E SALVA DESCRICAO
        epicoRepository.save(existingEpico);
    }
}
