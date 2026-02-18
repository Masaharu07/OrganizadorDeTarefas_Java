package com.tarefas.api_banco.scheduler;

import com.tarefas.api_banco.repository.TarefaRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class LimpezaTarefasScheduler {
    private final TarefaRepository repository;
    public LimpezaTarefasScheduler(TarefaRepository repository){
        this.repository = repository;
    }
    @Scheduled(cron = "0 0 2 * * *", zone = "America/Sao_Paulo")
    public void LimparTarefasAntigas(){
        LocalDate limite = LocalDate.now().minusDays(30);
        repository.deleteByDataTarefaBefore(limite);
    }
}
