package model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Cronograma {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Date dataInicio;
  private Date dataFim;

  /*
   * A funcionalidade pede que um evento liste as suas atividades a partir do seu cronograma.
   * Não há necessidade, no momento, de navegar de uma atividade para seu cronograma.
   */
  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Atividade> atividades = new ArrayList<>();

  public Cronograma() {
  }

  public Cronograma(Date dataInicio, Date dataFim) {
    this.dataInicio = dataInicio;
    this.dataFim = dataFim;
  }

  public void adicionaAtividade(Atividade atividade) {
    this.atividades.add(atividade);
  }

  public Date getDataInicio() {
    return dataInicio;
  }

  public Date getDataFim() {
    return dataFim;
  }

  public List<Atividade> getAtividades() {
    return atividades;
  }
}
