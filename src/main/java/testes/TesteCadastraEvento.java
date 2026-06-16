package testes;

import dao.EventoDAO;

import model.Cronograma;
import model.Atividade;
import model.StatusAtividade;
import model.Evento;
import model.StatusEvento;

import java.util.Calendar;
import java.util.Date;

public class TesteCadastraEvento {

    public static void main(String[] args) {
        Cronograma cronograma = new Cronograma();

        Calendar calendar = Calendar.getInstance();
        Date inicio = calendar.getTime();
        calendar.add(Calendar.HOUR, 1);
        Date fim = calendar.getTime();

        cronograma.adicionaAtividade(
                new Atividade(
                        "Abertura",
                        inicio,
                        fim,
                        StatusAtividade.PLANEJADA,
                        "Coordenação do evento"));

        calendar.add(Calendar.MINUTE, 30);
        inicio = calendar.getTime();
        calendar.add(Calendar.MINUTE, 90);
        fim = calendar.getTime();

        cronograma.adicionaAtividade(
                new Atividade(
                        "Palestra JPA",
                        inicio,
                        fim,
                        StatusAtividade.PLANEJADA,
                        "Coordenação do evento"));

        Evento evento = new Evento(
                new Date(),
                "JPA no IFRS",
                250,
                StatusEvento.PLANEJADO,
                cronograma);

        EventoDAO dao = new EventoDAO();
        dao.salva(evento);

        System.out.println("Evento cadastrado com sucesso!");
    }
}