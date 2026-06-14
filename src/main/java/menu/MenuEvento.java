package menu;

import dao.EventoDAO;

import jakarta.persistence.PersistenceException;

import model.Cronograma;
import model.Evento;
import model.Oficina;
import model.Palestra;
import model.Palestrante;
import model.StatusEvento;

import util.DataUtil;

import javax.swing.*;

import java.text.ParseException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MenuEvento {
  public static void cadastraEvento() {

    try {
      String[] tiposDeEvento = {"Palestra", "Oficina"};

      int tipo = JOptionPane.showOptionDialog(
          null,
          "Selecione o tipo de evento que deseja cadastrar:",
          "Cadastro de Evento",
          JOptionPane.DEFAULT_OPTION,
          JOptionPane.QUESTION_MESSAGE,
          null,
          tiposDeEvento,
          tiposDeEvento[0]
      );

      Date duracao = DataUtil.leData(
          "Insira a data do evento (dd/MM/yyyy):");

      String descricao = JOptionPane.showInputDialog(
          "Insira a Descrição do evento:");

      int capacidadeMaxima = Integer.parseInt(
          JOptionPane.showInputDialog(
              "Insira a capacidade máxima:"));

      StatusEvento situacao =
          (StatusEvento) JOptionPane.showInputDialog(
              null,
              "Insira a situação do evento:",
              "Situação",
              JOptionPane.QUESTION_MESSAGE,
              null,
              StatusEvento.values(),
              StatusEvento.PLANEJADO);

      Cronograma cronograma = cadastraCronograma();

      Evento evento;

      if (tipo == 0) {

        int tempo = Integer.parseInt(
            JOptionPane.showInputDialog(
                "Tempo da palestra (em minutos):"));

        Palestra palestra = new Palestra(
            duracao,
            descricao,
            capacidadeMaxima,
            situacao,
            cronograma,
            tempo);

        cadastraPalestrantes(palestra);

        evento = palestra;

      } else {

        List<String> materiais =
            cadastraMateriais();

        evento = new Oficina(
            duracao,
            descricao,
            capacidadeMaxima,
            situacao,
            cronograma,
            materiais);
      }

      EventoDAO dao = new EventoDAO();
      dao.salva(evento);

      JOptionPane.showMessageDialog(
          null,
          "Evento cadastrado com sucesso!");

    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(
          null,
          "Valor numérico inválido.");

    } catch (ParseException e) {
      JOptionPane.showMessageDialog(
          null,
          "Data informada em formato inválido.");

    } catch (PersistenceException e) {
      JOptionPane.showMessageDialog(
          null,
          "Erro ao salvar os dados no banco.");

    } catch (Exception e) {
      JOptionPane.showMessageDialog(
          null,
          "Um erro inesperado ocorreu.");
    }
  }

    private static Cronograma cadastraCronograma() {
      return new Cronograma();
    }

    private static void cadastraPalestrantes(Palestra palestra) {
      int resposta;

      do {

        String nome = JOptionPane.showInputDialog(
            "Insira o nome do palestrante:");

        String especialidade =
            JOptionPane.showInputDialog(
                "Insira sua especialidade:");

        palestra.adicionaPalestrante(
            new Palestrante(
                nome,
                especialidade));

        resposta = JOptionPane.showConfirmDialog(
            null,
            "Adicionar outro palestrante?");

      } while (resposta == JOptionPane.YES_OPTION);
    }

    private static List<String> cadastraMateriais() {

      List<String> materiais = new ArrayList<>();

      int resposta;

      do {

        materiais.add(
            JOptionPane.showInputDialog(
                "Material necessário:"));

        resposta = JOptionPane.showConfirmDialog(
            null,
            "Adicionar outro material?");

      } while (resposta == JOptionPane.YES_OPTION);

      return materiais;
    }
  }
