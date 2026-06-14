package testes;

import dao.EventoDAO;

import model.Evento;

import java.util.List;

public class TestePesquisarEventoPorNome {

  public static void main(String[] args) {
    EventoDAO dao = new EventoDAO();

    List<Evento> eventos = dao.buscaPorNome("aquarela");

    if (eventos.isEmpty()) {
      System.out.println("Nenhum evento encontrado.");
      return;
    }

    System.out.println("Eventos encontrados: " + eventos.size());

    for (Evento evento : eventos) {
      System.out.println("\n--------------------");
      System.out.println("ID: " + evento.getId());
      System.out.println("Descrição: " + evento.getDescricao());
      System.out.println("Capacidade máxima: " + evento.getCapacidadeMaxima());
      System.out.println("Situação: " + evento.getSituacao());
    }
  }
}