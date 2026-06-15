package menu;

import dao.EventoDAO;

import jakarta.persistence.PersistenceException;

import model.Atividade;
import model.Cronograma;
import model.Evento;
import model.Oficina;
import model.Palestra;
import model.Palestrante;
import model.StatusAtividade;
import model.StatusEvento;

import util.DataUtil;
import util.InputUtil;

import javax.swing.*;

import java.awt.*;

import java.text.ParseException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MenuEvento {
	private static final EventoDAO EVENTO_DAO =
			new EventoDAO();

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
					tiposDeEvento[0]);

			Date duracao = DataUtil.leData(
					"Insira a data do evento (dd/MM/yyyy):");

			String descricao = InputUtil.leTextoObrigatorio((
					"Insira a Descrição do evento:"));

			Integer capacidadeMaxima = InputUtil.leInteiroObrigatorio(
					"Capacidade máxima:");

			if (capacidadeMaxima == null) {
				return;
			}

			StatusEvento situacao = (StatusEvento) JOptionPane.showInputDialog(

					null,
					"Insira a situação do evento:",
					"Situação",
					JOptionPane.QUESTION_MESSAGE,
					null,
					StatusEvento.values(),
					StatusEvento.PLANEJADO);

			Cronograma cronograma = cadastraCronograma();

			Evento evento;

			// na dúvida sobre o tipo, vide tiposDeEvento
			if (tipo == 0) {

				Integer tempo = InputUtil.leInteiroObrigatorio(
						"Tempo da palestra (em minutos):");
				if (tempo == null) {
					return;
				}

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

	private static Cronograma cadastraCronograma()
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

	private static void cadastraPalestrantes(Palestra palestra) {
		do {

			String nome = InputUtil.leTextoObrigatorio(
					"Insira o nome do palestrante:");

			if (nome == null) {
				return;
			}

			String especialidade = InputUtil.leTextoObrigatorio(
					"Insira sua especialidade:");

			if (especialidade == null) {
				return;
			}

			palestra.adicionaPalestrante(
					new Palestrante(
							nome,
							especialidade));

		} while (InputUtil.confirma("Adicionar outro palestrante?"));
	}

	private static List<String> cadastraMateriais() {

		List<String> materiais = new ArrayList<>();

		do {

			String material = InputUtil.leTextoObrigatorio(
					"Material necessário:");

			if (material == null) {
				break;
			}

			materiais.add(material);

		} while (InputUtil.confirma("Adicionar outro material?"));

		return materiais;
	}

	public static void pesquisaEventoPorNome() {
		try {
			String nome = InputUtil.leTextoObrigatorio(
					"Pesquise um evento pelo nome (ou parte do nome):");

			if (nome == null) {
				return;
			}

			List<Evento> eventos = EVENTO_DAO.buscaPorNome(nome.trim());

			if (eventos.isEmpty()) {
				JOptionPane.showMessageDialog(
						null,
						"Nenhum evento encontrado.");

				return;
			}

			String resultado = """
					Eventos encontrados:
					
					""";

			for (Evento evento : eventos) {

				resultado += String.format("""
								ID: %d
								Descrição: %s
								Situação: %s
								
								--------------------
								
								""",
						evento.getId(),
						evento.getDescricao(),
						evento.getSituacao());
			}

			JOptionPane.showMessageDialog(
					null,
					resultado
			);
		} catch (PersistenceException e) {
			JOptionPane.showMessageDialog(
					null,
					"Erro ao consultar os eventos.");

		} catch (Exception e) {

			JOptionPane.showMessageDialog(
					null,
					"Ocorreu um erro inesperado.");

		}
	}

	public static void exibeEventoCompleto() {
		List<Evento> eventos = EVENTO_DAO.listaTodos();

		if (eventos.isEmpty()) {
			JOptionPane.showMessageDialog(
					null,
					"Nenhum evento cadastrado.");

			return;
		}

		Evento eventoSelecionado = (Evento) JOptionPane.showInputDialog(
				null,
				"Selecione um evento:",
				"Eventos",
				JOptionPane.QUESTION_MESSAGE,
				null,
				eventos.toArray(),
				eventos.get(0));

		if (eventoSelecionado == null) {
			return;
		}

		Evento eventoCompleto = EVENTO_DAO.buscaEventoCompletoPorId(
				eventoSelecionado.getId());

		exibeTextoComScroll(
				"Detalhes do evento",
				formataEventoCompleto(eventoCompleto));
	}

	private static String formataEventoCompleto(Evento evento) {
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
			texto += formatarPalestra(palestra);
		}

		return texto;
	}

	private static String formataCronograma(Cronograma cronograma) {
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

	private static String formataOficina(Oficina oficina) {
		String texto = "\nMateriais:\n";

		if (oficina.getMateriais().isEmpty()) {
			return texto + "Nenhum material cadastrado.\n";
		}

		for (String material : oficina.getMateriais()) {
			texto += "- " + material + "\n";
		}

		return texto;
	}

	private static String formatarPalestra(Palestra palestra) {
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

	private static void exibeTextoComScroll(
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