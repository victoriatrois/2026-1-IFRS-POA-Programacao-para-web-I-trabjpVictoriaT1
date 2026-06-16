package menu;

import util.CargaInicialUtil;
import util.InputUtil;

import javax.swing.JOptionPane;
import java.text.ParseException;

public class MenuPrincipal {

  public static void main(String[] args) throws ParseException {

    boolean executando = true;

    CargaInicialUtil.executaSeBancoEstiverVazio();

    while (executando) {

      String opcao = InputUtil.leTextoObrigatorio("""
                1 - Cadastrar Evento
                2 - Listar o evento e todos os itens vinculados a ele
                3 - Pesquisar um Evento por Nome
                4 - Listar inscrições e participantes
                5 - Listar um cronograma e todas suas atividades
                6 - Excluir cronograma
                0 - Sair
                """);

      if (opcao == null) {
        executando = false;
        continue;
      }

      switch (opcao) {

        case "1":
          MenuEvento.cadastraEvento();
          break;

        case "2":
          MenuEvento.exibeEventoCompleto();
          break;

        case "3":
          MenuEvento.pesquisaEventoPorNome();
          break;

        case "4":
          MenuInscricao.exibeInscricoesPorEvento();
          break;

        case "5":
          MenuCronograma.listaCronogramaEAtividades();
          break;

        case "6":
          MenuCronograma.excluiCronograma();
          break;

        case "0":
          executando = false;
          break;

        default:
          JOptionPane.showMessageDialog(
              null,
              "Opção inválida.");
      }
    }

    JOptionPane.showMessageDialog(
        null,
        "Sistema encerrado.");
  }
}