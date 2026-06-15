package util;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static util.InputUtil.leTextoObrigatorio;

public class DataUtil {
  private static final SimpleDateFormat FORMATO_DATA =
      new SimpleDateFormat("dd/MM/yyyy");

  private static final SimpleDateFormat FORMATO_DATA_HORA =
      new SimpleDateFormat("dd/MM/yyyy HH:mm");

  public static Date leData(String mensagem)
      throws ParseException {

    String texto =
        leTextoObrigatorio(mensagem);

    return FORMATO_DATA.parse(texto);
  }

  public static Date leDataHora(String mensagem)
      throws ParseException {

    String texto =
        JOptionPane.showInputDialog(mensagem);

    return FORMATO_DATA_HORA.parse(texto);
  }

  public static String formataData(Date data) {
    if (data == null) {
      return "";
    }

    return FORMATO_DATA.format(data);
  }
}
