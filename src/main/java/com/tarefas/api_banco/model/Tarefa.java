package com.tarefas.api_banco.model;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.UUID;

@Entity //salva a classe como sendo uma tabela no banco
@Table(name="tarefas")//da o nome da tarefa
public class Tarefa {
    @Id//salvar qual parte da tabela sera a chave primaria
    @UuidGenerator//gera um uuid para o campo
    private UUID id;
    //não pode ser nulo = nullable = false
    @Column(nullable = false, length = 100)
    private String titulo;
    //length = xxx = quantos digitos pode ter
    @Column(length = 255)
    private String descricao;
    @Column(nullable = false)
    private boolean concluido;
    //@Enumerated(EnumType.STRING) salva os membros do enum como eles mesmos e não numeros
    @Enumerated(EnumType.STRING)
    private PrioridadeTarefa prioridade;
    //name = xxxxxxxx diz para o sistema qual o nome do componente da tabela
    @Column(nullable = false, name = "dt_tarefa")
    private LocalDate dataTarefa;
    //por não ter como setar o id ele apenas o gera automaticamente
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

    public LocalDate getDataTarefa(){return dataTarefa;}
    public void setDataTarefa(LocalDate dataTarefa){ this.dataTarefa = dataTarefa; }
}