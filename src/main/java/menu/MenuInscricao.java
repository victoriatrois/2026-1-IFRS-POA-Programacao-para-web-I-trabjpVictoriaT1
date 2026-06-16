package menu;

import dao.EventoDAO;
import dao.InscricaoDAO;
import model.Evento;
import model.Inscricao;
import util.ExibicaoUtil;
import util.FormatadorDeInscricaoUtil;

import javax.swing.*;
import java.util.List;

public class MenuInscricao {

  private static final InscricaoDAO INSCRICAO_DAO =
      new InscricaoDAO();

  private static final EventoDAO EVENTO_DAO =
      new EventoDAO();

  public static void exibeInscricoesPorEvento() {

    List<Evento> eventos =
        EVENTO_DAO.listaTodos();

    if (eventos.isEmpty()) {
      JOptionPane.showMessageDialog(
          null,
          "Nenhum evento cadastrado.");
      return;
    }

    Evento eventoSelecionado =
        (Evento) JOptionPane.showInputDialog(
            null,
            "Selecione o evento para listar as inscrições:",
            "Inscrições por Evento",
            JOptionPane.QUESTION_MESSAGE,
            null,
            eventos.toArray(),
            eventos.get(0));

    if (eventoSelecionado == null) {
      return;
    }

    List<Inscricao> inscricoes =
        INSCRICAO_DAO.listaPorEvento(
            eventoSelecionado.getId());

    if (inscricoes.isEmpty()) {
      JOptionPane.showMessageDialog(
          null,
          "Não há inscrições para o evento selecionado.");
      return;
    }

    ExibicaoUtil.exibeTextoComScroll(
        "Inscrições do Evento",
        FormatadorDeInscricaoUtil
            .formataInscricoes(inscricoes));
  }
}