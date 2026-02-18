package com.tarefas.api_banco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ApiBancoApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiBancoApplication.class, args);
    }
}
