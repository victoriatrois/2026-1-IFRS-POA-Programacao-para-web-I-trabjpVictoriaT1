package testes;

import dao.EventoDAO;

import model.Evento;

import util.FormatadorDeEventoUtil;

import java.util.List;

public class TesteExibeCronogramaEAtividades {

  public static void main(String[] args) {
    EventoDAO dao = new EventoDAO();

    List<Evento> eventos = dao.listaTodos();

    if (eventos.isEmpty()) {
      System.out.println("Nenhum evento cadastrado.");
      return;
    }

    Evento eventoSelecionado = eventos.get(0);

    Evento eventoCompleto =
        dao.buscaEventoCompletoPorId(
            eventoSelecionado.getId());

    System.out.println(
        FormatadorDeEventoUtil.formataCronograma(
            eventoCompleto.getCronograma()));
  }
}