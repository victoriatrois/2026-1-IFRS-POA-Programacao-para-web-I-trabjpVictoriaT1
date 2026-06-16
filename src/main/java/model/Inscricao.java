package model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.util.Date;

@Entity
public class Inscricao {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Date data;

  @ManyToOne
  private Evento evento;

  @ManyToOne(cascade = CascadeType.PERSIST)
  private Participante participante;

  /*
   * Estratégia de enumeração escolhida: EnumType.STRING.
   *
   * Apesar do armazenamento de dados com ORDINAL poder ocupar menos espaço,
   * oferecendo vantagem de performance, principalmente se o número de registros escalar,
   * priorizei a legibilidade e a segurança da manutenção, armazenando no banco
   * os valores textuais PENDENTE, CONFIRMADA, CANCELADA e LISTA_DE_ESPERA, conforme indicado no diagrama de classes.
   */
  @Enumerated(EnumType.STRING)
  private StatusInscricao status;

  public Inscricao() {
  }

  public Inscricao(
      Date data,
      StatusInscricao status,
      Evento evento,
      Participante participante) {

    this.data = data;
    this.status = status;
    this.evento = evento;
    this.participante = participante;
  }

  public Long getId() {
    return id;
  }

  public Date getData() {
    return data;
  }

  public Evento getEvento() {
    return evento;
  }

  public Participante getParticipante() {
    return participante;
  }

  public StatusInscricao getStatus() {
    return status;
  }
}