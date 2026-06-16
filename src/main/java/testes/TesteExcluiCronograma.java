package testes;

import dao.EventoDAO;

import model.Atividade;
import model.Cronograma;
import model.Evento;
import model.StatusAtividade;

import java.util.Date;
import java.util.List;

public class TesteExcluiCronograma {

  public static void main(String[] args) {
    EventoDAO eventoDAO = new EventoDAO();

    List<Evento> eventos = eventoDAO.listaTodos();

    if (eventos.isEmpty()) {
      System.out.println("Cadastre um evento antes.");
      return;
    }

    Evento evento = eventos.get(0);

    Cronograma novoCronograma =
        new Cronograma(new Date(), new Date());

    Atividade novaAtividade =
        new Atividade(
            "Nova atividade do cronograma",
            new Date(),
            new Date(),
            StatusAtividade.PLANEJADA,
            "Coordenação"
        );

    novoCronograma.adicionaAtividade(novaAtividade);

    evento.substituiCronograma(novoCronograma);

    eventoDAO.atualiza(evento);

    Evento eventoAtualizado =
        eventoDAO.buscaEventoCompletoPorId(evento.getId());

    System.out.println("Evento atualizado:");
    System.out.println(eventoAtualizado.getDescricao());
    System.out.println("Atividades do novo cronograma:");

    for (Atividade atividade :
        eventoAtualizado.getCronograma().getAtividades()) {
      System.out.println("- " + atividade.getNome());
    }
  }
}