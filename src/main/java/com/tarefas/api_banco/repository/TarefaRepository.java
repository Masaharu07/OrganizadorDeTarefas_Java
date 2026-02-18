package com.tarefas.api_banco.repository;

import com.tarefas.api_banco.model.PrioridadeTarefa;
import com.tarefas.api_banco.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface TarefaRepository extends JpaRepository<Tarefa, UUID>{
    void deleteByDataTarefaBefore(LocalDate limite);
    List<Tarefa> findByTituloContainingIgnoreCase(String titulo);
    List<Tarefa> findByTituloContainingIgnoreCaseAndConcluido(String titulo, boolean concluido);
    List<Tarefa> findByTituloContainingIgnoreCaseAndPrioridade(String titulo, PrioridadeTarefa prioridade);
    List<Tarefa> findByTituloContainingIgnoreCaseAndConcluidoAndPrioridade(
            String titulo, boolean concluido, PrioridadeTarefa prioridade
    );
    List<Tarefa> findByConcluidoFalseAndDataTarefaGreaterThanEqualOrderByDataTarefaAsc(LocalDate hoje);
    List<Tarefa> findAllByConcluidoAndPrioridade(boolean concluido, PrioridadeTarefa prioridade);
    List<Tarefa> findByConcluido(boolean concluido);
    List<Tarefa> findByPrioridade(PrioridadeTarefa prioridade);
}