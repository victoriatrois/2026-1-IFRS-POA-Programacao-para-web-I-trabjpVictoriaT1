package model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToOne;

import java.util.Date;

@Entity
/*
 * Estratégia de herança escolhida: JOINED.
 *
 * Evento concentra os atributos comuns a todos os tipos de evento.
 * Palestra e Oficina possuem atributos específicos.
 *
 * A estratégia JOINED foi escolhida porque evita repetição dos atributos comuns
 * nas tabelas das subclasses e mantém uma tabela própria para cada subtipo.
 * Apesar de exigir JOINs em consultas polimórficas, ela representa bem o modelo
 * orientado a objetos e mantém o banco mais normalizado.
 */
@Inheritance(strategy = InheritanceType.JOINED)
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Date duracao;
    private String descricao;
    private int capacidadeMaxima;

    /*
     * Estratégia de enumeração escolhida: EnumType.STRING.
     *
     * Apesar do armazenamento de dados com ORDINAL possa ocupar menos espaço,
     * oferecendo vantagem de performance, principalmente se o número de registros escalar exponencialmente,
     * priorizei a legibilidade e a segurança da manutenção, armazenando no banco
     * os valores textuais PLANEJADO, EM_ANDAMENTO, CONCLUIDO e CANCELADO, conforme indicado no diagrama de classes.
     */
    @Enumerated(EnumType.STRING)
    private StatusEvento situacao;

    /*
     * Escolhi modelar o relacionamento entre Evento e Cronograma como unidirecional, pois os casos de uso exigem a navegação do evento para seu cronograma, mas não o acesso ao evento a partir do cronograma.
     */
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Cronograma cronograma;

    public Evento() {
    }

    public Evento(Date duracao, String descricao, int capacidadeMaxima, StatusEvento situacao, Cronograma cronograma) {
        this.duracao = duracao;
        this.descricao = descricao;
        this.capacidadeMaxima = capacidadeMaxima;
        this.situacao = situacao;
        this.cronograma = cronograma;
    }

    public long getId() {
        return id;
    }

    public Date getDuracao() {
        return duracao;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getCapacidadeMaxima() {
        return capacidadeMaxima;
    }

    public StatusEvento getSituacao() {
        return situacao;
    }

    @Override
    public String toString() {
        return String.format("""
            Evento:
            ID: %d
            Descricao: %s
            """, id, descricao);
    }
}
