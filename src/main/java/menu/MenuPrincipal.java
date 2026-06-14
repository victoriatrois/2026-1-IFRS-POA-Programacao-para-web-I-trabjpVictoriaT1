package menu;

import javax.swing.JOptionPane;

public class MenuPrincipal {

  public static void main(String[] args) {

    boolean executando = true;

    while (executando) {

      String opcao = JOptionPane.showInputDialog("""
                1 - Cadastrar Evento
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