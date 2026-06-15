package util;

import javax.swing.JOptionPane;

public class InputUtil {

  public static String leTextoObrigatorio(String mensagem) {
    String texto;

    do {
      texto = JOptionPane.showInputDialog(mensagem);

      if (texto == null) {
        return null;
      }

      texto = texto.trim();

      if (texto.isBlank()) {
        JOptionPane.showMessageDialog(
            null,
            "É necessário informar uma opção.");
      }

    } while (texto.isBlank());

    return texto;
  }

  public static boolean confirma(
      String mensagem) {

    return JOptionPane.showConfirmDialog(
        null,
        mensagem
    ) == JOptionPane.YES_OPTION;
  }
}
