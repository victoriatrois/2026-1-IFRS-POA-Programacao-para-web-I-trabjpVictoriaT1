package model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Oficina extends Evento {
  @ElementCollection
  private List<String> materiais = new ArrayList<>();

  public Oficina() {
  }

  public Oficina(Date duracao, String descricao, int capacidadeMaxima, StatusEvento situacao, Cronograma cronograma, List<String> materiais) {
    super(duracao, descricao, capacidadeMaxima, situacao, cronograma);
    this.materiais = materiais;
  }
}
