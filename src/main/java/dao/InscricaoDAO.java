package dao;

import jakarta.persistence.EntityManager;

import model.Inscricao;

import util.JPAUtil;

import java.util.List;

public class InscricaoDAO {

  public void salva(Inscricao inscricao) {

    try (EntityManager em =
             JPAUtil.getEntityManager()) {

      em.getTransaction().begin();

      em.persist(inscricao);

      em.getTransaction().commit();
    }
  }

  public List<Inscricao> listaTodas() {

    try (EntityManager em =
             JPAUtil.getEntityManager()) {

      return em.createQuery("""
                SELECT
                  inscricao
                FROM
                  Inscricao inscricao
                ORDER BY
                  inscricao.id
                """, Inscricao.class)
          .getResultList();
    }
  }

  public List<Inscricao> listaPorEvento(Long eventoId) {

    try (EntityManager em =
             JPAUtil.getEntityManager()) {

      return em.createQuery("""
              SELECT
                inscricao
              FROM
                Inscricao inscricao
              JOIN FETCH
                inscricao.participante
              JOIN FETCH
                inscricao.evento
              WHERE
                inscricao.evento.id = :eventoId
              ORDER BY
                inscricao.id
              """, Inscricao.class)
          .setParameter("eventoId", eventoId)
          .getResultList();
    }
  }
}