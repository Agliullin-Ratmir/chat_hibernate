package repositories;

import entities.Group;
import entities.Message;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MessageRepository {

    @PersistenceContext
    private EntityManager em;

    public void persist(Message message) {
        em.persist(message);
    }
}
