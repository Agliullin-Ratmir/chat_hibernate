package repositories;

import entities.Group;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class GroupRepository {

    @PersistenceContext
    private EntityManager em;

    public void persist(Group group) {
        em.persist(group);
    }

    public List<Group> findAll() {
        return em.createQuery("SELECT g FROM Group g").getResultList();
    }
}
