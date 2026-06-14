package model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Atividade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Date dataHoraInicio;
    private Date dataHoraFim;
    private String responsavel;

    /*
     * Estratégia de enumeração escolhida: EnumType.STRING.
     *
     * Apesar do armazenamento de dados com ORDINAL possa ocupar menos espaço,
     * oferecendo vantagem de performance, principalmente se o número de registros escalar exponencialmente,
     * priorizei a legibilidade e a segurança da manutenção, armazenando no banco
     * os valores textuais PLANEJADA, EM_ANDAMENTO, CONCLUIDA e CANCELADA, conforme indicado no diagrama de classes.
     */
    @Enumerated(EnumType.STRING)
    private StatusAtividade situacao;

    public Atividade() {
    }

    public Atividade(String nome, Date dataHoraInicio, Date dataHoraFim, StatusAtividade situacao, String responsavel) {
        this.nome = nome;
        this.dataHoraInicio = dataHoraInicio;
        this.dataHoraFim = dataHoraFim;
        this.situacao = situacao;
        this.responsavel = responsavel;
    }
}
