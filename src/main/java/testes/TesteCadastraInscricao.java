package testes;

import dao.EventoDAO;
import dao.InscricaoDAO;

import model.*;

import java.util.Date;
import java.util.List;

public class TesteCadastraInscricao {

  public static void main(String[] args) {

    EventoDAO eventoDAO =
        new EventoDAO();

    List<Evento> eventos =
        eventoDAO.listaTodos();

    if (eventos.isEmpty()) {

      System.out.println(
          "Cadastre um evento antes.");

      return;
    }

    Evento evento =
        eventos.get(0);

    Participante participante =
        new Participante(
            "Victoria Corrêa",
            "victoria@email.com",
            new Date(),
            TipoParticipante.ALUNO
        );

    Inscricao inscricao =
        new Inscricao(
            new Date(),
            StatusInscricao.CONFIRMADA,
            evento,
            participante
        );

    InscricaoDAO dao =
        new InscricaoDAO();

    dao.salva(inscricao);

    System.out.println(
        "Inscrição cadastrada!");
  }
}