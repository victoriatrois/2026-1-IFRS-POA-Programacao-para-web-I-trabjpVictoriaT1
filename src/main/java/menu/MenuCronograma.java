package menu;

import dao.EventoDAO;

import model.Evento;

import util.FormatadorDeEventoUtil;

import javax.swing.*;

import java.util.List;

import static util.ExibicaoUtil.exibeTextoComScroll;

public class MenuCronograma {

  private static final EventoDAO EVENTO_DAO = new EventoDAO();

  public static void listaCronogramaEAtividades() {
    List<Evento> eventos = EVENTO_DAO.listaTodos();

    if (eventos.isEmpty()) {
      JOptionPane.showMessageDialog(
          null,
          "Nenhum evento cadastrado.");
      return;
    }

    Evento eventoSelecionado = (Evento) JOptionPane.showInputDialog(
        null,
        "Selecione o evento para visualizar o cronograma:",
        "Cronogramas",
        JOptionPane.QUESTION_MESSAGE,
        null,
        eventos.toArray(),
        eventos.get(0));

    if (eventoSelecionado == null) {
      return;
    }

    Evento eventoCompleto =
        EVENTO_DAO.buscaEventoCompletoPorId(
            eventoSelecionado.getId());

    exibeTextoComScroll(
        "Cronograma e Atividades",
        FormatadorDeEventoUtil.formataCronograma(
            eventoCompleto.getCronograma()));
  }
}
