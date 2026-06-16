package util;

import javax.swing.*;

import java.awt.*;

public class ExibicaoUtil {
  public static void exibeTextoComScroll(
      String titulo,
      String texto) {

    JTextArea areaTexto =
        new JTextArea(texto);

    areaTexto.setEditable(false);
    areaTexto.setLineWrap(true);
    areaTexto.setWrapStyleWord(true);

    JScrollPane scrollPane =
        new JScrollPane(areaTexto);

    scrollPane.setPreferredSize(
        new Dimension(700, 450));

    JOptionPane.showMessageDialog(
        null,
        scrollPane,
        titulo,
        JOptionPane.INFORMATION_MESSAGE);
  }
}
