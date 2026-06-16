package menu;

import dao.EventoDAO;

import model.Atividade;
import model.Cronograma;
import model.Evento;

import model.StatusAtividade;
import util.DataUtil;
import util.FormatadorDeEventoUtil;
import util.InputUtil;

import javax.swing.*;

import java.text.ParseException;
import java.util.Date;
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

  private static Atividade cadastraAtividade()
      throws ParseException {

    String nome = InputUtil.leTextoObrigatorio(
        "Nome da atividade:");

    if (nome == null) {
      return null;
    }

    Date dataHoraInicio = DataUtil.leDataHora(
        "Data/hora de início da atividade (dd/MM/yyyy HH:mm):");

    Date dataHoraFim = DataUtil.leDataHora(
        "Data/hora de fim da atividade (dd/MM/yyyy HH:mm):");

    StatusAtividade situacao =
        (StatusAtividade) JOptionPane.showInputDialog(
            null,
            "Situação da atividade:",
            "Situação",
            JOptionPane.QUESTION_MESSAGE,
            null,
            StatusAtividade.values(),
            StatusAtividade.PLANEJADA);

    String responsavel = InputUtil.leTextoObrigatorio(
        "Responsável pela atividade:");

    if (responsavel == null) {
      return null;
    }

    return new Atividade(
        nome,
        dataHoraInicio,
        dataHoraFim,
        situacao,
        responsavel);
  }

  protected static Cronograma cadastraCronograma()
      throws ParseException {

    Date dataInicio = DataUtil.leData(
        "Data de início do cronograma (dd/MM/yyyy):");

    Date dataFim = DataUtil.leData(
        "Data de fim do cronograma (dd/MM/yyyy):");

    Cronograma cronograma = new Cronograma(dataInicio, dataFim);

    do {
      Atividade atividade = cadastraAtividade();

      if (atividade == null) {
        return cronograma;
      }

      cronograma.adicionaAtividade(atividade);

    } while (InputUtil.confirma("Adicionar outra atividade?"));

    return cronograma;
  }

  public static void excluiCronograma() throws ParseException {
    List<Evento> eventos = EVENTO_DAO.listaTodos();

    if (eventos.isEmpty()) {
      JOptionPane.showMessageDialog(
          null,
          "Nenhum evento cadastrado.");
      return;
    }

    Evento eventoSelecionado = (Evento) JOptionPane.showInputDialog(
        null,
        "Selecione o evento do cronograma:",
        "Excluir Cronograma",
        JOptionPane.QUESTION_MESSAGE,
        null,
        eventos.toArray(),
        eventos.get(0));

    if (eventoSelecionado == null) {
      return;
    }

    String[] opcoes = {
        "Substituir cronograma",
        "Excluir evento",
        "Cancelar"
    };

    int opcao = JOptionPane.showOptionDialog(
        null,
        """
        Um cronograma é parte de um evento.
  
        Para excluir o cronograma, você deve substituir por um novo cronograma
        com ao menos uma atividade, ou excluir o evento inteiro.
        """,
        "Excluir Cronograma",
        JOptionPane.DEFAULT_OPTION,
        JOptionPane.WARNING_MESSAGE,
        null,
        opcoes,
        opcoes[0]);

    if (opcao == 0) {
      Cronograma novoCronograma = cadastraCronograma();

      eventoSelecionado.substituiCronograma(novoCronograma);

      EVENTO_DAO.atualiza(eventoSelecionado);

      JOptionPane.showMessageDialog(
          null,
          "Cronograma substituído com sucesso.");
    }

    if (opcao == 1) {
      int confirma = JOptionPane.showConfirmDialog(
          null,
          "Tem certeza que deseja excluir o evento inteiro?",
          "Confirmar exclusão",
          JOptionPane.YES_NO_OPTION);

      if (confirma == JOptionPane.YES_OPTION) {
        EVENTO_DAO.remove(eventoSelecionado);

        JOptionPane.showMessageDialog(
            null,
            "Evento excluído com sucesso.");
      }
    }
  }
}
