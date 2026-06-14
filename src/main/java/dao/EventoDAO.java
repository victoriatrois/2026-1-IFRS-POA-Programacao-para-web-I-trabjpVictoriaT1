package dao;

import jakarta.persistence.EntityManager;
import model.Evento;
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

  /*
   * Observação:
   * O requisito funcional menciona pesquisa por nome do evento.
   * Como o modelo fornecido possui apenas o atributo descrição, a pesquisa foi implementada utilizando esse atributo.
   */
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
}
