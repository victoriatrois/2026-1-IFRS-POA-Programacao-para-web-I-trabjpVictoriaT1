package util;

import model.Atividade;
import model.Cronograma;
import model.Evento;
import model.Oficina;
import model.Palestra;
import model.Palestrante;

import javax.swing.*;
import java.awt.*;

public class FormatadorDeEventoUtil {

  public static String formataCronograma(Cronograma cronograma) {
    if (cronograma == null) {
      return "Cronograma: não cadastrado.\n";
    }

    String texto = String.format("""
						Cronograma:
						Início: %s
						Fim: %s
						
						Atividades:
						""",
        cronograma.getDataInicio(),
        cronograma.getDataFim());

    if (cronograma.getAtividades().isEmpty()) {
      return texto + "Nenhuma atividade cadastrada.\n";
    }

    for (Atividade atividade : cronograma.getAtividades()) {
      texto += String.format("""
								- %s
									Início: %s
									Fim: %s
									Situação: %s
									Responsável: %s
								
								""",
          atividade.getNome(),
          atividade.getDataHoraInicio(),
          atividade.getDataHoraFim(),
          atividade.getSituacao(),
          atividade.getResponsavel());
    }

    return texto;
  }

  public static String formataOficina(Oficina oficina) {
    String texto = "\nMateriais:\n";

    if (oficina.getMateriais().isEmpty()) {
      return texto + "Nenhum material cadastrado.\n";
    }

    for (String material : oficina.getMateriais()) {
      texto += "- " + material + "\n";
    }

    return texto;
  }

  public static String formataPalestra(Palestra palestra) {
    String texto = String.format("""
						
						Tempo: %d minutos
						
						Palestrantes:
						""",
        palestra.getTempo());

    if (palestra.getPalestrantes().isEmpty()) {
      return texto + "Nenhum palestrante cadastrado.\n";
    }

    for (Palestrante palestrante : palestra.getPalestrantes()) {
      texto += String.format("""
								- %s (%s)
								""",
          palestrante.getNome(),
          palestrante.getEspecialidade());
    }

    return texto;
  }

  public static String formataEventoCompleto(Evento evento) {
    String texto = String.format("""
						ID: %d
						Tipo: %s
						Descrição: %s
						Data: %s
						Capacidade máxima: %d
						Situação: %s
						
						""",
        evento.getId(),
        evento.getClass().getSimpleName(),
        evento.getDescricao(),
        DataUtil.formataData(evento.getDuracao()),
        evento.getCapacidadeMaxima(),
        evento.getSituacao());

    texto += formataCronograma(evento.getCronograma());

    if (evento instanceof Oficina oficina) {
      texto += formataOficina(oficina);
    }

    if (evento instanceof Palestra palestra) {
      texto += formataPalestra(palestra);
    }

    return texto;
  }
}
