package dao;

import jakarta.persistence.EntityManager;
import model.Evento;
import util.JPAUtil;

public class EventoDAO {

    public void salva(Evento evento){

        try (EntityManager em = JPAUtil.getEntityManager()) {
            em.getTransaction().begin();
            em.persist(evento);
            em.getTransaction().commit();
        }
    }
}
