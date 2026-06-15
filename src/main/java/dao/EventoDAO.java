package dao;

import jakarta.persistence.EntityManager;
import model.Evento;
import model.Oficina;
import model.Palestra;
import util.JPAUtil;

import java.util.List;

public class EventoDAO {

  public void salva(Evento evento) {

    try (EntityManager em = JPAUtil.getEntityManager()) {
      em.getTransaction().begin();
      em.persist(evento);
      em.getTransaction().commit();
    }
  }

  public List<Evento> buscaPorNome(String nome) {
    try (EntityManager em = JPAUtil.getEntityManager()) {
      return em.createQuery("""
                        SELECT
                          evento
                        FROM
                          Evento evento
                        WHERE
                          LOWER(evento.descricao) LIKE LOWER(:nome)
                        """, Evento.class)
          .setParameter("nome", "%" + nome + "%")
          .getResultList();
    }
  }

  public List<Evento> listaTodos() {
    try (EntityManager em = JPAUtil.getEntityManager()) {
      return em.createQuery("""
                SELECT
                  evento
                FROM
                  Evento evento
                ORDER BY
                  evento.id
                """, Evento.class)
          .getResultList();
    }
  }

  public Evento buscaEventoCompletoPorId(Long id) {
    try (EntityManager em = JPAUtil.getEntityManager()) {
      Evento evento = em.createQuery("""
                SELECT DISTINCT evento
                FROM Evento evento
                LEFT JOIN FETCH evento.cronograma cronograma
                LEFT JOIN FETCH cronograma.atividades
                WHERE evento.id = :id
                """, Evento.class)
          .setParameter("id", id)
          .getSingleResult();

      if (evento instanceof Oficina oficina) {
        oficina.getMateriais().size();
      }

      if (evento instanceof Palestra palestra) {
        palestra.getPalestrantes().size();
      }

      return evento;
    }
  }
}
