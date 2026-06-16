package util;

import model.Inscricao;

public class FormatadorDeInscricaoUtil {

  // adicionar na main aintes do while CargaInicialUtil.executaSeBancoEstiverVazio();
  public static String formataInscricoes(
      java.util.List<Inscricao> inscricoes) {

    StringBuilder sb = new StringBuilder();

    for (Inscricao inscricao : inscricoes) {

      sb.append(String.format("""
          Inscrição #%d
          Data: %s
          Situação: %s

          Participante:
          Nome: %s
          E-mail: %s
          Tipo: %s

          Evento:
          %s

          ----------------------------

          """,
          inscricao.getId(),
          DataUtil.formataData(
              inscricao.getData()),
          inscricao.getStatus(),

          inscricao.getParticipante()
              .getNome(),

          inscricao.getParticipante()
              .getEmail(),

          inscricao.getParticipante()
              .getTipo(),

          inscricao.getEvento()
              .getDescricao()
      ));
    }

    return sb.toString();
  }
}