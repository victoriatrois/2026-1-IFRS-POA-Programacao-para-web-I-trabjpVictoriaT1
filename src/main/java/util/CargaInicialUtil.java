package util;

import dao.EventoDAO;
import dao.InscricaoDAO;

import model.Atividade;
import model.Cronograma;
import model.Inscricao;
import model.Oficina;
import model.Palestra;
import model.Palestrante;
import model.Participante;
import model.StatusAtividade;
import model.StatusEvento;
import model.StatusInscricao;
import model.TipoParticipante;

import java.util.Date;
import java.util.List;

public class CargaInicialUtil {

  public static void executaSeBancoEstiverVazio() {
    EventoDAO eventoDAO = new EventoDAO();

    if (!eventoDAO.listaTodos().isEmpty()) {
      return;
    }

    Oficina oficina = criaOficina();
    Palestra palestra = criaPalestra();

    eventoDAO.salva(oficina);
    eventoDAO.salva(palestra);

    InscricaoDAO inscricaoDAO = new InscricaoDAO();

    inscricaoDAO.salva(new Inscricao(
        new Date(),
        StatusInscricao.CONFIRMADA,
        oficina,
        new Participante("Victoria", "victoria@email.com", new Date(), TipoParticipante.ALUNO)));

    inscricaoDAO.salva(new Inscricao(
        new Date(),
        StatusInscricao.CONFIRMADA,
        palestra,
        new Participante("Ana", "ana@email.com", new Date(), TipoParticipante.PROFESSOR)));
  }

  private static Oficina criaOficina() {
    Cronograma cronograma = new Cronograma(new Date(), new Date());

    cronograma.adicionaAtividade(new Atividade(
        "Introdução aos materiais",
        new Date(),
        new Date(),
        StatusAtividade.PLANEJADA,
        "Coordenação"));

    return new Oficina(
        new Date(),
        "Aquarela para iniciantes",
        12,
        StatusEvento.PLANEJADO,
        cronograma,
        List.of("Papel algodão 300g", "Tinta aquarela", "Pincéis redondos"));
  }

  private static Palestra criaPalestra() {
    Cronograma cronograma = new Cronograma(new Date(), new Date());

    cronograma.adicionaAtividade(new Atividade(
        "Conceitos iniciais de JPA",
        new Date(),
        new Date(),
        StatusAtividade.PLANEJADA,
        "Coordenação"));

    Palestra palestra = new Palestra(
        new Date(),
        "Introdução ao Jakarta Persistence",
        80,
        StatusEvento.PLANEJADO,
        cronograma,
        90);

    palestra.adicionaPalestrante(
        new Palestrante("Silvia", "Java e ORM"));

    return palestra;
  }
}