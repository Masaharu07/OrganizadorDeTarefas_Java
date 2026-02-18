
CREATE TYPE prioridade_tarefa as ENUM ('BAIXA', 'MEDIA', 'ALTA' );

CREATE TABLE tarefas (
                         id UUID PRIMARY KEY,
                         titulo VARCHAR(100) NOT NULL,
                         descricao VARCHAR(255),
                         concluido BOOLEAN NOT NULL,
                         prioridade prioridade_tarefa
);
