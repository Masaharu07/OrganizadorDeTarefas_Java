package com.tarefas.api_banco.model;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name="tarefas")
public class Tarefa {
    @Id
    @UuidGenerator
    private UUID id;

    @Column(nullable = false, length = 100)
    private String titulo;
    @Column(length = 255)
    private String descricao;
    @Column(nullable = false)
    private boolean concluido;
    @Enumerated(EnumType.STRING)
    private PrioridadeTarefa prioridade;

    public UUID getId(){ return id; }

    public String getTitulo(){ return titulo; }
    public void setTitulo(String titulo){
        this.titulo = titulo;
    }

    public String getDescricao(){ return descricao;}
    public void setDescricao(String descricao){
        this.descricao = descricao;
    }

    public boolean isConcluido(){ return concluido; }
    public void setConcluido(boolean concluido){
        this.concluido = concluido;
    }

    public PrioridadeTarefa getPrioridade(){ return prioridade; }
    public void setPrioridade(PrioridadeTarefa prioridadeTarefa) {
        this.prioridade = prioridadeTarefa;
    }
}