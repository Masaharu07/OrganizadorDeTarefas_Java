package com.tarefas.api_banco.controller;

import com.tarefas.api_banco.model.PrioridadeTarefa;
import com.tarefas.api_banco.model.Tarefa;
import com.tarefas.api_banco.repository.TarefaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {
    private final TarefaRepository repository;

    public TarefaController(TarefaRepository repository){
        this.repository = repository;
    }

    @PostMapping
    public Tarefa criar(@RequestBody Tarefa tarefa){
        return repository.save(tarefa);
    }

    @GetMapping
    public List<Tarefa> Listar(
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) Boolean concluido,
            @RequestParam(required = false) PrioridadeTarefa prioridade){
        boolean temTitulo = (titulo != null && !titulo.isBlank());
        if(temTitulo && concluido != null && prioridade != null){
            return repository.findByTituloContainingIgnoreCaseAndConcluidoAndPrioridade(titulo, concluido, prioridade);
        }
        if(temTitulo && concluido != null){
            return repository.findByTituloContainingIgnoreCaseAndConcluido(titulo, concluido);
        }
        if(temTitulo && prioridade != null){
            return repository.findByTituloContainingIgnoreCaseAndPrioridade(titulo, prioridade);
        }
        if(temTitulo){
            return repository.findByTituloContainingIgnoreCase(titulo);
        }
        if(concluido != null && prioridade != null){
            return repository.findAllByConcluidoAndPrioridade(concluido, prioridade);
        }
        if (concluido != null){
            return repository.findByConcluido(concluido);
        }
        if (prioridade != null){
            return repository.findByPrioridade(prioridade);
        }
        return repository.findAllByOrderByConcluidoAsc(concluido);
    }

    @PatchMapping("/{id}/concluido")
    public Tarefa concluir(@PathVariable UUID id){
        Tarefa conc = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Tarefa não encontrada!"));
        conc.setConcluido(!conc.isConcluido());
        return repository.save(conc);
    }

    @PutMapping("/{id}")
    public Tarefa atualizar(@RequestBody Tarefa nova, @PathVariable UUID id) {
        Tarefa existente = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada!"));

        existente.setTitulo(nova.getTitulo());
        existente.setDescricao(nova.getDescricao());
        existente.setConcluido(nova.isConcluido());
        existente.setPrioridade(nova.getPrioridade());

        return repository.save(existente);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable UUID id){
        Tarefa existente = repository.findById(id)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Tarefa não encontrada!"));
        repository.deleteById(id);
    }
}