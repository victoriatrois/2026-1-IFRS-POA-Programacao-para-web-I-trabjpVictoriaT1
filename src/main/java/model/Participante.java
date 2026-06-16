package model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Participante {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nome;

  private String email;

  private Date dataCadastro;

  /*
   * Estratégia de enumeração escolhida: EnumType.STRING.
   *
   * Apesar do armazenamento de dados com ORDINAL possa ocupar menos espaço,
   * oferecendo vantagem de performance, principalmente se o número de registros escalar exponencialmente,
   * priorizei a legibilidade e a segurança da manutenção, armazenando no banco
   * os valores textuais ALUNO, PROFESSOR, PALESTRANTE e COMUNIDADE_EXTERNA, conforme indicado no diagrama de classes.
   */
  @Enumerated(EnumType.STRING)
  private TipoParticipante tipo;

  public Participante() {
  }

  public Participante(
      String nome,
      String email,
      Date dataCadastro,
      TipoParticipante tipo) {

    this.nome = nome;
    this.email = email;
    this.dataCadastro = dataCadastro;
    this.tipo = tipo;
  }

  public String getNome() {
    return nome;
  }

  public String getEmail() {
    return email;
  }

  public Date getDataCadastro() {
    return dataCadastro;
  }

  public TipoParticipante getTipo() {
    return tipo;
  }
}