# Comandos SQL

## Comandos utilizados para interação com o banco

```bash
mysql -u root
```

```sql
SHOW DATABASES;

USE trabjpa;

SHOW TABLES;
```

## Comandos utilizados durante a implementação do trabalho

### US1 - Cadastrar Evento
```sql
SELECT * FROM EVENTO;
SELECT * FROM PALESTRA;
SELECT * FROM OFICINA;
SELECT * FROM PALESTRANTE;
SELECT * FROM PALESTRANTE_DA_PALESTRA;
```

## US2 - Visualizar Evento Completo
```sql

```

## US3 - Pesquisar Evento por Nome
```sql
SELECT * FROM Evento WHERE LOWER(descricao) LIKE LOWER('aqua%'); -- pois tem no banco um evento cujo nome contém "Aquarela"

SELECT
  *
FROM 
  Evento
    LEFT JOIN Oficina
      ON Evento.id = Oficina.id
    LEFT JOIN Palestra
      ON Evento.id = Palestra.id;

SELECT
  *
FROM
  evento
ORDER BY
  evento.id;

SELECT DISTINCT
  evento.*
FROM
  Evento evento
    LEFT JOIN Cronograma cronograma
      ON evento.cronograma_id = cronograma.id
    LEFT JOIN Cronograma_Atividade cronograma_atividade
      ON cronograma.id = cronograma_atividade.Cronograma_id
    LEFT JOIN Atividade atividade
      ON cronograma_atividade.atividades_id = atividade.id
WHERE
  evento.id = 4;
```

## US4 - Listar Inscrições e Participantes
```sql

```

## US5 - Listar Cronograma e Atividades
```sql

```

## US6 - Excluir Evento
```sql

```
