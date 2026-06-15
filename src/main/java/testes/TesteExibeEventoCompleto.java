package testes;

import dao.EventoDAO;
import model.Evento;
import util.DataUtil;

import java.util.List;

public class TesteExibeEventoCompleto {

  public static void main(String[] args) {
    EventoDAO dao = new EventoDAO();

    List<Evento> eventos = dao.listaTodos();

    if (eventos.isEmpty()) {
      System.out.println("Nenhum evento encontrado.");
      return;
    }

    System.out.println("Eventos cadastrados: " + eventos.size());

    for (Evento evento : eventos) {
      System.out.println("--------------------");
      System.out.println("ID: " + evento.getId());
      System.out.println("Descrição: " + evento.getDescricao());
      System.out.println("Data: " + DataUtil.formataData(evento.getDuracao()));
      System.out.println("Capacidade máxima: " + evento.getCapacidadeMaxima());
      System.out.println("Situação: " + evento.getSituacao());
      System.out.println("Tipo: " + evento.getClass().getSimpleName());
    }


  }
}