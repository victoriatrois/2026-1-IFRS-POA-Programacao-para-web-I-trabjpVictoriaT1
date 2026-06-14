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
